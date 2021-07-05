package edu.natu.systemuser.dao;

import edu.natu.systemuser.entity.TUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TUserDaoTest {
    @Autowired
    private TUserDao userDao;

    @Test
    public void queryAll() {
        List<TUser> userList = userDao.list();
        System.out.println(userList);
    }
}
