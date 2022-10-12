package com.bookshop.dao;

import com.bookshop.bean.AdminBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 操作数据库的表，对应的表示admins
 */
@Component
public class UserDao {
    //数据库所有操作都封装在JdbcTemplate中
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param account 账号
     * @param pass 密码
     * @return AdminBean 一个管理员对象
     */
    public AdminBean selectByAccountAndPass(String account, String pass){

//        String sql="select*" +
//                "From admins where account=? and pass=MD5(?)";
        String sql="SELECT admin_id,account,pass,nick_name,del_flag " + "FROM admins where account=? and pass=md5(?)";

//        AdminBean admin =null;
        List<AdminBean> admins=jdbcTemplate.query(sql, new RowMapper<AdminBean>() {
            @Override
            public AdminBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminBean admin = new AdminBean();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAccount(rs.getString("account"));
                admin.setPass(rs.getString("pass"));
                admin.setNickName(rs.getString("nick_name"));
                admin.setDelFlag(rs.getInt("del_flag"));
                return admin;
            }
        },account,pass);

       return admins!=null&&admins.size()>0?admins.get(0):null;
    }
    private void rowMapper(){
        RowMapper<AdminBean> rm =new RowMapper<AdminBean>(){
            @Override
            public AdminBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminBean admin = new AdminBean();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAccount(rs.getString("account"));
                admin.setPass(rs.getString("pass"));
                admin.setNickName(rs.getString("nick_name"));
                admin.setDelFlag(rs.getInt("del_flag"));
                return admin;
            }
        };
    }

    public AdminBean selectById(String id){
        String sql = "select admin_id,account,pass,nick_name,del_flag from admins where admin_id=?";
        List<AdminBean> admins=jdbcTemplate.query(sql,new RowMapper<AdminBean>(){
            @Override
            public AdminBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminBean admin = new AdminBean();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAccount(rs.getString("account"));
                admin.setPass(rs.getString("pass"));
                admin.setNickName(rs.getString("nick_name"));
                admin.setDelFlag(rs.getInt("del_flag"));
                return admin;
            }
        },id);
       return admins!=null && admins.size()>0?admins.get(0):null;
    }

    /**
     * 密码修改
     * @param id
     * @param pass
     * @return
     */
    public int updatePass(String id,String pass){
        String sql ="update admins set pass=md5(?) where admin_id=?";
       return jdbcTemplate.update(sql,pass,id);
    }

    public int updateAdmin(AdminBean admin){
        String sql="update admins set account=?,nick_name=? where admin_id=?";
       return jdbcTemplate.update(sql,admin.getAccount(),admin.getNickName(),admin.getAdminId());
    }
}
