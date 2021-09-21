package com.teamup.demo.mapper;

import com.teamup.demo.entity.MailCode;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMapper {
    int addCode(MailCode code);
}
