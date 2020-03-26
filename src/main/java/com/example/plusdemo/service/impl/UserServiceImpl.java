package com.example.plusdemo.service.impl;

import com.example.plusdemo.entity.User;
import com.example.plusdemo.mapper.UserMapper;
import com.example.plusdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author VingKing
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
