package com.bookshop;

import com.bookshop.bean.AdminBean;
import com.bookshop.dao.UserDao;
import com.bookshop.util.CaptchaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.DigestUtils;

import javax.jws.soap.SOAPBinding;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class BookshopApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads3(){
        String s = DigestUtils.md5DigestAsHex("123".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);//202cb962ac59075b964b07152d234b70
    }

    @Test
    void contextLoads2(){
     AdminBean admin=userDao.selectByAccountAndPass("guanli","123");
        System.out.println(admin);
    }

    @Test
    void contextLoads() {
        CaptchaUtil cu = new CaptchaUtil(6,100,30);
        System.out.println(cu.generatorCodeString());
        cu.generatorCodeImage(cu.generatorCodeString(),true);
    }

}
