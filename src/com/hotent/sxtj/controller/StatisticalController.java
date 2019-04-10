package com.hotent.sxtj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.annotion.Action;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.PositionService;

/**
 * 对象功能:办事统计类
 * 开发公司:正方软件股份有限公司
 * 开发人员:包永青
 * 创建时间:2018-09-11 15:00:00
 */
@Controller
@RequestMapping("/sxtj/controller/statistical/")
@Action(ownermodel = SysAuditModelType.PROCESS_MANAGEMENT)
public class StatisticalController extends BaseController {

    @Resource
    private ProcessRunService processRunService;
    @Resource
    private PositionService positionService;
    @Resource
    private BpmDefAuthorizeService bpmDefAuthorizeService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    //数据库查询日期类型
    public static final String DATE_DD = "yyyy-mm-dd";
    public static final String DATE_WI = "yyyy-iw";
    public static final String DATE_MM = "yyyy-mm";
    public static final String DATE_YYYY = "yyyy";


    /**
     * 查看办事统计
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getStatistical")
    @Action(description = "查看办事统计")
    public ModelAndView getStatistical(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/sxtj/statisticalList.jsp");
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        List<String> roleIds = new ArrayList();
        List<String> mapDefKey = new ArrayList();
        Map<String, String> zdsjtjMap = new HashMap<String, String>();

        params.put("nowTime", new Date());

        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRun = processRunService.getZdsjtj(params);
                for (ProcessRun run : processRun) {
                    zdsjtjMap.put(run.getSubject(), run.getNumbers());
                }
                List<Position> positionsList = positionService.getAll();
                return mv.addObject("zdsjtjMap", zdsjtjMap).addObject("positionsList", positionsList);
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                for (String sysUserId : sysUserIds) {
                    List<String> sysRoleIds = sysRoleService.getRoleIdByUserId(sysUserId);
                    for (String sysRoleId : sysRoleIds) {
                        roleIds.add(sysRoleId);
                    }
                }
                //去除重复的角色Id
                HashSet h = new HashSet(roleIds);
                roleIds.clear();
                roleIds.addAll(h);
                //获取角色流程服务的KEY
                if (BeanUtils.isNotEmpty(roleIds)) {
                    for (String roleId : roleIds) {
                        List<String> defKeys = bpmDefAuthorizeService.getDefKeyByRoleId(roleId);
                        if (defKeys.size() > 0) {
                            for (String defCKey : defKeys) {
                                if (defCKey == null) continue;
                                mapDefKey.add(defCKey);
                            }
                        }

                    }
                    //去除重复的KEY
                    HashSet hash = new HashSet(mapDefKey);
                    mapDefKey.clear();
                    mapDefKey.addAll(hash);

                }
                String DefKeys = "";
                for (String bpmDefAct : mapDefKey) {
                    DefKeys += "'" + bpmDefAct + "',";
                }
                DefKeys = DefKeys.substring(0, DefKeys.length() - 1);
                params.put("actRights", DefKeys);
                params.put("isNeedRight", isNeedRight);

                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRun = processRunService.getZdsjtj(params);
        for (ProcessRun run : processRun) {
            zdsjtjMap.put(run.getSubject(), run.getNumbers());
        }
        List<Position> positionsList = positionService.getAll();
        return mv.addObject("zdsjtjMap", zdsjtjMap).addObject("positionsList", positionsList);
    }

    @RequestMapping("getZdsjtj")
    @ResponseBody
    @Action(description = "站点数据统计")
    public JSONObject getZdsjtj(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        List<String> roleIds = new ArrayList();
        List<String> mapDefKey = new ArrayList();
        JSONObject json = new JSONObject();

        params.put("nowTime", new Date());
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);

        Long userId = ContextUtil.getCurrentUserId();

        Map<String, Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(userId, BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
        String isNeedRight = "";
        String orgType = "";

        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRun = processRunService.getZdsjtj(params);
                for (ProcessRun run : processRun) {
                    json.put(run.getSubject(), run.getNumbers());
                }
                json.put("ctx", request.getContextPath());
                return json;
            }
            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                for (String sysUserId : sysUserIds) {
                    List<String> sysRoleIds = sysRoleService.getRoleIdByUserId(sysUserId);
                    for (String sysRoleId : sysRoleIds) {
                        System.out.println("sysRoleId:" + sysRoleId);
                        roleIds.add(sysRoleId);
                    }
                }
                //去除重复的角色Id
                HashSet h = new HashSet(roleIds);
                roleIds.clear();
                roleIds.addAll(h);

                //获取角色流程服务的KEY
                if (BeanUtils.isNotEmpty(roleIds)) {
                    for (String roleId : roleIds) {
                        List<String> defKeys = bpmDefAuthorizeService.getDefKeyByRoleId(roleId);
                        if (defKeys.size() > 0) {
                            for (String defCKey : defKeys) {
                                if (defCKey == null) continue;
                                mapDefKey.add(defCKey);
                            }
                        }

                    }
                    //去除重复的KEY
                    HashSet hash = new HashSet(mapDefKey);
                    mapDefKey.clear();
                    mapDefKey.addAll(hash);

                }
                String DefKeys = "";
                for (String bpmDefAct : mapDefKey) {
                    DefKeys += "'" + bpmDefAct + "',";
                }
                DefKeys = DefKeys.substring(0, DefKeys.length() - 1);
                params.put("actRights", DefKeys);
                params.put("isNeedRight", isNeedRight);

                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);
                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }


        List<ProcessRun> processRun = processRunService.getZdsjtj(params);
        for (ProcessRun run : processRun) {
            json.put(run.getSubject(), run.getNumbers());
        }
        json.put("ctx", request.getContextPath());
        return json;
    }

    @RequestMapping("getSxtjforten")
    @ResponseBody
    @Action(description = "内容Top10事项统计")
    public JSONArray getSxtjforten(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        List<String> roleIds = new ArrayList();
        List<String> mapDefKey = new ArrayList();
        JSONArray data = new JSONArray();
        PageBean pb = new PageBean();
        pb.setCurrentPage(1);
        pb.setPagesize(10);
        // 去掉进行分页的总记录数的查询
        pb.setShowTotal(false);

        params.put("nowTime", new Date());
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);

        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRunBstjsl = processRunService.getSxtjforten(params, pb);
                for (int i = 0; i < processRunBstjsl.size(); i++) {
                    JSONObject jo = new JSONObject();
                    if (i == 0) {
                        jo.put("order", "<span class=\"ranking first\">" + (i + 1) + "</span>");
                    } else if (i == 1) {
                        jo.put("order", "<span class=\"ranking second\">" + (i + 1) + "</span>");
                    } else if (i == 2) {
                        jo.put("order", "<span class=\"ranking third\">" + (i + 1) + "</span>");
                    } else {
                        jo.put("order", "<span class=\"ranking\">" + (i + 1) + "</span>");
                    }
                    jo.put("name", processRunBstjsl.get(i).getSubject());
                    jo.put("amount", processRunBstjsl.get(i).getNumbers());
                    jo.put("versionNo", "V" + processRunBstjsl.get(i).getVersionNo());
                    data.add(jo);
                }

                return data;
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                for (String sysUserId : sysUserIds) {
                    List<String> sysRoleIds = sysRoleService.getRoleIdByUserId(sysUserId);
                    for (String sysRoleId : sysRoleIds) {
                        roleIds.add(sysRoleId);
                    }
                }
                //去除重复的角色Id
                HashSet h = new HashSet(roleIds);
                roleIds.clear();
                roleIds.addAll(h);
                //获取角色流程服务的KEY
                if (BeanUtils.isNotEmpty(roleIds)) {
                    for (String roleId : roleIds) {
                        List<String> defKeys = bpmDefAuthorizeService.getDefKeyByRoleId(roleId);
                        if (defKeys.size() > 0) {
                            for (String defCKey : defKeys) {
                                if (defCKey == null) continue;
                                mapDefKey.add(defCKey);
                            }
                        }

                    }
                    //去除重复的KEY
                    HashSet hash = new HashSet(mapDefKey);
                    mapDefKey.clear();
                    mapDefKey.addAll(hash);

                }
                String DefKeys = "";
                for (String bpmDefAct : mapDefKey) {
                    DefKeys += "'" + bpmDefAct + "',";
                }
                DefKeys = DefKeys.substring(0, DefKeys.length() - 1);
                params.put("actRights", DefKeys);
                params.put("isNeedRight", isNeedRight);

                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRunBstjsl = processRunService.getSxtjforten(params, pb);
        for (int i = 0; i < processRunBstjsl.size(); i++) {
            JSONObject jo = new JSONObject();
            if (i == 0) {
                jo.put("order", "<span class=\"ranking first\">" + (i + 1) + "</span>");
            } else if (i == 1) {
                jo.put("order", "<span class=\"ranking second\">" + (i + 1) + "</span>");
            } else if (i == 2) {
                jo.put("order", "<span class=\"ranking third\">" + (i + 1) + "</span>");
            } else {
                jo.put("order", "<span class=\"ranking\">" + (i + 1) + "</span>");
            }
            jo.put("name", processRunBstjsl.get(i).getSubject());
            jo.put("amount", processRunBstjsl.get(i).getNumbers());
            jo.put("versionNo", "V" + processRunBstjsl.get(i).getVersionNo());
            data.add(jo);
        }

        return data;
    }

    @RequestMapping("getSxbltj")
    @ResponseBody
    @Action(description = "查看事项办理统计")
    public JSONArray getSxbltj(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        JSONArray data = new JSONArray();
        PageBean pb = new PageBean();
        pb.setCurrentPage(1);
        pb.setPagesize(999999);
        // 去掉进行分页的总记录数的查询
        pb.setShowTotal(false);

        params.put("nowTime", new Date());
        //long sxtjIndex = RequestUtil.getLong(request, "sxtjIndex", 0L);
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);
        String subject = RequestUtil.getString(request, "subject");
        if (subject != null && StringUtil.isNotEmpty(subject)) {
            params.put("subject", "%" + subject + "%");
        }
        String starttime = RequestUtil.getString(request, "starttime");
        params.put("startTimes", starttime);
        String endtime = RequestUtil.getString(request, "endtime");
        params.put("endTimes", endtime);


        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRunBstjsl = processRunService.getSxbltj(params, pb);


                for (ProcessRun processRun : processRunBstjsl) {
                    JSONObject jo = new JSONObject();
                    jo.put("defId", processRun.getDefId());
                    jo.put("name", processRun.getSubject());
                    jo.put("sponsor", processRun.getLaunchProcessesTotal());
                    jo.put("finished", processRun.getProcessesOperationsTotal());
                    jo.put("timeout", processRun.getTimeOutTotal());
                    jo.put("rate", processRun.getHandlingRate());
                    jo.put("versionNo", "V" + processRun.getVersionNo());

                    double day = 0, hour = 0, minute = 0, second = 0;
                    double days = 0, hours = 0, minutes = 0, seconds = 0;
                    double h24 = 24, m60 = 60, s60 = 60;

                    day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
                    hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
                    minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
                    second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
                    //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
                    //天
                    days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
                    hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
                    day = days;
                    //小时
                    hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
                    minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
                    hour = hours;
                    //分钟
                    minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
                    second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
                    minute = minutes;
                    //秒
                    second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
                    while (hour >= 24 || minute >= 60 || second >= 60) {
                        if (hour >= 24) {
                            day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                            hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                        }
                        if (minute >= 60) {
                            hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                            minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                        }
                        if (second >= 60) {
                            minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                            second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                        }
                    }
                    //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
                    String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";
                    jo.put("time", Time);
                    data.add(jo);
                }

                return data;
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                params.put("isNeedRight", isNeedRight);
                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRunBstjsl = processRunService.getSxbltj(params, pb);


        for (ProcessRun processRun : processRunBstjsl) {
            JSONObject jo = new JSONObject();
            jo.put("defId", processRun.getDefId());
            jo.put("name", processRun.getSubject());
            jo.put("sponsor", processRun.getLaunchProcessesTotal());
            jo.put("finished", processRun.getProcessesOperationsTotal());
            jo.put("timeout", processRun.getTimeOutTotal());
            jo.put("rate", processRun.getHandlingRate());
            jo.put("versionNo", "V" + processRun.getVersionNo());

            double day = 0, hour = 0, minute = 0, second = 0;
            double days = 0, hours = 0, minutes = 0, seconds = 0;
            double h24 = 24, m60 = 60, s60 = 60;

            day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
            hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
            minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
            second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
            //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
            //天
            days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
            hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
            day = days;
            //小时
            hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
            minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
            hour = hours;
            //分钟
            minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
            second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
            minute = minutes;
            //秒
            second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
            while (hour >= 24 || minute >= 60 || second >= 60) {
                if (hour >= 24) {
                    day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                    hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                }
                if (minute >= 60) {
                    hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                    minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                }
                if (second >= 60) {
                    minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                    second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                }
            }
            //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
            String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";
            jo.put("time", Time);
            data.add(jo);
        }

        return data;
    }

    /**/
    @RequestMapping("getNodeAvgTime")
    @ResponseBody
    @Action(description = "流程节点平均时间统计")
    public JSONArray getNodeAvgTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        JSONArray data = new JSONArray();

