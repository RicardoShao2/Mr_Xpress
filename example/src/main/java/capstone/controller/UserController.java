package capstone.controller;

import capstone.common.R;
import capstone.entity.Users;
import capstone.service.UserService;
import capstone.utils.GenerateTool;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    @PostMapping ("/login")
    public R<String> login(HttpServletRequest request, @RequestBody Users user){
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getEmail,user.getEmail());
        Users userFind = userService.getOne(queryWrapper);

        if(userFind==null)
            return R.error("Wrong email");

        String newPsw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if(!userFind.getPassword().equals(newPsw))
            return R.error("Wrong password");

        request.getSession().setAttribute("user",user.getId());
        return R.success("login success");

    }

    /*
    *
    *
    * 注册方法上半段
    *
    * */
    @PostMapping("/sign_up_1")
    public R<String> sign_up_1(@RequestBody Users user,HttpServletRequest request){
        String veriCode= userService.sign_up_1(user);
        if(veriCode == null)
            return R.error("Email exists");
        else {
            HttpSession session = request.getSession();
            session.setAttribute("verifyCode",veriCode);
            session.setAttribute("codeCreateTime",System.currentTimeMillis());
            return R.success("Successfully send verify email, please check");
        }
    }


    /*
    *
    * 注册方法下半段
    *
    * */
    @PostMapping("/sign_up_2/{veriCode}")
    public R<String> sign_up_2(@RequestBody Users user,@PathVariable String veriCode,HttpServletRequest request){
        long creationTime = (Long) request.getSession().getAttribute("codeCreateTime");
        if(veriCode.equals(request.getSession().getAttribute("verifyCode")) && System.currentTimeMillis()- creationTime<GenerateTool.CODEVALIDTIME ) {
            userService.sign_up_2(user, veriCode);
            return R.success("sign up success");
        }
        else
            return R.error("sign up failed");


    }


}
