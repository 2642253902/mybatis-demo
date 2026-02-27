# MyBatis 入门示例项目

本项目是一个完整的 MyBatis 入门学习项目，涵盖了 MyBatis 的核心功能和常用开发模式，适合初学者系统学习 MyBatis 框架的使用。

## 📚 项目简介

本项目通过两个实际业务场景（用户管理和品牌管理）演示了 MyBatis 的核心特性，包括：
- MyBatis 基础配置和使用
- 传统开发方式和代理开发方式
- 完整的 CRUD 操作
- 动态 SQL 的使用
- 参数传递的多种方式
- ResultMap 结果映射
- 注解式开发

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| JDK | 17 | Java 开发环境 |
| MyBatis | 3.5.6 | 持久层框架 |
| MySQL | 5.7.26 | 数据库 |
| MySQL Connector | 5.1.21 | MySQL 驱动 |
| JUnit | 4.13.2 | 单元测试框架 |
| Logback | 1.2.3 | 日志框架 |
| Maven | - | 项目管理工具 |

## 📁 项目结构

```
mybatis-demo/
├── db/
│   └── mybatis.sql                          # 数据库建表脚本和测试数据
├── src/
│   ├── main/
│   │   ├── java/com/pan/
│   │   │   ├── MyBatisDemo.java            # 传统方式示例（使用 selectList）
│   │   │   ├── MyBatisDemo2.java           # 代理开发方式示例
│   │   │   ├── mapper/
│   │   │   │   ├── UserMapper.java         # 用户 Mapper 接口
│   │   │   │   └── BrandMapper.java        # 品牌 Mapper 接口
│   │   │   └── pojo/
│   │   │       ├── User.java               # 用户实体类
│   │   │       └── Brand.java              # 品牌实体类
│   │   └── resources/
│   │       ├── mybatis-config.xml          # MyBatis 核心配置文件
│   │       ├── logback.xml                 # 日志配置文件
│   │       └── com/pan/mapper/
│   │           ├── UserMapper.xml          # 用户 SQL 映射文件
│   │           └── BrandMapper.xml         # 品牌 SQL 映射文件
│   └── test/
│       └── java/com/pan/test/
│           ├── MyBatisTest.java            # 品牌功能测试类
│           └── UserMapperTest.java         # 用户功能测试类
└── pom.xml                                  # Maven 依赖配置
```

## 🚀 快速开始

### 1. 环境准备

- 安装 JDK 17 或以上版本
- 安装 MySQL 5.7 或以上版本
- 安装 Maven 3.x

### 2. 创建数据库

执行 `db/mybatis.sql` 文件创建数据库和表：

```bash
mysql -u root -p < mybatis-demo/db/mybatis.sql
```

或者手动创建数据库：

```sql
CREATE DATABASE mybatis;
USE mybatis;
-- 然后执行 mybatis.sql 中的建表语句
```

### 3. 配置数据库连接

修改 `src/main/resources/mybatis-config.xml` 中的数据库连接信息：

```xml
<property name="username" value="your_username"/>
<property name="password" value="your_password"/>
```

### 4. 编译项目

```bash
cd mybatis-demo
mvn clean compile
```

### 5. 运行示例

#### 方式一：运行 Main 方法

```bash
# 传统方式
mvn exec:java -Dexec.mainClass="com.pan.MyBatisDemo"

# 代理开发方式
mvn exec:java -Dexec.mainClass="com.pan.MyBatisDemo2"
```

#### 方式二：运行单元测试

```bash
mvn test
```

## ✨ 功能特性

### 1. 两种开发方式

#### 传统方式（MyBatisDemo.java）
```java
// 直接使用 SqlSession 执行 SQL
List<User> users = sqlSession.selectList("test.selectAll");
```

#### 代理开发方式（MyBatisDemo2.java）
```java
// 通过 Mapper 代理对象执行 SQL（推荐）
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
List<User> users = mapper.selectAll();
```

### 2. 完整的 CRUD 操作

#### 查询功能
- **查询所有**：`selectAll()`
- **根据 ID 查询**：`selectById(int id)`
- **多条件查询**：`selectByCondition(Map map)`
- **单条件动态查询**：`selectByConditionSingle(Brand brand)`

#### 新增功能
- **添加数据**：`add(Brand brand)`
- 支持主键回填

#### 修改功能
- **修改数据**：`update(Brand brand)`
- 支持动态 SQL，只更新非空字段

#### 删除功能
- **根据 ID 删除**：`deleteById(int id)`
- **批量删除**：`deleteByIds(int[] ids)`

### 3. 动态 SQL 示例

#### if 标签 - 动态条件查询
```xml
<select id="selectByCondition" resultMap="brandResultMap">
    select * from tb_brand
    <where>
        <if test="status != null">
            and status = #{status}
        </if>
        <if test="companyName != null and companyName != ''">
            and company_name like #{companyName}
        </if>
        <if test="brandName != null and brandName != ''">
            and brand_name like #{brandName}
        </if>
    </where>
</select>
```

#### choose 标签 - 单条件选择查询
```xml
<select id="selectByConditionSingle" resultMap="brandResultMap">
    select * from tb_brand
    <where>
        <choose>
            <when test="status != null">
                status = #{status}
            </when>
            <when test="companyName != null and companyName != ''">
                company_name like #{companyName}
            </when>
            <otherwise>
                1=1
            </otherwise>
        </choose>
    </where>
</select>
```

