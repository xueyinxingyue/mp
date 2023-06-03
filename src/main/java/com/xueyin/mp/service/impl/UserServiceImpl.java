package com.xueyin.mp.service.impl;

import com.xueyin.mp.entity.User;
import com.xueyin.mp.mapper.UserMapper;
import com.xueyin.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueyin
 * @since 2023-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
