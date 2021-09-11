package com.teamup.demo.mapper;

import com.teamup.demo.entity.MailCode;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CodeMapper {
    int addCode(MailCode code);
    String findCodeByUser(Map<String,Object> map);
}
