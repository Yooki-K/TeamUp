package com.teamup.demo.userManage.service;

import com.teamup.demo.userManage.entity.MailCode;
import com.teamup.demo.userManage.mapper.CodeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "CodeService")
public class CodeService {
    @Resource
    private CodeMapper codeMapper;
    public int addCode(MailCode code){
        return codeMapper.addCode(code);
    }
    public String findCodeByUser(String user,long time,int sendType){
        return codeMapper.findCodeByUser(user,time,sendType);
    }
    public int deleteInvalid(long time){
        return codeMapper.deleteInvalid(time);
    }
}
