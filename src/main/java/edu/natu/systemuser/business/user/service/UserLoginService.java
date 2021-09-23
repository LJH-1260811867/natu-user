package edu.natu.systemuser.business.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.natu.systemuser.business.user.dao.UserDaoImpl;
import edu.natu.systemuser.business.user.model.User;
import edu.natu.systemuser.common.token.UserToken;
import edu.natu.systemuser.common.token.UserTokenCache;
import edu.natu.systemuser.common.utils.AssertUtil;
import edu.natu.systemuser.common.utils.JsonUtil;
import edu.natu.systemuser.common.utils.JwtTokenUtil;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * @author Ljiahai
 * @des UserRoleService
 * @date 2021-09-15 15:11:25
 */
@Service
public class UserLoginService {
    @Autowired
    private UserDaoImpl userDao;

    public User login(User userReq, HttpServletResponse response) {
        User user = getLoginUser(userReq);
        String token = generateToken(user);
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
        return  user;
    }
    private User getLoginUser(User userReq) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(Objects.nonNull(userReq.getAccount()), User::getAccount, userReq.getAccount())
                .eq(Objects.nonNull(userReq.getPassword()), User::getPassword, userReq.getPassword());
        User user = userDao.getOne(query);
        checkUserReq(user);
        return user;
    }

    private void checkUserReq(User user) {
        AssertUtil.notNull(user, "账号与密码不匹配");
    }

    private String generateToken(User user) {
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setAccount(user.getAccount());
        userToken.setExpiresAt(new Date(System.currentTimeMillis() + JwtTokenUtil.EFFECTIVE_DURATION));
        String token = JwtTokenUtil.createToken(JsonUtil.objectToJson(userToken));
        UserTokenCache.LOGIN_USER_CACHE.put(token, userToken);
        return token;
    }
}
