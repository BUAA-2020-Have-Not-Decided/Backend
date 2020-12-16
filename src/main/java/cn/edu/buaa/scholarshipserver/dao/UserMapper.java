package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /*数据获取部分*/
    /*根据UserID获得用户*/
    User getUserByID(Integer id);
    /*根据Name获取用户*/
    User getUserByName(String name);
    /*根据Email获取用户*/
    User getUserByEmail(String email);
    /*插入数据部分*/
    /*创建一个新的用户*/
    void insertNewUser(User u);
    /*数据更新部分*/
    /*修改密码*/
    void updatePassword(@Param("ID") int user_id, @Param("Password") String new_password);
    /*修改用户的简介*/
    void updateIntro(@Param("ID") int user_id, @Param("Intro") String new_intro);
    /*修改用户名*/
    void updateName(@Param("ID") int user_id, @Param("Name") String new_name);
    /*修改用户的头像Url*/
    void updateImagePath(@Param("ID") int user_id, @Param("Path") String new_path);
    /*修改用户权限*/
    void updateIdentify(@Param("ID") int user_id, @Param("new_identify") int i);
    /*修改用户的邮箱*/
    void updateEmail(@Param("ID")int user_id, @Param("email") String email);
}