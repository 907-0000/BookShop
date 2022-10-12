package com.bookshop.control;

import com.bookshop.bean.AdminBean;
import com.bookshop.bean.ResultBean;
import com.bookshop.dao.UserDao;
import com.bookshop.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class UserControl {
    @Autowired
    private UserDao userDao;
    /**
     * 给浏览器响应验证码
     * @param session
     * @param response
     */
    @RequestMapping("/captcha")
    public void captcha(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        CaptchaUtil captchaUtil = new CaptchaUtil(4,100,35,20);
        String code =captchaUtil.generatorCodeString();

        //保存到session
        session.setAttribute("code",code);
        System.out.println("session:,id"+session.getId()+",code="+code);

        //生成验证码图片
        BufferedImage image= captchaUtil.generatorCodeImage(code,true);

        //响应给浏览器
        ImageIO.write(image,"gif",response.getOutputStream());

    }

    /**
     *
     * @param account 账号
     * @param pass 密码
     * @param code 验证码
     * @param remember 记住密码
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public ResultBean login(String account, String pass, String code, String remember, HttpSession session){
        ResultBean result = new ResultBean();
        //比较验证码是否正确
        String oldCode = (String) session.getAttribute("code");
        System.out.println(oldCode);
        if (!code.equalsIgnoreCase(oldCode)){//验证码不正确
            result.setCode(-1);
            result.setMsg("验证码输入有误");
            return result;
        }
        System.out.println(account);
        System.out.println(pass);
        //查询数据库
        AdminBean admin = userDao.selectByAccountAndPass(account,pass);
        System.out.println(admin);
        if (admin == null){//账号密码不正确
            result.setCode(-1);
            result.setMsg("账号或密码不正确，登陆失败");
            return result;
        }
        if (admin.getDelFlag()==0){//用户是被删除的
            result.setCode(-1);
            result.setMsg("账号已被删除，登陆失败");
            return result;
        }
        //登录成功
        //保存登陆成功的用户
        session.setAttribute("user",admin);
        //设置返回的数据
        result.setCode(0);
        result.setMsg("登陆成功");
        result.setData(admin);
        return result;
    }

    /**
     * 退出登录
     * @param session
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();//强制session失效

    }

    /**
     * 验证原始密码是否正确
     * @param pass
     * @param id
     * @return
     */
    @RequestMapping("/check_old_pass")
    public ResultBean checkOldPass(String pass,String id){
        AdminBean admin = userDao.selectById(id);
        ResultBean result = new ResultBean();
        if(admin==null){
            //id不存在
            result.setCode(-1);
            result.setMsg("管理员不存在");
            return result;
        }
        //页面密码和数据库查的密码是否一致
        String pass2 = DigestUtils.md5DigestAsHex(pass.getBytes(StandardCharsets.UTF_8));
        if (!pass2.equals(admin.getPass())){//pass是没有加密的
            result.setCode(-1);
            result.setMsg("原始密码不正确");
            return result;
        }
        result.setCode(0);
        result.setMsg("原始密码正确");
        return result;
    }

    /**
     *
     * @param id
     * @param pass
     * @return
     */
    @RequestMapping("/modify_pass")
    public  ResultBean modifyPass(String id,String pass){
        ResultBean result = new ResultBean();
        if (userDao.updatePass(id,pass)==1){//成功
            result.setCode(1);
            result.setMsg("密码修改成功");
            return result;
        }
        result.setCode(-1);
        result.setMsg("密码修改失败");
        return result;

    }
@RequestMapping("/modifyAdmin")
    public ResultBean modifyAdmin(AdminBean admin){
    ResultBean result = new ResultBean();
    if (userDao.updateAdmin(admin)==1){
            //变更成功
            result.setCode(1);
            result.setMsg("个人信息修改成功");
            return result;
        }else{
        result.setCode(-1);
        result.setMsg("个人信息修改失败");
        return result;
        }
    }
}
