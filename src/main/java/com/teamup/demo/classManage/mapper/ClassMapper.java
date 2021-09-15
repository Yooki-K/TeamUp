package com.teamup.demo.classManage.mapper;

import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ClassMapper {
    //todo 创建sql语句_1
    int addClass(Class c)throws SQLException;//创建班级
    List<Class> getClassByTea(String user);//根据老师用户名查询所创建班级
    List<Class> getClassByStu(String user);//根据学生用户名查询所在所有班级
    List<Student> getStuByClass(String classId);//根据班级编号查询班级成员

    Class getClassById(String classId);//根据编号查找班级
    int deleteClass(String Id);//根据编号删除班级
    Teacher getTeaByClass(String classId);//根据班级编号查找老师

}
