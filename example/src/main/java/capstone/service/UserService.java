package capstone.service;

import capstone.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;


public interface UserService extends IService<Users> {

    public int sign_up_1(Users users);

    public void sign_up_2(Users users);
}
