package com.teamup.demo.service;

import com.teamup.demo.entity.MailCode;
import com.teamup.demo.mapper.CodeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service(value = "CodeService")
public class CodeService {
    @Resource
    private CodeMapper codeMapper;
    public int addCode(MailCode code){
        return codeMapper.addCode(code);
    }
    public String findCodeByUser(Map<String,Object> map){
        return codeMapper.findCodeByUser(map);
    }
}