        params.put("nowTime", new Date());
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        long defId = RequestUtil.getLong(request, "defId", 0L);
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);
        params.put("defId", defId);
        String subject = RequestUtil.getString(request, "subject");
        if (subject != null && StringUtil.isNotEmpty(subject)) {
            params.put("subject", "%" + subject + "%");
        }
        String starttime = RequestUtil.getString(request, "starttime");
        params.put("startTimes", starttime);
        String endtime = RequestUtil.getString(request, "endtime");
        params.put("endTimes", endtime);


        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRunNodeAvgTime = processRunService.getNodeAvgTime(params);

                for (ProcessRun processRun : processRunNodeAvgTime) {
                    JSONObject jo = new JSONObject();
                    jo.put("taskName", processRun.getTaskName());

                    double day = 0, hour = 0, minute = 0, second = 0;
                    double days = 0, hours = 0, minutes = 0, seconds = 0;
                    double h24 = 24, m60 = 60, s60 = 60;

                    day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
                    hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
                    minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
                    second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
                    //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
                    //天
                    days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
                    hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
                    day = days;
                    //小时
                    hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
                    minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
                    hour = hours;
                    //分钟
                    minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
                    second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
                    minute = minutes;
                    //秒
                    second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
                    while (hour >= 24 || minute >= 60 || second >= 60) {
                        if (hour >= 24) {
                            day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                            hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                        }
                        if (minute >= 60) {
                            hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                            minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                        }
                        if (second >= 60) {
                            minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                            second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                        }
                    }
                    //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
                    String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";
                    jo.put("time", Time);
                    data.add(jo);
                }

                return data;
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                params.put("isNeedRight", isNeedRight);
                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRunNodeAvgTime = processRunService.getNodeAvgTime(params);

        for (ProcessRun processRun : processRunNodeAvgTime) {
            JSONObject jo = new JSONObject();
            jo.put("taskName", processRun.getTaskName());

            double day = 0, hour = 0, minute = 0, second = 0;
            double days = 0, hours = 0, minutes = 0, seconds = 0;
            double h24 = 24, m60 = 60, s60 = 60;

            day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
            hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
            minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
            second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
            //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
            //天
            days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
            hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
            day = days;
            //小时
            hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
            minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
            hour = hours;
            //分钟
            minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
            second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
            minute = minutes;
            //秒
            second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
            while (hour >= 24 || minute >= 60 || second >= 60) {
                if (hour >= 24) {
                    day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                    hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                }
                if (minute >= 60) {
                    hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                    minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                }
                if (second >= 60) {
                    minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                    second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                }
            }
            //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
            String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";
            jo.put("time", Time);
            data.add(jo);
        }

        return data;
    }

    /**/
    @RequestMapping("getRycstj")
    @ResponseBody
    @Action(description = "审批人员超时统计")
    public JSONArray getRycstj(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        JSONArray data = new JSONArray();
        PageBean pb = new PageBean();
        pb.setCurrentPage(1);
        pb.setPagesize(999999);
        // 去掉进行分页的总记录数的查询
        pb.setShowTotal(false);

        params.put("nowTime", new Date());
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);
        String ssyxid = RequestUtil.getString(request, "ssyx");
        params.put("startOrgId", ssyxid);
        String starttime = RequestUtil.getString(request, "starttime");
        params.put("startTimes", starttime);
        String endtime = RequestUtil.getString(request, "endtime");
        params.put("endTimes", endtime);

        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {


            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRunRycstl = processRunService.getRycstj(params, pb);
                for (ProcessRun processRun : processRunRycstl) {
                    JSONObject jo = new JSONObject();
                    jo.put("name", processRun.getCreator());
                    jo.put("ssyx", processRun.getStartOrgName());
                    jo.put("splczs", processRun.getLaunchProcessesTotal());
                    jo.put("timeout", processRun.getProcessesOperationsTotal());
                    jo.put("csspl", processRun.getHandlingRate());

                    double day = 0, hour = 0, minute = 0, second = 0;
                    double days = 0, hours = 0, minutes = 0, seconds = 0;
                    double h24 = 24, m60 = 60, s60 = 60;

                    day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
                    hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
                    minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
                    second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
                    //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
                    //天
                    days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
                    hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
                    day = days;
                    //小时
                    hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
                    minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
                    hour = hours;
                    //分钟
                    minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
                    second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
                    minute = minutes;
                    //秒
                    second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
                    while (hour >= 24 || minute >= 60 || second >= 60) {
                        if (hour >= 24) {
                            day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                            hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                        }
                        if (minute >= 60) {
                            hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                            minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                        }
                        if (second >= 60) {
                            minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                            second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                        }
                    }
                    //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
                    String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";

                    jo.put("pjhs", Time);
                    data.add(jo);
                }

                return data;
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);

            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                params.put("isNeedRight", isNeedRight);
                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    //List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    List<String> processRuns = sysUserService.getExefullNameByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRunRycstl = processRunService.getRycstj(params, pb);
        for (ProcessRun processRun : processRunRycstl) {
            JSONObject jo = new JSONObject();
            jo.put("name", processRun.getCreator());
            jo.put("ssyx", processRun.getStartOrgName());
            jo.put("splczs", processRun.getLaunchProcessesTotal());
            jo.put("timeout", processRun.getProcessesOperationsTotal());
            jo.put("csspl", processRun.getHandlingRate());

            double day = 0, hour = 0, minute = 0, second = 0;
            double days = 0, hours = 0, minutes = 0, seconds = 0;
            double h24 = 24, m60 = 60, s60 = 60;

            day = Double.valueOf(processRun.getTimePerRequest().substring(0, processRun.getTimePerRequest().indexOf("天")));
            hour = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("天") + 1, processRun.getTimePerRequest().indexOf("小时")));
            minute = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("小时") + 2, processRun.getTimePerRequest().indexOf("分")));
            second = Double.valueOf(processRun.getTimePerRequest().substring(processRun.getTimePerRequest().indexOf("分") + 1, processRun.getTimePerRequest().indexOf("秒")));
            //System.out.println("获取的平均值值："+day+"|"+hour+"|"+minute+"|"+second);
            //天
            days += Double.valueOf(Double.toString(day).substring(0, Double.toString(day).indexOf(".")));
            hour += Double.valueOf(Double.toString(day).substring(Double.toString(day).indexOf("."), Double.toString(day).length())) * h24;
            day = days;
            //小时
            hours += Double.valueOf(Double.toString(hour).substring(0, Double.toString(hour).indexOf(".")));
            minute += Double.valueOf(Double.toString(hour).substring(Double.toString(hour).indexOf("."), Double.toString(hour).length())) * m60;
            hour = hours;
            //分钟
            minutes += Double.valueOf(Double.toString(minute).substring(0, Double.toString(minute).indexOf(".")));
            second += Double.valueOf(Double.toString(minute).substring(Double.toString(minute).indexOf("."), Double.toString(minute).length())) * s60;
            minute = minutes;
            //秒
            second = Double.valueOf(Double.toString(second).substring(0, Double.toString(second).indexOf("."))) + Math.round(Double.valueOf(Double.toString(second).substring(Double.toString(second).indexOf("."), Double.toString(second).length())));
            while (hour >= 24 || minute >= 60 || second >= 60) {
                if (hour >= 24) {
                    day += Double.valueOf(Double.toString(hour / 24).substring(0, Double.toString(hour / 24).indexOf(".")));
                    hour = Double.valueOf(Double.toString(hour / 24).substring(Double.toString(hour / 24).indexOf("."), Double.toString(hour / 24).length())) * h24;
                }
                if (minute >= 60) {
                    hour += Double.valueOf(Double.toString(minute / 60).substring(0, Double.toString(minute / 60).indexOf(".")));
                    minute = Double.valueOf(Double.toString(minute / 60).substring(Double.toString(minute / 60).indexOf("."), Double.toString(minute / 60).length())) * m60;
                }
                if (second >= 60) {
                    minute += Double.valueOf(Double.toString(second / 60).substring(0, Double.toString(second / 60).indexOf(".")));
                    second = Math.round(Double.valueOf(Double.toString(second / 60).substring(Double.toString(second / 60).indexOf("."), Double.toString(second / 60).length())));
                }
            }
            //System.out.println("改变后的值："+day+"|"+hour+"|"+minute+"|"+second);
            String Time = Math.round(day) + "天" + Math.round(hour) + "小时" + Math.round(minute) + "分";

            jo.put("pjhs", Time);
            data.add(jo);
        }

        return data;
    }

    @RequestMapping("getSxbltjxq")
    @Action(description = "查看事项办理统计详情")
    public ModelAndView getSxbltjxq(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/sxtj/statisticalDetails.jsp");
        long defId = RequestUtil.getLong(request, "defId", 0L);
        List<Position> positionsList = positionService.getAll();

        return mv.addObject("defId", defId).addObject("positionsList", positionsList);
    }

    @RequestMapping("statisticalDetails")
    @ResponseBody
    @Action(description = "查看事项办理统计详情")
    public JSONArray statisticalDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> sysUserIds = new ArrayList();
        long defId = RequestUtil.getLong(request, "defId", 0L);
        JSONArray data = new JSONArray();
        PageBean pb = new PageBean();
        pb.setCurrentPage(1);
        pb.setPagesize(999999);
        // 去掉进行分页的总记录数的查询
        pb.setShowTotal(false);

        params.put("defId", defId);
        params.put("nowTime", new Date());
        String date_format = "";
        String type = RequestUtil.getString(request, "type");
        if ("1".equals(type)) {
            date_format = DATE_DD;
        } else if ("2".equals(type)) {
            date_format = DATE_WI;
        } else if ("3".equals(type)) {
            date_format = DATE_MM;
        }
        params.put("date_format", date_format);
        String starttime = RequestUtil.getString(request, "starttime");
        params.put("startTimes", starttime);
        String endtime = RequestUtil.getString(request, "endtime");
        params.put("endTimes", endtime);
        String spzt = RequestUtil.getString(request, "spzt");
        params.put("status", spzt);
        String sfcq = RequestUtil.getString(request, "sfcq");
        params.put("handlingRate", sfcq);
        String ssyxid = RequestUtil.getString(request, "ssyx");
        params.put("timePerRequest", ssyxid);

        Long userId = ContextUtil.getCurrentUserId();
        String isNeedRight = "";
        String orgType = "";
        if (!ContextUtil.isSuperAdmin()) {

            //如果用户角色为管理员，则直接显示全部数据
            if (sysRoleService.getRoleIdByUserId(userId.toString()).contains("1")) {

                isNeedRight = "";
                orgType = "";
                params.put("superAdmin", "yes");

                List<ProcessRun> processRunBltjxq = processRunService.getSxbltjxq(params, pb);
                for (ProcessRun processRun : processRunBltjxq) {
                    JSONObject jo = new JSONObject();
                    jo.put("runId", processRun.getRunId());
                    jo.put("name", processRun.getSubject());
                    jo.put("service", processRun.getProcessName());
                    jo.put("department", processRun.getTimePerRequest());
                    jo.put("time", processRun.getLaunchProcessesTotal());
                    jo.put("status", processRun.getStatus());
                    jo.put("whether", processRun.getHandlingRate());
                    data.add(jo);
                }
                return data;
            }

            isNeedRight = "yes";
            // 查询当前用户是否是特定角色用户，并且是否是组织的负责人,则取到该组织下所有成员的userId
            sysUserIds = sysUserService.getOrgIdByUserId(userId);
            // 如果userID不为空，则获取userId所在的角色
            if (BeanUtils.isNotEmpty(sysUserIds)) {
                orgType = "yes";
                params.put("isNeedRight", isNeedRight);
                //创建者
                List<String> creator = new ArrayList<>();
                for (String sysUserId : sysUserIds) {
                    params.put("userId", sysUserId);
                    List<String> processRuns = sysUserService.getcreatorByUserId(Long.valueOf(sysUserId));
                    for (String processRun : processRuns) {
                        creator.add(processRun);
                    }
                    params.remove("userId");
                }
                //去除重复的用户姓名
                HashSet hash = new HashSet(creator);
                creator.clear();
                creator.addAll(hash);

                params.put("creators", creator);
            }
            params.put("orgType", orgType);
        } else {
            params.put("superAdmin", "yes");
        }

        List<ProcessRun> processRunBltjxq = processRunService.getSxbltjxq(params, pb);
        for (ProcessRun processRun : processRunBltjxq) {
            JSONObject jo = new JSONObject();
            jo.put("runId", processRun.getRunId());
            jo.put("name", processRun.getSubject());
            jo.put("service", processRun.getProcessName());
            jo.put("department", processRun.getTimePerRequest());
            jo.put("time", processRun.getLaunchProcessesTotal());
            jo.put("status", processRun.getStatus());
            jo.put("whether", processRun.getHandlingRate());
            data.add(jo);
        }
        return data;
    }

}
