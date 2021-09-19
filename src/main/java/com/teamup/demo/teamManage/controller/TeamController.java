package com.teamup.demo.teamManage.controller;

import com.teamup.demo.teamManage.service.TeamService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TeamController {
    @Resource
    private TeamService teamService;
}
