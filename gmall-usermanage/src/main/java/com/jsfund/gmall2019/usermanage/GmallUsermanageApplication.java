package com.jsfund.gmall2019.usermanage;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jsfund.gmall2019.usermanage.mapper")
@ComponentScan(basePackages = "com.jsfund.gmall2019")
//@EnableDubbo(scanBasePackages = "com.jsfund.gmall2019")
public class GmallUsermanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUsermanageApplication.class, args);
    }

}
