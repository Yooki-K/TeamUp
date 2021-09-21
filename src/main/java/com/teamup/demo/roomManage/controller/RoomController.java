package com.teamup.demo.roomManage.controller;


import com.sun.istack.internal.NotNull;
import com.teamup.demo.roomManage.entity.ApplyRoom;
import com.teamup.demo.roomManage.entity.Invitation;
import com.teamup.demo.roomManage.entity.Room;
import com.teamup.demo.roomManage.service.RoomService;
import com.teamup.demo.tool.Custom;
import com.teamup.demo.tool.Message;
import com.teamup.demo.tool.Util;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class RoomController {
    @Resource
    private RoomService roomService;
    @Resource
    Custom custom;
    @Resource
    private UserService userService;
    @PostMapping("/data/matchTeammate")
    /*匹配 参数type {intel智能匹配  key 关键字匹配}*/
    public Map<String,List<Student>> intelMatch(@Param("type") String type, @Param("aim") String aim,HttpSession session){
        Student user= (Student) session.getAttribute("user");
        Map<String,List<Student>> map =new HashMap<>();
        if(type.equals("intel")){//    智能匹配
            for (String x: custom.getLabels()
                 ) {
                if (user.getLabel()!=null && x.equals(user.getLabel())) continue;
                map.put(x,roomService.matchStuByLabel(user,x));
            }
        }else if(type.equals("key")){//    关键字匹配
            if(aim!=null)
                map.put(aim,roomService.matchStuByLabel(user,aim));
            else
                map.put("error",null);
        }
        else
            map.put("error",null);
        return map;
    }
    @PostMapping("/data/query/room/member")
    /*查询房间成员*/
    public List<Student> queryClassMember(@Param("roomId")int roomId){
        return roomService.getStuByRoom(roomId);
    }
    @PostMapping("/updateRoom")
    /*必须带参数int roomId*/
    public Message updateRoomById(@RequestBody Map<String,String> room){
        int roomId = Integer.parseInt(room.get("id"));
        room.remove("id");
        if(roomService.updateRoomById(room,roomId)>0)
            return new Message(true);
        else
            return new Message(false);
    }
    /*创建房间 必须参数 name teamName content tag targetNum
              可选参数 color classId*/
    @PostMapping("/create/room")
    public Message createRoom(Room room, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        room.setUser(student.getUser());
        room.setCreateTime();
        if(roomService.addRoom(room) > 0)
            return new Message(true,String.format("%s房间创建成功",room.getName()));
        else
            return new Message(false,"创建房间失败");
    }
    /*删除房间,只有房主才有删除权限*/
    @PostMapping("/delete/room")
    public Message deleteRoom(@Param("roomId")int roomId, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        if(roomService.deleteRoom(student,roomId) > 0)
            return new Message(true,"删除房间成功");
        else
            return new Message(false,"删除房间失败，房间不存在或无删除权限");
    }
    /*成员加入或退出房间，房间人数+1(-1)*/
    @PostMapping("/operate/room")
    public Message operateRoom(@Param("type")String type,@Param("id")int roomId,
                               HttpSession session){
        Student student = (Student) session.getAttribute("user");
        int num;
        if ("join".equals(type)){
            num = roomService.addStuByRoom(student.getUser(),roomId);
            if (num>0){
                return new Message(true);
            }
            return new Message(false);
        }else if("exit".equals(type)){
            num = roomService.removeStuByRoom(student.getUser(), roomId);
            if (num>0){
                return new Message(true);
            }
            return new Message(false);
        }else
            return new Message(false,String.format("%s操作参数未知",type));
    }
    @PostMapping("/data/query/room")
    public List<Map<String,Object>> queryRoom( @Param("type")@NotNull String type, @Param("param")String param,
            /*根据参数查询房间*/
                                 @Param("sort")String sort, @Param("isAsc")String isAsc){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Room> rooms=null;
        switch (type){
            case "class":
                rooms = roomService.getRoomByClass(Integer.parseInt(param));
                break;
            case "public":
                if(sort!=null) {
                    if ("false".equals(isAsc)) {
                        rooms = roomService.getRoomPublic(sort, false);
                        break;
                    }else {
                        rooms = roomService.getRoomPublic(sort,true);
                    }
                }
                else {
                    rooms = roomService.getRoomPublic("id",true);
                }
                break;
            case "tag":
                rooms = roomService.getRoomByTag(param);
                break;
            case "name":
                rooms = roomService.getRoomByName(param);
                break;
            default:break;
        }
        for(Room x :rooms){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("room",x);
            map.put("teacher",userService.findUserByNo(x.getTeacherId(),"teacher").getName());
            Student student = userService.findUserByUser(x.getUser(),"student");
            if(student.getHeadshot()!=null) {
                String imgType = Util.getImgType(student.getHeadshot());
                if(imgType!=null)
                    map.put("headShot", String.format("data:image/%s;base64,", imgType) +
                        Base64.getEncoder().encodeToString(student.getHeadshot()));
                student.setHeadshot(null);
            }
            else{
                map.put("headShot","/img/nullPerson.png");
            }
            map.put("tag",x.getTag());
            map.put("user",student);

            list.add(map);
        }
        return list;
    }
    /*邀请加入房间 table参数 student teacher*/
    @PostMapping("/invite/{table}")
    public Message inviteMember(@PathVariable String table,@Param("toUser")String toUser,
                                @Param("roomId")int roomId, HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Invitation invitation = new Invitation(user.getUser(),toUser,roomId, "student".equals(table));
        if(roomService.addInvitation(invitation)>0)
            return new Message(true,"邀请成功");
        else
            return new Message(false,"邀请失败");
    }
    /*查看邀请 type参数 send我发送的 recv我接收的*/
    @PostMapping("/data/query/invitation/{type}")
    public List<Invitation> queryInvitation(@PathVariable String type,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        return roomService.getInvitation(user.getUser(), type);
    }
    /*对邀请进行操作 json 数组
    * type参数 agree disagree*/
    @RequestMapping(value = "/operate/invitation/{type}",method = RequestMethod.POST)
    public Message operateInvitation(@RequestBody int[] idList, @PathVariable String type,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        if ("agree".equals(type)){
            if(roomService.operateInvitation(true,idList,user.getUser())>0)
                return new Message(true);
            else
                return new Message(false);
        }else if("disagree".equals(type)){
            if(roomService.operateInvitation(false,idList, user.getUser())>0)
                return new Message(true);
            else
                return new Message(false);
        }else{
            return new Message(false,"操作未知");
        }
    }
    /*申请加入房间 json roomId content*/
    @PostMapping("/apply/room")
    public Message applyRoom(@RequestBody  ApplyRoom applyRoom, HttpSession session){
        Student user = (Student) session.getAttribute("user");
        applyRoom.setUser(user.getUser());
        if(roomService.addApplication(applyRoom)>0)
            return new Message(true);
        else
            return new Message(false);
    }
    /*查看未处理申请数量*/
    @GetMapping("/data/query/application/num")
    public int queryApplicationNum(@Param("roomId")int roomId){
        return roomService.getApplicationNum(roomId);
    }
    /*查看申请*/
    @PostMapping("/data/query/application")
    public List<ApplyRoom> queryApplication(@Param("roomId")int roomId){
        return roomService.getApplicationByRoom(roomId);
    }
    /*对申请进行操作 json 数组
    * type参数 agree disagree*/
    @RequestMapping(value = "/operate/application/{type}/{roomId}",method = RequestMethod.POST)
    public Message operateApplication(@RequestBody int[] idList, @PathVariable String type,
                                      @PathVariable int roomId,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Room room = roomService.findRoomById(roomId);
        if(room == null)
            return new Message(false,"班级不存在");
        if (!user.getUser().equals(room.getUser()))
            return new Message(false,"只有房主有同意申请的权限");
        if ("agree".equals(type)){
            if(roomService.operateApplication(true,idList)>0)
                return new Message(true);
            else
                return new Message(false);
        }else if("disagree".equals(type)){
            if(roomService.operateApplication(false,idList)>0)
                return new Message(true);
            else
                return new Message(false);
        }else{
            return new Message(false,"操作未知");
        }
    }
    /*退出房间以及房主踢人 不传toUser参数自己退出，传房主踢人*/
    @PostMapping("/delete/room/member")
    public Message removeRoomMember(@Param("roomId")int roomId,@Param("toUser")String toUser,
                                    HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Room room = roomService.findRoomById(roomId);
        if(room==null)
            return new Message(false,"房间不存在");
        if (toUser!=null){
            if(!room.getUser().equals(user.getUser()))
                return new Message(false,"只有房主才有踢人权限");
            if(roomService.removeStuByRoom(toUser,roomId)>0)
                return new Message(true);
            else
                return new Message(false);
        }
        if(roomService.removeStuByRoom(user.getUser(),roomId)>0)
            return new Message(true);
        else
            return new Message(false);
    }
}
