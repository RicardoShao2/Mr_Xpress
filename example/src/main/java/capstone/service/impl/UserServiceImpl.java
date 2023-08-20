package capstone.service.impl;


import capstone.entity.Users;
import capstone.mapper.UserMapper;
import capstone.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {




}
