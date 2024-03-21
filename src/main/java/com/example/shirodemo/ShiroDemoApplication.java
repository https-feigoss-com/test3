package com.example.shirodemo;

//import com.jd.security.configsec.spring.config.JDSecurityPropertyCleanService;
//import com.jd.security.configsec.spring.config.JDSecurityPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
/*@PropertySources(
    value = {
        @PropertySource(value = {"classpath:important.properties"}, encoding = "utf-8", factory = JDSecurityPropertySourceFactory.class),
    }
)
@Import(JDSecurityPropertyCleanService.class)*/
@EnableHystrixDashboard
@SpringBootApplication
public class ShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoApplication.class, args);
    }

}
