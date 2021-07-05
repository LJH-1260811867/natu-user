package edu.natu.systemuser.service;

import edu.natu.systemuser.entity.TUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<TUser> list(Integer pageNo, Integer pageSize);
    TUser queryUserByAccount(String account);
}
