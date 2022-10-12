package com.bookshop.dao;

import com.bookshop.bean.PageBean;
import com.bookshop.bean.TypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TypeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insertType(TypeBean type){
        String sql="insert into boottype(type_name,type_depict,del_flag) values(?,?,1)";
        return jdbcTemplate.update(sql,type.getTypeName(),type.getTypeDepict());
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<TypeBean> selectTypePage(PageBean page){
        String sql="select type_id,type_name,type_depict,del_flag from boottype limit ?,?";
        List<TypeBean> types = new ArrayList<>();

        types = jdbcTemplate.query(sql,
                new RowMapper<TypeBean>() {
            @Override
            public TypeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                TypeBean type = new TypeBean();
                type.setTypeId(rs.getInt("type_id"));
                type.setTypeName(rs.getString("type_name"));
                type.setTypeDepict(rs.getString("type_depict"));
                type.setDelFlag(rs.getInt("del_flag"));
                return type;
            }
        }
        ,(page.getPageNow()-1)* page.getPageSize(),page.getPageSize());
        return types;
    }

    /**
     * 查询总行数
     * @return
     */
    public int getRowCount(){
        String sql ="select count(type_id) from boottype";
        return  jdbcTemplate.queryForObject(sql,Integer.class);
    }

    /**
     * 删除图书类型
     * @param id
     * @return
     */
    public int deleteType(String id){
        String sql ="UPDATE boottype set del_flag=0 where type_id = ?";

        return jdbcTemplate.update(sql,id);
    }

    /**
     * 修改图书类型
     * @param type
     * @return
     */
    public int updateType(TypeBean type){
        String sql ="UPDATE boottype set type_name=?,type_depict=? where type_id = ?";
        return jdbcTemplate.update(sql,type.getTypeName(), type.getTypeDepict(),type.getTypeId());
    }

    /**
     * 查询所有的图书类型
     * @param
     * @return
     */
    public List<TypeBean> selectTypeAll(){
        String sql="select type_id,type_name,type_depict,del_flag from boottype";
        List<TypeBean> types = new ArrayList<>();

        types = jdbcTemplate.query(sql,
                new RowMapper<TypeBean>() {
                    @Override
                    public TypeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TypeBean type = new TypeBean();
                        type.setTypeId(rs.getInt("type_id"));
                        type.setTypeName(rs.getString("type_name"));
                        type.setTypeDepict(rs.getString("type_depict"));
                        type.setDelFlag(rs.getInt("del_flag"));
                        return type;
                    }
                });
        return types;
    }
}
