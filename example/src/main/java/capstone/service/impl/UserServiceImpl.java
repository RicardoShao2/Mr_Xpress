package capstone.service.impl;


import capstone.entity.Users;
import capstone.mapper.UserMapper;
import capstone.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {


    @Override
    public int sign_up_1(Users users) {
        //返回值设置为int，几种情况
        //邮箱如果已经存在于数据库，返回1
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getEmail,users.getEmail());
        Users users1= this.getOne(queryWrapper);
        if(users1!=null)
            return 1;
        //如果邮箱不存在于数据库中，返回0，表示已经成功发送邮箱验证邮件，需要查收。
        return 0;

    }

    @Override
    public void sign_up_2(Users users) {

        users.setCreateTime(LocalDateTime.now());
        //设置用户的账户状态
        users.setStatus(1);
        String oldPsw = users.getPassword();
        String newPsw = DigestUtils.md5DigestAsHex(oldPsw.getBytes());
        users.setPassword(newPsw);
        this.save(users);

        return;
    }
}
