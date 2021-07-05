package edu.natu.systemuser.dao.impl;

import edu.natu.systemuser.entity.TUser;
import edu.natu.systemuser.mapper.TUserMapper;
import edu.natu.systemuser.dao.TUserDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-07-04
 */
@Service
public class TUserDaoImpl extends ServiceImpl<TUserMapper, TUser> implements TUserDao {

}
