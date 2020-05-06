package icu.thyself.cloudlesson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Aaron
 * @date 2020/5/2
 */
@SpringBootApplication
@MapperScan("icu.thyself.cloudlesson.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