#### set 标签 - 动态更新
```xml
<update id="update">
    update tb_brand
    <set>
        <if test="brandName != null and brandName != ''">
            brand_name = #{brandName},
        </if>
        <if test="companyName != null and companyName != ''">
            company_name = #{companyName},
        </if>
    </set>
    where id = #{id}
</update>
```

#### foreach 标签 - 批量删除
```xml
<delete id="deleteByIds">
    delete from tb_brand where id in
    <foreach collection="ids" item="id" separator="," open="(" close=")">
        #{id}
    </foreach>
</delete>
```

### 4. 参数传递方式

MyBatis 支持多种参数传递方式：

#### 单个参数
```java
// 1. POJO 类型
Brand selectByPojo(Brand brand);

// 2. Map 集合
List<Brand> selectByCondition(Map map);

// 3. 简单类型
User selectById(int id);
```

#### 多个参数（使用 @Param 注解）
```java
User select(@Param("username") String username, 
            @Param("password") String password);
```

#### 数组参数
```java
void deleteByIds(@Param("ids") int[] ids);
```

### 5. 注解式开发

除了 XML 映射文件，MyBatis 也支持注解方式：

```java
@Select("select * from tb_user where id = #{id}")
User selectById(int id);
```

### 6. ResultMap 映射

解决数据库字段名与实体类属性名不一致的问题：

```xml
<resultMap id="brandResultMap" type="brand">
    <result column="brand_name" property="brandName"/>
    <result column="company_name" property="companyName"/>
</resultMap>
```

## 📊 数据库表结构

### tb_brand（品牌表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 主键ID |
| brand_name | varchar(20) | 品牌名称 |
| company_name | varchar(20) | 企业名称 |
| ordered | int | 排序字段 |
| description | varchar(100) | 描述信息 |
| status | int | 状态：0-禁用，1-启用 |

### tb_user（用户表）
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 主键ID |
| username | varchar(20) | 用户名 |
| password | varchar(20) | 密码 |
| gender | char(1) | 性别 |
| addr | varchar(30) | 地址 |

## 💡 核心知识点

### 1. SqlSessionFactory
- 使用建造者模式创建
- 是 MyBatis 的核心对象，用于创建 SqlSession
- 整个应用中只需要创建一次（单例模式）

### 2. SqlSession
- 用于执行 SQL 语句
- 每次数据库操作都需要创建新的 SqlSession
- 使用完毕后需要关闭释放资源

### 3. Mapper 代理
- 推荐使用的开发方式
- 接口和 XML 映射文件需要满足以下规则：
  - 名称相同
  - 在同一目录下
  - namespace 值为接口的全限定名
  - 方法名与 SQL 的 id 一致

### 4. 参数占位符
- `#{}` - 占位符，防止 SQL 注入（推荐）
- `${}` - 字符串拼接，存在 SQL 注入风险（谨慎使用）

### 5. 事务管理
```java
// 开启自动提交
SqlSession sqlSession = sqlSessionFactory.openSession(true);

// 手动提交
sqlSession.commit();

// 回滚
sqlSession.rollback();
```

## 🔧 常见问题

### 1. 中文乱码问题
在数据库连接 URL 中添加编码参数：
```xml
<property name="url" value="jdbc:mysql:///mybatis?useSSL=false&amp;characterEncoding=utf8"/>
```

### 2. 参数为 null 导致条件失效
在动态 SQL 中判断参数是否为空：
```xml
<if test="brandName != null and brandName != ''">
    and brand_name like #{brandName}
</if>
```

### 3. 主键回填
使用 `useGeneratedKeys` 和 `keyProperty` 属性：
```xml
<insert id="add" useGeneratedKeys="true" keyProperty="id">
    insert into tb_brand values (null, #{brandName}, ...)
</insert>
```

### 4. 数据库字段名与实体类属性名不一致
- 方法一：SQL 中使用别名
- 方法二：使用 ResultMap 映射（推荐）

## 📖 学习建议

1. **先理解传统方式**：通过 MyBatisDemo.java 理解 MyBatis 的工作流程
2. **掌握代理开发**：学习 MyBatisDemo2.java 的代理模式开发
3. **熟悉 CRUD 操作**：通过测试类学习各类数据库操作
4. **深入动态 SQL**：掌握 if、choose、foreach 等标签的使用
5. **理解参数传递**：了解不同参数类型的封装方式

## 📚 学习资源

- 项目视频教程：[MyBatis 入门教程](https://www.bilibili.com/video/BV1MT4y1k7wZ/)
- MyBatis 官方文档：[https://mybatis.org/mybatis-3/](https://mybatis.org/mybatis-3/)
- MyBatis 中文文档：[https://mybatis.org/mybatis-3/zh/](https://mybatis.org/mybatis-3/zh/)

## 📝 开发规范

1. 实体类中基本数据类型建议使用包装类型
2. Mapper 接口和 XML 文件要按照规范放置
3. 增删改操作需要手动提交事务（除非开启自动提交）
4. 使用完 SqlSession 后必须关闭资源
5. 优先使用 `#{}` 占位符，避免 SQL 注入
