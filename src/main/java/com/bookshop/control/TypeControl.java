package com.bookshop.control;

import com.bookshop.bean.PageBean;
import com.bookshop.bean.ResultBean;
import com.bookshop.bean.TypeBean;
import com.bookshop.dao.TypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TypeControl {
    @Autowired
    private TypeDao typeDao;

    @RequestMapping("/add_type")
    public ResultBean addType(TypeBean type){
        ResultBean result = new ResultBean();
        if(typeDao.insertType(type)==1){
            //成功
            result.setCode(1);
            result.setMsg("类型添加成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("类型添加失败");
        return result;
    }

    /**
     *实现分页查询
     * @param page
     * @return
     */
    @RequestMapping("/find_types_page")
    public ResultBean findTypesPage(PageBean page){
        ResultBean result = new ResultBean();
        //处理page的相关信息
        page.setPageNow(page.getPageNow()==null || page.getPageNow()<1?1:page.getPageNow());
        page.setRowCount(typeDao.getRowCount());
        page.setPageCount(page.getRowCount()%page.getPageSize()==0
                ?page.getRowCount()/page.getPageSize()
                :page.getRowCount()/page.getPageSize()+1);
        //pageNow处理
        page.setPageNow(page.getPageNow()>page.getPageCount()?page.getPageCount():page.getPageNow());

        //查询数据
        List<TypeBean> types=typeDao.selectTypePage(page);
        //把查询结果和分页的信息
        Map<String,Object> map = new HashMap<>();
        map.put("types",types);
        map.put("pages",page);

        result.setCode(1);
        result.setData(map);
        return result;
    }

    /**
     * 删除图书类型 . 问题： 删除类型前，先把这个类型的图书外键修改为null.
     * @param id
     * @return
     */
    @RequestMapping("/drop_type")
    public ResultBean dropType(String id){
        //将这种类型的图书的外键，修改为null
        //删除图书
        ResultBean result = new ResultBean();
        if (typeDao.deleteType(id)==1){
            result.setCode(1);
            result.setMsg("类型删除成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("类型删除失败");
        return result;
    }

    /**
     * 修改类型
     * @param type
     * @return
     */
    @RequestMapping("/modify_type")
    public ResultBean modifyType(TypeBean type){
        ResultBean result = new ResultBean();
        if (typeDao.updateType(type)==1){
            result.setCode(1);
            result.setMsg("类型修改成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("类型修改失败");
        return result;
    }

    /**
     * 获得所有的图书类型
     * @return
     */
    @RequestMapping("get_all_types")
        public ResultBean getAllTypes(){
        List<TypeBean> types=typeDao.selectTypeAll();
        ResultBean result = new ResultBean();

        types.add(0,new TypeBean(-1,"选择图书类型"));

        result.setCode(1);
        result.setData(types);
        return result;
}
}
