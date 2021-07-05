package edu.natu.systemuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.natu.systemuser.dao.TUserDao;
import edu.natu.systemuser.entity.TUser;
import edu.natu.systemuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TUserDao userDao;

    @Override
    public List<TUser> list(Integer pageNo, Integer pageSize) {
        return userDao.list();
    }

    @Override
    public TUser queryUserByAccount(String account) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<TUser>();
        wrapper.eq("account", account);
        return userDao.getOne(wrapper);
    }
}
