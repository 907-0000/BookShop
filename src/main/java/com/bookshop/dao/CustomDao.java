package com.bookshop.dao;

import com.bookshop.bean.CustomBean;
import com.bookshop.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有客户
     * @return
     */
    public List<CustomBean> selectAll(){
        String sql ="select custom_id,phone,pass,nick_name,gender,address,del_flag from customer";
        List<CustomBean> customs= new ArrayList<>();
        customs=jdbcTemplate.query(sql, new RowMapper<CustomBean>() {

            @Override
            public CustomBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomBean custom = new CustomBean();
                custom.setCustomId(rs.getInt("custom_id"));
                custom.setPhone(rs.getString("phone"));
                custom.setPass(rs.getString("pass"));
                custom.setNickName(rs.getString("nick_name"));
                custom.setGender(rs.getString("gender"));
                custom.setAddress(rs.getString("address"));
                custom.setDelFlag(rs.getInt("del_flag"));

                return custom;
            }
        });
        return customs;
    }

    /**
     * 分页查询数据
     * @param page 分页数据
     * @param phone
     * @return
     */
    public List<CustomBean> selectCustomsPage(PageBean page,String phone){
        String sql ="select custom_id,phone,pass,nick_name,gender,address,del_flag from customer ";
        if (phone!=null&&!phone.equals("")){
            sql+="where phone LIKE'?%'";
        }
        sql+="LIMIT ?,?";
        List<CustomBean> customs= new ArrayList<>();
        if(phone!=null&&!phone.equals("")){
            //start =(pagenow -1) * pagesize
            customs=jdbcTemplate.query(sql, new RowMapper<CustomBean>() {

                @Override
                public CustomBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CustomBean custom = new CustomBean();
                    custom.setCustomId(rs.getInt("custom_id"));
                    custom.setPhone(rs.getString("phone"));
                    custom.setPass(rs.getString("pass"));
                    custom.setNickName(rs.getString("nick_name"));
                    custom.setGender(rs.getString("gender"));
                    custom.setAddress(rs.getString("address"));
                    custom.setDelFlag(rs.getInt("del_flag"));

                    return custom;
                }
            },(page.getPageNow()-1)* page.getPageSize(),page.getPageSize(),phone);
        }else{
            //start =(pagenow -1) * pagesize
            customs=jdbcTemplate.query(sql, new RowMapper<CustomBean>() {

                @Override
                public CustomBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CustomBean custom = new CustomBean();
                    custom.setCustomId(rs.getInt("custom_id"));
                    custom.setPhone(rs.getString("phone"));
                    custom.setPass(rs.getString("pass"));
                    custom.setNickName(rs.getString("nick_name"));
                    custom.setGender(rs.getString("gender"));
                    custom.setAddress(rs.getString("address"));
                    custom.setDelFlag(rs.getInt("del_flag"));

                    return custom;
                }
            },(page.getPageNow()-1)* page.getPageSize(),page.getPageSize());
        }

        return customs;
    }

    /**
     * 查询总行数
     * @param phone
     * @return
     */
    public int getRowCount(String phone){
        String sql ="select count(custom_id) from customer";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    /**
     * 修改客户状态
     * @param id
     * @param flag 0：删除，2：冻结
     * @return
     */
    public int updateStatus(String id,String flag){
        String sql ="update customer set del_flag=? where custom_id=?";
        return jdbcTemplate.update(sql,flag,id);
    }
}
