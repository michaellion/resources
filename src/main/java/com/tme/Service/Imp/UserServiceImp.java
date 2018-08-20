package com.tme.Service.Imp;


import com.tme.Dao.UserDao;
import com.tme.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean login(@RequestParam("username") String username,@RequestParam("password") String password) {
        boolean flag = false;
        if(userDao.login(username,password)!=null){
            flag = true;
        }
        return flag;
    }
}
