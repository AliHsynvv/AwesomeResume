package entity.dao.impl;

import entity.dao.inter.AbstractDAO;
import entity.dao.inter.CountryDaoInter;
import entity.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id,name,nationality);
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from country ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country cntry =getCountry(rs);
                result.add(cntry);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
