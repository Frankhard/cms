package com.cms.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cms.model.*;
import com.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.base.BaseController;
import com.cms.config.ResponseMsg;
import com.cms.utils.Pager;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserTeamService userTeamService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ActSignupService actSignupService;

    /**
     * 分页获取新闻列表
     *
     * @param pageNumber
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getNewsList")
    public ResponseEntity<?> getNewsList(Integer pageNumber, String title, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<List<News>> responseMsg = new ResponseMsg();
        //分页查询
        String sql = "SELECT * FROM news WHERE 1=1  ";
		if (title!=null) {
			sql += " and title like '%" + title + "%'";
		}
        /*if (!isEmpty(news.getTitle())) {
            sql += " and title like '%" + news.getTitle() + "%'";
        }*/
        sql += " ORDER BY ID DESC ";
        Pager<News> pagers = newsService.getNewsList(pageNumber, sql);
        responseMsg.setData(pagers.getDatas());
        responseMsg.setPager(pagers);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    /**
     * 通过 id查询新闻
     *
     * @param id
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getNewsById")
    public ResponseEntity<?> getNewsById(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        News byId = newsService.getById(id);
        responseMsg.setData(byId);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    /**
     * 获取活动列表
     *
     * @param pageNumber
     * @param activity
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getActivityList")
    public ResponseEntity<?> getActivityList(Integer pageNumber, Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<List<Activity>> responseMsg = new ResponseMsg();
        //分页查询
        String sql = "SELECT * FROM activity WHERE 1=1 and status = 1 ";
        if (!isEmpty(activity.getTitle())) {
            sql += " and title like '%" + activity.getTitle() + "%'";
        }
        sql += " ORDER BY ID DESC ";
        Pager<Activity> pagers = activityService.getActivityList(pageNumber, sql);
        responseMsg.setData(pagers.getDatas());
        responseMsg.setPager(pagers);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 通过活动id查询活动详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/getActivityById")
    public ResponseEntity<?> getActivityById(Integer id, @RequestBody JSONObject userIdobj, Model model) {
        System.out.println(id);
        System.out.println(userIdobj);
        int userId=Integer.valueOf(userIdobj.get("userId").toString());
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        Activity byId = activityService.getById(id);
        ActSignup a = new ActSignup();
        a.setActivityId(id);
        //userStatus是否报名活动，1未报名，2已报名
        Integer userStatus = 1;
        if (userIdobj == null) {
            //api直接调用没传userid
            userStatus = 1;
        } else {
            a.setUserId(userId);
            ActSignup byEntity = actSignupService.getByEntity(a);
            if (byEntity == null) {
                userStatus = 1;
            } else {
                userStatus = 2;
            }
        }
        byId.setUserStatus(userStatus);
        responseMsg.setData(byId);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);

    }

    /**
     * 分页查询社团列表
     *
     * @param pageNumber
     * @param team
     * @return
     */
    @RequestMapping(value = "/getTeamList")
    public ResponseEntity<?> getTeamList(Integer pageNumber, Team team) {
        ResponseMsg<List<Team>> responseMsg = new ResponseMsg();
        //分页查询
        String sql = "SELECT * FROM team WHERE 1=1 and isDelete = 0 ";
        if (!isEmpty(team.getName())) {
            sql += " and name like '%" + team.getName() + "%'";
        }
        sql += " ORDER BY ID DESC ";
        Pager<Team> pagers = teamService.getTeamList(pageNumber, sql);
        responseMsg.setData(pagers.getDatas());
        responseMsg.setPager(pagers);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 我加入的社团
     *
     * @param pageNumber
     * @param team
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getTeamMyList")
    public ResponseEntity<?> getTeamMyList(Integer pageNumber, Integer userId, Team team, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<List<Team>> responseMsg = new ResponseMsg();
        //分页查询
        String sql = "SELECT * FROM team WHERE 1=1 and isDelete = 0 and id in (SELECT team_id FROM user_team where user_id = " + userId + ") ";
        if (!isEmpty(team.getName())) {
            sql += " and name like '%" + team.getName() + "%'";
        }
        sql += " ORDER BY ID DESC ";
        Pager<Team> pagers = teamService.getTeamList(pageNumber, sql);
        responseMsg.setData(pagers.getDatas());
        responseMsg.setPager(pagers);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 通过社团id查询活动详情
     *
     * @param id
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getTeamById")
    public ResponseEntity<?> getTeamById(Integer id, Integer userId, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        Team byId = teamService.getById(id);
        UserTeam u = new UserTeam();
        u.setTeamId(byId.getId());
        List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
        if (!CollectionUtils.isEmpty(listAllByEntity)) {
            byId.setNum(listAllByEntity.size());
        } else {
            byId.setNum(0);
        }
        Integer userStatus = 1;
        if (userId == null) {
            userStatus = 1;
        } else {
            u.setUserId(userId);
            UserTeam byEntity = userTeamService.getByEntity(u);
            if (byEntity == null) {
                userStatus = 1;
            } else {
                userStatus = 2;
            }
        }
        byId.setUserStatus(userStatus);
        responseMsg.setData(byId);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 小程序端登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        List<User> listAll = userService.listAllByEntity(user);
        if (CollectionUtils.isEmpty(listAll)) {
            responseMsg.setErrcode("101");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }
        responseMsg.setData(listAll.get(0).getId());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/res")
    public ResponseEntity<?> res(@RequestBody User user){
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //用作校验学号的唯一性
        User usertmp = new User();
        usertmp.setNo(user.getNo());

        //用作校验学生表是否存在学号
        Student studenttmp = new Student();
        studenttmp.setNo(user.getNo());

        List<User> listAllu = userService.listAllByEntity(usertmp);
        if (!CollectionUtils.isEmpty(listAllu)) {
            responseMsg.setErrcode("101");
            responseMsg.setErrmsg("此学号已注册请直接登录");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }else{
            List<Student> listAllstd = studentService.listAllByEntity(studenttmp);
            if (!CollectionUtils.isEmpty(listAllstd)) {
                responseMsg.setErrcode("101");
                responseMsg.setErrmsg("学号输入有误请重新输入");
                return new ResponseEntity<>(responseMsg, HttpStatus.OK);
            }
            else{
                userService.insert(user);
            }
        }
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    /**
     * 加入 社团
     *
     * @param user
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addTeam")
    public ResponseEntity<?> addTeam(UserTeam user, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //先判断当前有没有已经申请加入同一社团的没被审批的
        Apply apply = new Apply();
        apply.setStatus(0);
        apply.setType(1);
        apply.setTeamId(user.getTeamId());
        apply.setUserId(user.getUserId());
        Apply byEntity = applyService.getByEntity(apply);
        if (byEntity != null) {
            responseMsg.setErrmsg("请耐心等待审批，无需重复申请");
            responseMsg.setErrcode("101");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        UserTeam userTeam = new UserTeam();
        userTeam.setTeamId(user.getTeamId());
        userTeam.setUserId(user.getUserId());
        List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(userTeam);
        if (!CollectionUtils.isEmpty(listAllByEntity)) {
            responseMsg.setErrmsg("您已经是社团成员，无需重新申请");
            responseMsg.setErrcode("102");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }
        UserTeam tmp=new UserTeam();
        tmp.setTeamId(user.getTeamId());
        List<UserTeam> listbyteamid=userTeamService.listAllByEntity(tmp);
        if(!CollectionUtils.isEmpty(listbyteamid)){
            applyService.insert(apply);
        }
        else {
            userTeam.setRole(2);
            userTeamService.insert(userTeam);
            Team team= teamService.getById(user.getTeamId());
            team.setUserId(user.getUserId());
            teamService.updateById(team);
        }
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 退出社团
     * @param userTeam
     * @param content
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteTeam")
    public ResponseEntity<?> deleteTeam(UserTeam userTeam, String content, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //先判断当前有没有正在申请的没被审批的
        Apply apply = new Apply();
        apply.setStatus(0);
        apply.setType(2);
        apply.setTeamId(userTeam.getTeamId());
        apply.setUserId(userTeam.getUserId());
        Apply byEntity = applyService.getByEntity(apply);
        if (byEntity != null) {
            responseMsg.setErrmsg("请耐心等待退社审批，无需重复申请");
            responseMsg.setErrcode("101");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        UserTeam u = new UserTeam();
        u.setTeamId(userTeam.getTeamId());
        u.setUserId(userTeam.getUserId());
        List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
        if (CollectionUtils.isEmpty(listAllByEntity)) {
            responseMsg.setErrmsg("您还不是社团成员，不能申请退社");
            responseMsg.setErrcode("102");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }
        UserTeam userTeamed = listAllByEntity.get(0);
        apply.setReason(content);
        applyService.insert(apply);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    /**
     * 分页查看自己已报名的活动
     * @param pageNumber
     * @param userId
     * @param activity
     * @return
     */
    @RequestMapping(value = "/getActSignupList")
    public ResponseEntity<?> getActSignupList(Integer pageNumber,Integer userId,Activity activity) {
        ResponseMsg<List<Activity>> responseMsg = new ResponseMsg();
        //分页查询
        String sql = "SELECT * FROM activity WHERE 1=1 and id in (SELECT activity_id FROM act_signup where user_id = " + userId + ") ";
        if (activity.getTitle() != null) {
            sql += " and title like '%" + activity.getTitle() + "%'";
        }
        sql += " ORDER BY ID DESC ";
        //Pager<Activity> pagers = actSignupService.getActSignupList(pageNumber, sql);
        Pager<Activity> pagers =activityService.getActivityList(pageNumber, sql);
        responseMsg.setData(pagers.getDatas());
        responseMsg.setPager(pagers);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 报名活动
     *
     * @param actSignup
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/signupActivity")
    public ResponseEntity<?> signupActivity(ActSignup actSignup, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //先判断当前有没有已经报名同一活动的
        ActSignup actSignuped = new ActSignup();
        actSignuped.setActivityId(actSignup.getActivityId());
        actSignuped.setUserId(actSignup.getUserId());
        List<ActSignup> byEntity = actSignupService.listAllByEntity(actSignuped);

        if (!CollectionUtils.isEmpty(byEntity)) {
            responseMsg.setErrmsg("已报名活动无需再报名");
            responseMsg.setErrcode("101");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        Activity activity = activityService.getById(actSignup.getActivityId());
        if (activity!=null) {
            if(activity.getMemberCount() <= 0){
                responseMsg.setErrmsg("活动报名已满，下次请早");
                responseMsg.setErrcode("103");
                return new ResponseEntity<>(responseMsg, HttpStatus.OK);
            }
            activity.setMemberCount(activity.getMemberCount()-1);
            actSignupService.insert(actSignup);
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        responseMsg.setErrmsg("非法活动，无法报名");
        responseMsg.setErrcode("102");
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 取消活动报名
     *
     * @param cancel
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cancelActSignup")
    public ResponseEntity<?> cancelActSignup(ActSignup cancel, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //先判断该活动当前有没有报名
        ActSignup actSignuped = new ActSignup();
        actSignuped.setActivityId(cancel.getActivityId());
        actSignuped.setUserId(cancel.getUserId());
        ActSignup actSignupbyEntity = actSignupService.getByEntity(actSignuped);
        if (actSignupbyEntity == null) {
            responseMsg.setErrmsg("尚未报名该活动无需取消报名");
            responseMsg.setErrcode("102");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        Activity activity = new Activity();
        activity.setId(cancel.getActivityId());
        Activity activityByEntity = activityService.getByEntity(activity);
        if (activityByEntity != null) {
            //判断当前时间是否已在活动开始时间之后
            System.out.println(activityByEntity.getStartTime());
            System.out.println(LocalDateTime.now());

            Date starttimeDate=activityByEntity.getStartTime();
            Instant instant = starttimeDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime starttime = instant.atZone(zoneId).toLocalDateTime();
            /*String starttime = activityByEntity.getStartTime().toString();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(starttime, dtf2);*/
            if(starttime.compareTo(LocalDateTime.now())<=0){
                //活动开始时间在当前时间之前是true
                responseMsg.setErrmsg("活动已经开始无法取消报名，请去签到");
                responseMsg.setErrcode("104");
                return new ResponseEntity<>(responseMsg, HttpStatus.OK);
            }
            activity.setMemberCount(activity.getMemberCount()+1);
            actSignupService.deleteByEntity(cancel);
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }
        responseMsg.setErrmsg("非法活动，无法取消报名");
        responseMsg.setErrcode("102");
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    /**
     * 活动签到
     *
     * @param actSignin
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/signinActivity")
    public ResponseEntity<?> signinActivity(ActSignup actSignin, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg<Object> responseMsg = new ResponseMsg();
        //先判断该活动当前有没有报名
        ActSignup actSignuped = new ActSignup();
        actSignuped.setActivityId(actSignin.getActivityId());
        actSignuped.setUserId(actSignin.getUserId());
        ActSignup byEntity = actSignupService.getByEntity(actSignuped);
        if (byEntity == null) {
            responseMsg.setErrmsg("尚未报名该活动无需签到");
            responseMsg.setErrcode("102");
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }

        Activity activity = new Activity();
        activity.setId(actSignin.getActivityId());
        Activity activityByEntity = activityService.getByEntity(activity);
        if (activityByEntity != null) {
            /**
             * 判断当前时间是否已在活动开始时间之后
             * int compareTo = date1.compareTo(date2);
             * date1小于date2返回-1，date1大于date2返回1，相等返回0
             * **/
            Date starttimeDate=activityByEntity.getStartTime();
            Instant instant = starttimeDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime starttime = instant.atZone(zoneId).toLocalDateTime();
            if(starttime.compareTo(LocalDateTime.now()) > 0){
                //活动开始时间在当前时间之后是true，活动还没开始
                responseMsg.setErrmsg("活动还没开始,等会再来签到");
                responseMsg.setErrcode("104");
                return new ResponseEntity<>(responseMsg, HttpStatus.OK);
            }

            Date endtimeDate = activityByEntity.getEndTime();
            Instant instanend = endtimeDate.toInstant();
            ZoneId zoneIdend = ZoneId.systemDefault();
            LocalDateTime endtime = instanend.atZone(zoneIdend).toLocalDateTime();
            if(endtime.compareTo(LocalDateTime.now()) < 0){
                //活动结束时间在当前时间之前是true，活动已经结束
                responseMsg.setErrmsg("活动已经结束,下次记得早点签到");
                responseMsg.setErrcode("104");
                return new ResponseEntity<>(responseMsg, HttpStatus.OK);
            }
            actSignin.setStatus(1);
            actSignupService.updateById(actSignin);
            return new ResponseEntity<>(responseMsg, HttpStatus.OK);
        }
        responseMsg.setErrmsg("非法活动，无法签到");
        responseMsg.setErrcode("102");
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

}
