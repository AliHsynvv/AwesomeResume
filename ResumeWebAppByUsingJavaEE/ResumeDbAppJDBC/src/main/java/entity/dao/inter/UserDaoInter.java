package entity.dao.inter;

import entity.User;
import entity.UserSkill;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User findByEmailAndAdress(String email, String password);

    public User getByteID(int id);

    public boolean updateUser(User u);

    public boolean removeUser(int id);

    public boolean addUser(User u);

    public User findByEmail(String email);
}
