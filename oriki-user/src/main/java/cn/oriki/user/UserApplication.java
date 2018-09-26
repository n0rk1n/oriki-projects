package cn.oriki.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * User Application
 *
 * @author oriki.wang
 */
@SpringBootApplication
@ComponentScan(value = {
        "cn.oriki.user.controller",
        "cn.oriki.user.service",
        "cn.oriki.user.dao.repository"
})
@EnableEurekaClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
