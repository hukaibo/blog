package xdml.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import xdml.eneity.User;
import xdml.service.UserService;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    @Inject
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService=userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    @ResponseBody
    public  Object auth() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser=userService.getUserByUsername(userName);

        if (loggedInUser==null) {
            return new Result("ok", "用户没有登录", false);
        } else {
            return new Result("ok", null, true, loggedInUser);
            // return new Result("ok","用户没有登录",false);
        }
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody Map<String,Object> usernameAndPassword){
         String username=usernameAndPassword.get("username").toString();
         String password=usernameAndPassword.get("password").toString();

         UserDetails userDetails;
         try {
              userDetails = userService.loadUserByUsername(username);
         }catch (UsernameNotFoundException e){
                return new Result("fail","用户名不存在",false);
         }
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

       try {
           authenticationManager.authenticate(token);
           //把用户信息保存在一个地方
           //Cookies
           SecurityContextHolder.getContext().setAuthentication(token);
           return new Result("ok","登录成功",true,userService.getUserByUsername(username));
       }catch (BadCredentialsException e){
         return new Result("false","密码不正确",false);
       }
    }
    private static class Result{
        String status;
        String msg;
        boolean isLogin;
        Object data;

        public Result(String status, String msg, boolean isLogin) {
              this(status,msg,isLogin,null);
        }
        public Result(String status, String msg, boolean isLogin, Object data) {
            this.status = status;
            this.msg = msg;
            this.isLogin = isLogin;
            this.data=data;

        }

        public String getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public boolean isLogin() {
            return isLogin;
        }
        public Object getData(){ return data;}
    }
}
