package com.bookshop.control;

import com.bookshop.bean.BookBean;
import com.bookshop.bean.PageBean;
import com.bookshop.bean.ResultBean;
import com.bookshop.bean.TypeBean;
import com.bookshop.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookControl {
    @Autowired
    private BookDao bookDao;

    /**
     * 添加图书
     * @param book
     * @return
     */
    @RequestMapping("/add_book")
    public ResultBean addBook(BookBean book){
        ResultBean result = new ResultBean();
        if (bookDao.insertBook(book)==1){
            result.setCode(1);
            result.setMsg("添加图书成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("添加图书失败");
        return result;
    }

    @RequestMapping("/get_books_pages")
    public ResultBean getBooksPage(PageBean page){
        ResultBean result = new ResultBean();
        //处理page的相关信息
        page.setPageNow(page.getPageNow()==null || page.getPageNow()<1?1:page.getPageNow());
        page.setRowCount(bookDao.getRowCount());
        page.setPageCount(page.getRowCount()%page.getPageSize()==0
                ?page.getRowCount()/page.getPageSize()
                :page.getRowCount()/page.getPageSize()+1);
        //pageNow处理
        page.setPageNow(page.getPageNow()>page.getPageCount()?page.getPageCount():page.getPageNow());

        //查询数据
        List<BookBean> books=bookDao.selectBooksPage(page);
        //把查询结果和分页的信息
        Map<String,Object> map = new HashMap<>();
        map.put("books",books);
        map.put("pages",page);

        result.setCode(1);
        result.setData(map);
        return result;
    }

    @RequestMapping("/drop_book")
    public ResultBean dropBook(String id){
        ResultBean result = new ResultBean();
        if(bookDao.deleteBook(id)==1){
        result.setCode(1);
        result.setMsg("删除图书成功");
        return result;
        }
        result.setCode(-1);
        result.setMsg("删除图书失败");
        return result;
    }

    @RequestMapping("/edit_book")
    public ResultBean editBook(BookBean book){
        ResultBean result = new ResultBean();
        if(bookDao.updatebook(book)==1){
            result.setCode(1);
            result.setMsg("修改图书成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("修改图书失败");
        return result;
    }

}
