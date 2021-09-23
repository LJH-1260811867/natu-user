package edu.natu.systemuser.business.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.natu.systemuser.business.user.dao.UserDaoImpl;
import edu.natu.systemuser.business.user.model.User;
import edu.natu.systemuser.common.result.CommonPageParams;
import edu.natu.systemuser.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Ljiahai
 * @des UserRoleService
 * @date 2021-09-15 15:11:25
 */
@Service
public class UserService {

   @Autowired
   private UserDaoImpl userDao;

    public IPage<User> list(CommonPageParams<User> params) {
        User req = params.getReal();
        IPage<User> pageParam = new Page<>();
        pageParam.setCurrent(params.getPageNum());
        pageParam.setSize(params.getPageSize());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().setEntity(req);
        return userDao.page(pageParam,wrapper);
    }

    public void add(User user) {
        user.setId(UUIDUtil.generateUUID());
        userDao.save(user);
    }
}
