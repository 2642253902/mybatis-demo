package com.pan;


import com.pan.mapper.UserMapper;
import com.pan.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//MyBatis 代理开发
public class MyBatisDemo2 {

    public static void main(String[] args) throws IOException {
        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.执行sql语句
        //List<User> users = sqlSession.selectList("test.selectAll");
        //sqlSession.selectOne();
        //3.1 获取UserMapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.selectAll();


        System.out.println(users);

        //4.释放资源
        sqlSession.close();

    }

}
