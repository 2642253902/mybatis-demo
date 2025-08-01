package com.pan.mapper;

import com.pan.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    @Select("select * from  tb_user where id =#{id}")
    User selectById(int id);

    /*
         MyBatis参数封装：
             * 单个参数：
                1.P0J0类型： 直接使用，属性名 和 参数占位符名称 一致
                2.Map集合： 直接使用，键名 和 参数占位符名称 一致
                3.Collection: 封装为mMap集合，可以使用@Param注解，替换map集合中默认的arg键名
                    map.put("arg0",Collection集合)
                    map.put("collection",Collection集合)
                4.List: 封装为mMap集合，可以使用@Param注解，替换map集合中默认的arg键名
                    map.put("arg0",list集合)
                    map.put("collection",list集合)
                    map.put("list",list集合)
                5.Array: 封装为mMap集合，可以使用@Param注解，替换map集合中默认的arg键名
                     map.put("arg0",数组)
                      map.put("array",数组)
                6.其他类型： 直接使用
             * 多个参数： 封装为map集合,可以使用@Param注解，替换map集合中默认的arg键名
                map.put("arg0",参数值1)
                map.put("param1",参数值1)
                map.put("param2",参数值2)
                map.put("agr1",参数值2)
                --------------------------@Param("username")
                map.put("username",参数值1)
                map.put("param1",参数值1)
                map.put("param2",参数值2)
                map.put("agr1",参数值2)

    */
    User select(@Param("username") String username, String password);

    User select(Collection collection);

}
