package capstone.controller;

import capstone.common.R;
import capstone.entity.Users;
import capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /*
    *
    * 登录方法
    *
    * */
    public R<Users> login(HttpServletRequest request, @RequestBody Users user){
        return null;
    }

    /*
    *
    *
    * 注册方法上半段
    *
    * */
    @PostMapping
    public R<String> sign_up_1(@RequestBody Users user){
        return null;
    }


    /*
    *
    * 注册方法下半段
    *
    * */
    @PostMapping
    public R<String> sign_up_2(@RequestBody Users user){
        return null;
    }


}
