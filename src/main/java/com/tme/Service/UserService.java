package com.tme.Service;

import com.tme.Bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
@Service
public interface UserService {
    boolean login(@RequestParam("username") String username, @RequestParam("password") String password);
}
