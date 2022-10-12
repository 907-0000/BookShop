package com.bookshop.dao;

import com.bookshop.bean.BookBean;
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
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insertBook(BookBean book){

        String sql = "Insert into book(book_name,type_id,book_pic,author,publish,publish_date,depict,price,storages,statu,del_flag)" +
                "values(?,?,?,?,?,?,?,?,?,0,1)";
        return jdbcTemplate.update(sql,book.getBookName(),book.getTypeId(),book.getBookPic(),
                                        book.getAuthor(),book.getPublish(),book.getPublicDate(),
                                        book.getDepict(),book.getPrice(),book.getStorages());
    }

    public List<BookBean> selectBooksPage(PageBean page) {
//        String sql = "select book_id,book_name,type_id,book_pic,author,publish,publish_date,depict,price,storages,statu,del_flag from book limit ?,? ";
        String sql = "select book_id,book_name,b.type_name,book_pic,author,publish,publish_date,depict,price,storages,statu,book.del_flag from book left join boottype b on b.type_id = book.type_id limit ?,? ";
        List<BookBean> books = new ArrayList<>();
        books = jdbcTemplate.query(sql,
                new RowMapper<BookBean>() {
                    @Override
                    public BookBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        BookBean book = new BookBean();
                        TypeBean type = new TypeBean();
                        book.setBookId(rs.getInt("book_id"));
                        book.setBookName(rs.getString("book_name"));
//                        book.setTypeId(rs.getInt("type_id"));
                        type.setTypeName(rs.getString("type_name"));
                        book.setTypeName(type.getTypeName());
                        book.setBookPic(rs.getString("book_pic"));
                        book.setAuthor(rs.getString("author"));
                        book.setPublish(rs.getString("publish"));
                        book.setPublicDate(rs.getString("Publish_date"));
                        book.setDepict(rs.getString("depict"));
                        book.setPrice(rs.getDouble("price"));
                        book.setStorages(rs.getInt("storages"));
                        book.setStatu(rs.getInt("statu"));
                        book.setDelFlag(rs.getInt("del_flag"));
                        return book;
                    }
                }, (page.getPageNow() - 1) * page.getPageSize(), page.getPageSize());
        return books;
    }

    /**
     * 获取总行数
     * @return
     */
    public int getRowCount(){
        String sql ="select count(book_id) from book";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    /**
     * 删除图书
     * @param id
     * @return
     */
    public int deleteBook(String id){
        String sql ="update book set del_flag=0 where book_id = ?";
        return jdbcTemplate.update(sql,id);
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    public int updatebook(BookBean book){
        String sql ="update book set book_name=?,type_id=?,publish=?,publish_date=?," +
                "price=?,storages=?,author=?,depict=? where book_id=?";
        return jdbcTemplate.update(sql,book.getBookName(),book.getTypeId(),book.getPublish(),
                                        book.getPublicDate(),book.getPrice(),book.getStorages(),
                                        book.getAuthor(),book.getDepict(),book.getBookId()
        );
    }
}
