package com.bookshop.control;

import com.bookshop.bean.CustomBean;
import com.bookshop.bean.PageBean;
import com.bookshop.bean.ResultBean;
import com.bookshop.dao.CustomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomControl {
    @Autowired
    private CustomDao customDao;

    /**
     * 查找所有的客户
     * @return
     */
    @RequestMapping("/find_customs")
    public ResultBean findCustoms(){
        ResultBean result = new ResultBean();
        List<CustomBean> customs = customDao.selectAll();
        result.setCode(1);
        result.setData(customs);
        return result;
    }

    @RequestMapping("/find_pages")
    public ResultBean findPages(PageBean page,String phone){
        ResultBean result = new ResultBean();
        //处理page的相关信息
        page.setPageNow(page.getPageNow()==null || page.getPageNow()<1?1:page.getPageNow());
        page.setRowCount(customDao.getRowCount(null));
        page.setPageCount(page.getRowCount()%page.getPageSize()==0
                   ?page.getRowCount()/page.getPageSize()
                   :page.getRowCount()/page.getPageSize()+1);
        //pageNow处理
        page.setPageNow(page.getPageNow()>page.getPageCount()?page.getPageCount():page.getPageNow());
        List<CustomBean> customs = customDao.selectCustomsPage(page,phone);
        result.setCode(1);
        //把查询结果和分页的信息
        Map<String,Object> map = new HashMap<>();
        map.put("customs",customs);
        map.put("pages",page);
        result.setData(map);
        return result;
    }
    /**
     * 删除客户
     * @return
     */
    @RequestMapping("/drop_custom")
    public ResultBean dropCustom(String id){
        ResultBean result = new ResultBean();

        if(customDao.updateStatus(id,"0")==1){
            System.out.println(id);
            result.setCode(0);
            result.setMsg("删除成功");
        }
        return result;
    }

    /**
     * 冻结客户
     * @return
     */
    @RequestMapping("/fix_custom")
    public ResultBean fixCustom(String id){
        ResultBean result = new ResultBean();

        if(customDao.updateStatus(id,"2")==1){
            System.out.println(id);
            result.setCode(2);
            result.setMsg("冻结成功");
        }
        return result;
    }

}
