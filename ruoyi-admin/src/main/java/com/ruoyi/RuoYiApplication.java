package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }, scanBasePackages = { "com.ruoyi.*", "com.example.*" })
@ComponentScan({ "com.ruoyi.*", "com.example.*" })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  daily project 启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "  .-------.       ____     __        \n" +
                " //        \\\\     ||     //      \n" +
                "||          ||    ||   //        \n" +
                "||          ||    || //          \n" +
                "||          ||    ||  \\\\         \n" +
                " \\\\ _______ //    ||    \\\\       \n" +
                "    -------  ||   ||       \\\\     ");
    }
}
