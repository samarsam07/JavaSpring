package com.samar.springJDBCDemo.repo;

import com.samar.springJDBCDemo.model.Alein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AleinRepo {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Alein alein){
        String sql="insert into alein (id,name,tech) values (?,?,?)";
        int rows=template.update(sql,alein.getId(),alein.getName(),alein.getTech());
        System.out.println(rows+"rows affected");
    }
    public List<Alein> findAll(){
        String sql="select * from alein";
        RowMapper<Alein> mapper=new RowMapper<Alein>() {
            @Override
            public Alein mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alein a=new Alein();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setTech(rs.getString(3));
                return a;
            }
        };
        List<Alein> aleins=template.query(sql,mapper);
        return  aleins;
    }
}
