package com.teamup.demo.userManage.mapper;

import com.teamup.demo.userManage.entity.MailCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMapper {
    int addCode(MailCode code);
    String findCodeByUser(@Param("user") String user, @Param("time") long time, @Param("type") int sendType);
    int deleteInvalid(long time);
}
