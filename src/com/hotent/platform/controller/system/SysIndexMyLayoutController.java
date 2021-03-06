package com.hotent.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.annotion.Action;
import com.hotent.platform.model.system.SysIndexColumn;
import com.hotent.platform.model.system.SysIndexLayout;
import com.hotent.platform.model.system.SysIndexMyLayout;
import com.hotent.platform.service.system.SysIndexColumnService;
import com.hotent.platform.service.system.SysIndexLayoutService;
import com.hotent.platform.service.system.SysIndexMyLayoutService;
/**
 *<pre>
 * 对象功能:我的布局 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:39:48
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysIndexMyLayout/")
public class SysIndexMyLayoutController extends BaseController
{
	@Resource
	private SysIndexMyLayoutService sysIndexMyLayoutService;
	@Resource
	private SysIndexLayoutService sysIndexLayoutService;
	@Resource
	private SysIndexColumnService sysIndexColumnService;
	
	/**
	 * 添加或更新我的布局。
	 * @param request
	 * @param response
	 * @param sysIndexMyLayout 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新我的布局")
	public void save(HttpServletRequest request, HttpServletResponse response,SysIndexMyLayout sysIndexMyLayout) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysIndexMyLayout.getId()==null||sysIndexMyLayout.getId()==0){
				sysIndexMyLayout.setId(UniqueIdUtil.genId());
				sysIndexMyLayoutService.add(sysIndexMyLayout);
				resultMsg=getText("添加","我的布局");
			}else{
			    sysIndexMyLayoutService.update(sysIndexMyLayout);
				resultMsg=getText("更新","我的布局");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得我的布局分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看我的布局分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysIndexMyLayout> list=sysIndexMyLayoutService.getAll(new QueryFilter(request,"sysIndexMyLayoutItem"));
		ModelAndView mv=this.getAutoView().addObject("sysIndexMyLayoutList",list);
		
		return mv;
	}
	
	/**
	 * 删除我的布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除我的布局")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysIndexMyLayoutService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除我的布局成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑我的布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑我的布局")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysIndexMyLayout sysIndexMyLayout=sysIndexMyLayoutService.getById(id);
		
		return getAutoView().addObject("sysIndexMyLayout",sysIndexMyLayout)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得我的布局明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看我的布局明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysIndexMyLayout sysIndexMyLayout = sysIndexMyLayoutService.getById(id);	
		return getAutoView().addObject("sysIndexMyLayout", sysIndexMyLayout);
	}
	
	/**
	 * 设计我的首页布局
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design")
	@Action(description="设计我的首页布局")
	public ModelAndView design(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId= ContextUtil.getCurrentUserId();
		//首页布局
		List<SysIndexLayout> layoutList = sysIndexLayoutService.getAll();
		QueryFilter filter =new QueryFilter(request, "sysIndexMyLayout");
		Map<String,Object>  params  =  RequestUtil.getParameterValueMap(request);
		//首页栏目，取出来需要解析
		List<SysIndexColumn>  columnList = sysIndexColumnService.getHashRightColumnList(filter,params,true);
		//获取展示的布局
		Map<String,List<SysIndexColumn>> columnMap = sysIndexColumnService.getColumnMap(columnList);
		//获取当前的布局
		SysIndexMyLayout sysIndexMyLayout = sysIndexMyLayoutService.getLayoutList(userId,columnList);	
		
		return getAutoView().addObject("layoutList",layoutList)
				.addObject("columnMap",columnMap)
				.addObject("sysIndexMyLayout", sysIndexMyLayout);
	}
	
	/**
	 * 保存首页布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveLayout")
	@Action(description="保存首页布局")
	public void saveLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String html =  RequestUtil.getString(request, "html");
		String designHtml =  RequestUtil.getString(request, "designHtml");
		ResultMessage resultObj = null;
	try {
		sysIndexMyLayoutService.save(html,designHtml);
		resultObj = new ResultMessage(ResultMessage.Success,"保存成功");	
	} catch (Exception e) {
		resultObj = new ResultMessage(ResultMessage.Fail, e.getMessage());	
	}
	 response.getWriter().print(resultObj);
	}
	/**
	 * 删除首页布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("deleteLayout")
	@Action(description="保存首页布局")
	public void deleteLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id =  RequestUtil.getLong(request, "id");
		ResultMessage resultObj = null;
	try {
		sysIndexMyLayoutService.delById(id);
		resultObj = new ResultMessage(ResultMessage.Success,"删除成功");	
	} catch (Exception e) {
		resultObj = new ResultMessage(ResultMessage.Fail, e.getMessage());	
	}
	 response.getWriter().print(resultObj);
	}
	
	
}
