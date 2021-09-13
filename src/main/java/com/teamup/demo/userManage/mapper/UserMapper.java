package com.teamup.demo.userManage.mapper;

import com.teamup.demo.userManage.entity.Certification;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserMapper {
    List<Student> getAllStu();
    List<Teacher> getAllTea();
    Student findUserByUser(@Param("user")String user,@Param("table")String table)throws SQLException;//根据用户名查找用户
    int addUser(@Param("user")Student user,@Param("table") String table) throws SQLException; //注册
    int updatePwdByUser(@Param("user")String user,@Param("pwd")String pwd,@Param("table")String table) throws SQLException;//修改密码
    int addCertification(Certification certification) throws SQLException;//实名认证
    List<Certification> getCertificationByType(int type);//管理员查询实名认证申请
    int agreeCertification(List<String> userList) throws SQLException;//管理员同意实名认证
}
