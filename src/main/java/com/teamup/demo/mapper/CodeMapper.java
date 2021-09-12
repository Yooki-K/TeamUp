package com.teamup.demo.mapper;

import com.teamup.demo.entity.MailCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CodeMapper {
    int addCode(MailCode code);
    String findCodeByUser(@Param("user") String user, @Param("time") long time, @Param("type") int sendType);
    int deleteInvalid(long time);
}
