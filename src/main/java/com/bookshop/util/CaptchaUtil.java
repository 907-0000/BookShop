package com.bookshop.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
* 生成验证码和图片
* */
public class CaptchaUtil {
    //验证码字符数组
    private final  static char[] CODE={'0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s',
            't','u','v','w','x','y','z','A','B','D','E','F','G','H','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };

    //验证码长度
    private  int codeLen=4;

    //验证码图片宽度
    private int width=100;

    //验证码的高度
    private int height=30;
    //字体名称
    private final String[] fontName={"黑体","宋体","Courier","Arial"};

    //字体样式
    private final int[] fontStyle={Font.BOLD,Font.ITALIC | Font.BOLD};

    //验证码的字体大小
    private int fontSize=18;

    //干扰线条数
    private int line =2;

    public CaptchaUtil() {}

    /**
     *
     * @param codeLen 验证码长度
     * @param width 验证码宽度
     * @param height 验证码高度
     */
    public CaptchaUtil(int codeLen, int width, int height) {
        this.codeLen = codeLen;
        this.width = width;
        this.height = height;
    }
    public CaptchaUtil(int codeLen, int width, int height, int fontSize) {
        this.codeLen = codeLen;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
    }

    /**
     * 生成验证码字符串
     * @return 验证码字符串
     */
    public String generatorCodeString(){
        StringBuffer strCode = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < this.codeLen; i++) {
            int temp=random.nextInt(CODE.length);
            strCode.append(CODE[temp]);
        }
        return  strCode.toString();
    }

    /**
     *
     * @param code  验证码字符串
     * @param drawLine 是否有干扰线
     * @return 返回 图片对象
     */
    public BufferedImage generatorCodeImage(String code, boolean drawLine){
        //创建图片缓存对象
        BufferedImage codeImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

        //获得画笔，设置背景色，填充
        Graphics g=codeImage.getGraphics();
        g.setColor(randomColor());
//        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,width,height);

        //创建随机数
        Random ran = new Random();

        //是否画干扰线
        if (drawLine){
            for (int i= 0; i <line ; i++) {
                int x1=ran.nextInt(width);
                int y1=ran.nextInt(height);
                int x2=ran.nextInt(width);
                int y2=ran.nextInt(height);
                g.setColor(randomColor());
                g.drawLine(x1,y1,x2,y2);
            }
        }


        //画图、随机字符串、循环
        for (int i = 0; i < code.length(); i++) {
            //设置字体，名、样式、大小
            g.setFont(new Font(fontName[ran.nextInt(fontName.length)],
                    fontStyle[ran.nextInt(fontStyle.length)],fontSize));

            //设置随机颜色
            g.setColor(randomColor());
            //画字符
            g.drawString(code.charAt(i)+"",i * fontSize + 10,fontSize + 5);

        }
        //关闭画笔
        g.dispose();
        return codeImage;
    }

    private Color randomColor(){
        Random ran = new Random();
        return new Color(ran.nextInt(230),
                ran.nextInt(230),
                ran.nextInt(230));
    }
}
