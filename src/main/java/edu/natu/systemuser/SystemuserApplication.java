package edu.natu.systemuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LJH
 */
@SpringBootApplication
@MapperScan("edu.natu.systemuser.business.*.mapper")

public class SystemuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemuserApplication.class, args);
    }

}
