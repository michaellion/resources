package com.tme.Controller;

import com.tme.Service.Imp.UserServiceImp;
import com.tme.Util.FileHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@MapperScan("com.tme.Dao")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    FileHelper fileHelper;

    /*@RequestMapping("/")
    public String login(){
        System.out.println("ok");
        return "login";
    }*/

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String index(@RequestParam("username") String username,@RequestParam("password")String password ){
        System.out.println(username);
        System.out.println(password);
        if(userServiceImp.login(username,password)){
            return "index";
        }
        return "error";
    }

    @RequestMapping("/")
    public String index(Model model){
        List list = fileHelper.getFiles();
        model.addAttribute("lists",list);
        return "index";
    }

}
