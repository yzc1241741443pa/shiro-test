package me.injoker.service;

import me.injoker.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User queryUser(String username);
}
