package com.pan.test;

import com.pan.mapper.BrandMapper;
import com.pan.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {


    @Test
    public void testSelectAll() throws IOException {
        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        //5.释放资源
        sqlSession.close();

    }

    @Test
    public void testSelectById() throws IOException {
        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法
        Brand brand = brandMapper.selectById(1);
        System.out.println(brand);

        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testSelectByCondition() throws IOException {

        String companyName = "%" + "华为" + "%";
        String brandName = "%" + "华为" + "%";

//        Brand brand = new Brand();
//        brand.setStatus(1);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);


        Map map = new HashMap();
//        map.put("status", 1);
        map.put("companyName", companyName);
//        map.put("brandName", brandName);


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

//        List<Brand> brands = brandMapper.selectByCondition(1,companyName,brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);

        //5.释放资源
        sqlSession.close();

    }

    @Test
    public void testSelectByConditionSingle() throws IOException {

        String companyName = "%" + "华为" + "%";
        String brandName = "%" + "华为" + "%";

        //封装对象
        Brand brand = new Brand();
        //brand.setStatus(1);
        brand.setCompanyName(companyName);
        //brand.setBrandName(brandName);


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

//        List<Brand> brands = brandMapper.selectByCondition(1,companyName,brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        List<Brand> brands = brandMapper.selectByConditionSingle(brand);

        System.out.println(brands);

        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testAdd() throws IOException {

        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        int ordered = 100;
        String description = "手机中的战斗机";


        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setOrdered(ordered);
        brand.setDescription(description);


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

//        List<Brand> brands = brandMapper.selectByCondition(1,companyName,brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        brandMapper.add(brand);

        //提交事务
//        sqlSession.commit();


        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testAdd2() throws IOException {

        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        int ordered = 100;
        String description = "手机中的战斗机";


        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setOrdered(ordered);
        brand.setDescription(description);


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

//        List<Brand> brands = brandMapper.selectByCondition(1,companyName,brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        brandMapper.add(brand);

        Integer id = brand.getId();

        System.out.println(id);


        //提交事务
//        sqlSession.commit();


        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testUpdate() throws IOException {

        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        int ordered = 200;
        String description = "波导手机,手机中的战斗机";

        int id = 5;


        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setOrdered(ordered);
//        brand.setDescription(description);
        brand.setId(id);


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

        int count = brandMapper.update(brand);

        System.out.println(count);


        //提交事务
//        sqlSession.commit();


        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testDeleteById() throws IOException {


        int id = 5;


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

        brandMapper.deleteById(id);


        //提交事务
//        sqlSession.commit();


        //5.释放资源
        sqlSession.close();

    }


    @Test
    public void testDeleteByIds() throws IOException {


        int[] ids = {5, 7, 8};


        //1.获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        //4,执行方法

        brandMapper.deleteByIds(ids);


        //提交事务
//        sqlSession.commit();


        //5.释放资源
        sqlSession.close();

    }


}
