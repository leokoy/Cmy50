package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {


    List<User> findAll();

    /**
     * 添加用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /***
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);


}
