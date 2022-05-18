package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.AbstractDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {


    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
//        return new Skill(id,name);
        return null;
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill skl = getSkill(rs);
                result.add(skl);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
