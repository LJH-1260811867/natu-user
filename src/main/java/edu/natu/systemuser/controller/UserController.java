package edu.natu.systemuser.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.natu.systemuser.common.result.CommonResult;
import edu.natu.systemuser.entity.TUser;
import edu.natu.systemuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public CommonResult<List<TUser>> getUserList(@RequestParam(name = "pageNo") Integer pageNo, @RequestParam(name = "pageSize") Integer pageSize) {
        List<TUser> dataList = userService.list(pageNo, pageSize);
        return CommonResult.success(dataList);
    }

    @GetMapping("/info")
    public  CommonResult<TUser> queryUserByAccount(@RequestParam(name = "account") String account) {
        return CommonResult.success(userService.queryUserByAccount(account));
    }
}
