package com.teamup.demo.classManage.mapper;

import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.entity.ClassInf;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ClassMapper {
    int addClass(Class c)throws SQLException;//创建班级
    List<Class> getClassByTea(String user);//根据老师用户名查询所创建班级
    List<Class> getClassByStu(String user);//根据学生用户名查询所创建班级
    List<Student> getStuByClass(int classId);//根据班级编号查询班级成员
    Class getClassById(int classId);//根据编号查找班级
    int deleteClass(List<String> list)throws SQLException;//根据编号删除班级
    Teacher getTeaByClass(int classId);//根据班级编号查找老师
    int updateClassInf(ClassInf classInf)throws SQLException;//更改当前班级信息记录，userId由null改为xx
    int joinClass(@Param("list")List<ClassInf>list)throws SQLException;//多人加入班级
    String codeIsExist(String invCode);//查询某一邀请码是否存在 返回班级编号
}
