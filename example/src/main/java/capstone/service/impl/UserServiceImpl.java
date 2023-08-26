package capstone.service.impl;


import capstone.entity.Users;
import capstone.mapper.UserMapper;
import capstone.service.UserService;
import capstone.utils.GenerateTool;
import capstone.utils.MailClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private GenerateTool generateTool;

    @Override
    public String sign_up_1(Users users) {

        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getEmail,users.getEmail());
        Users users1= this.getOne(queryWrapper);
        if(users1!=null)
            return null;
        //如果邮箱不存在于数据库中，返回0，表示已经成功发送邮箱验证邮件，需要查收。
        String veriCode=generateTool.generateVerificationCode();
        mailClient.sendMail(users.getEmail(), "Verify your email",veriCode);

        return veriCode;

    }

    @Override
    public void sign_up_2(Users users,String veriCode) {
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
