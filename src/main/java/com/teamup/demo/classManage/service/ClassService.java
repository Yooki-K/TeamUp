package com.teamup.demo.classManage.service;

import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.entity.ClassInf;
import com.teamup.demo.classManage.mapper.ClassMapper;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service(value = "ClassService")
public class ClassService {
    @Resource
    private ClassMapper classMapper;

    public int addClass(Class c){
        try {
            return classMapper.addClass(c);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public String codeIsExist(String code){
        return classMapper.codeIsExist(code);
    }
    public List<Class> getClassByUser(Teacher user,String table){
        if (table.equals("student"))
            return classMapper.getClassByStu(user.getUser());
        else if(table.equals("teacher"))
            return classMapper.getClassByTea(user.getUser());
        else
            return null;
    }
    public List<Student> getStuByClassId(String classId){
        return classMapper.getStuByClass(classId);
    }
    public Teacher getTeaByClassId(String classId){
        return classMapper.getTeaByClass(classId);
    }
    public int deleteClassByClassId(List<String> idList){
        try {
            return classMapper.deleteClass(idList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
    public int joinClass(List<ClassInf>list){
        try {
            return classMapper.joinClass(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
    public int joinClass(ClassInf classInf){
        try {
            return classMapper.updateClassInf(classInf);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
