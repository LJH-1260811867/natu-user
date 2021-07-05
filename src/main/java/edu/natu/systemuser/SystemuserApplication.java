package edu.natu.systemuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.natu.systemuser.mapper")
public class SystemuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemuserApplication.class, args);
    }

}
