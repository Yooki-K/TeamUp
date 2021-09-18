package com.teamup.demo.teamManage.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

@Repository
public interface TeamMapper {
    int updateTeamInfById(@Param("map") Map<String, String> map, @Param("id")int id)throws SQLException;
    int setTeamId(@Param("user")String user,@Param("classId")int classId,@Param("teamId")int teamId)throws SQLException;
}
