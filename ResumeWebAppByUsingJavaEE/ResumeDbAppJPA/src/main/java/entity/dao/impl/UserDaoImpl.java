package entity.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import entity.Country;
import entity.User;
import entity.dao.inter.AbstractDAO;
import entity.dao.inter.UserDaoInter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        return new User(id, name, surname, phone, email, profileDesc, birthdate, nationality, birthplace);
    }


    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        Date birthdate = rs.getDate("birthdate");

//        User user = new User(id, name, surname, phone, email, profileDesc, birthdate, null, null);
//        user.setPassword(rs.getString("password"));

        return null;
    }


    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try {
            Connection c = connect();
            String sql = "SELECT u.*, " +
                    " n.nationality as nationality, " +
                    "  c.name as birthplace " +
                    "FROM user u " +
                    "LEFT JOIN country n on u.nationality_id=n.id " +
                    "LEFT JOIN country c on u.birthplace_id=c.id where 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=?";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and u.surname=? ";
            }
            if (nationalityId != null) {
                sql += " and u.nationality_id=? ";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }
            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("Select * from user where email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public User findByEmailAndAdress(String email, String password) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("Select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getByteID(int userId) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emFactory.createEntityManager();
        User u = entityManager.find(User.class, userId);
        emFactory.close();
        entityManager.close();
        return u;

    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement
                    stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=?,profile_description=? where id=? ");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setInt(6, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emFactory.createEntityManager();

        entityManager.getTransaction().begin(); //bir neçə əməliyyat etməyə yarayır
        User u = entityManager.find(User.class, id);
        entityManager.remove(u);
        entityManager.getTransaction().commit(); //təsdiqləmək


        emFactory.close();
        entityManager.close();
        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    public boolean addUser(User u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,password,profile_description) values (?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDesc());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
