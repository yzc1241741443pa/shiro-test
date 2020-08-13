package me.injoker;

import me.injoker.mapper.UserMapper;
import me.injoker.service.UserService;
import me.injoker.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroTestApplicationTests {


    @Autowired
    private UserServiceImpl userService;
    @Test
    void contextLoads() {

        System.out.println(userService.queryUser("yzc"));

    }

}
