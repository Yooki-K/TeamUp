package com.teamup.demo.userManage.mapper;

import com.teamup.demo.userManage.entity.Certification;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<Student> getAllStu();
    List<Teacher> getAllTea();
    List<Student> findUsersById(@Param("list") List<String>list);
    List<Student> fuzzyMatchUsersByUser(@Param("param") String param,@Param("table")String table);
    Student findUserByUser(@Param("user")String user,@Param("table")String table)throws SQLException;//根据用户名查找用户
    int addUser(@Param("user")Student user,@Param("table") String table) throws SQLException; //注册
    int updateUser(@Param("user")String user, @Param("map") Map<String,String> map, @Param("table")String table) throws SQLException;//修改用户
    int addCertification(Certification certification) throws SQLException;//实名认证
    int operateCertification(@Param("list") List<String> list,@Param("value")String value) throws SQLException;//管理员同意、拒绝实名认证
    int updateHeadshot(@Param("user")String user,@Param("headshot")String headshot, @Param("table")String table) throws SQLException;//上传头像
    List<Certification> getCertificationByType(int type);//管理员查询实名认证申请
    Certification findCertificationByUser(@Param("user")String user);//查询认证是否通过
    Student findUserByNo(@Param("no") int no, @Param("table")String table);
}
