package com.itheima;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    /***
     * 在执行@Test之前执行
     */
    @Before
    public void init() {
        try {
            //1.读取主配置文件(SqlMapConfig.xml)，获取配置文件的字节输入流
            InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

            //2.创建一个SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //3.构建SqlSessionFactory(会话工厂对象)
            SqlSessionFactory sqlSessionFactory = builder.build(is);

            //4.打开一个SqlSession，拥有Connection的作用
            sqlSession = sqlSessionFactory.openSession();

            //5.通过SqlSession获取对应Dao的代理类
            userMapper = sqlSession.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 查询所有
     */
    @Test
    public void testFindAll() throws Exception {
        //调用对应Dao实现数据库增删改查
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 保存用户
     */

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("张三");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("深圳");
        userMapper.saveUser(user);
    }
    /***
     * 修改用户
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUsername("张飞");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("天津");
        user.setId(68);
        userMapper.updateUser(user);
    }



    /***
     * 根据ID删除用户
     */
    @Test
    public void testDeleteUser(){
        userMapper.deleteUser(66);
    }
    /****
     * @Test执行完成之后执行
     */
    @After
    public void destroy() {
        sqlSession.commit();
        //7.关闭资源
        sqlSession.close();


    }
}
