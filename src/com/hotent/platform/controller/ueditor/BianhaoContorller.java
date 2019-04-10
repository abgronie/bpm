package com.hotent.platform.controller.ueditor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.table.TableModel;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.annotion.Action;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.share.SysShareRightsService;
import net.sf.json.JSONObject;

	/**
	 * 对象功能:数据功能权限分享 控制器类
	 */
	@Controller	
	@RequestMapping("/platform/ueditor/bianhao/")
	@Action(ownermodel = SysAuditModelType.FORM_MANAGEMENT)
public class BianhaoContorller extends BaseController{

	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	@Resource
	private BpmFormDefService bpmFormDefService;
	
	@Resource
	private BpmFormTableService bpmFormTableService;
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("set")
	@Action(description = "查看编号")
	@ResponseBody
	public String getPermissionByRule(HttpServletRequest request, HttpServletResponse response, Long defId) throws Exception{
		String bianhao = "";
		BpmDefinition bpmDefinition = null;
		bpmDefinition = bpmDefinitionService.getById(defId);
		if(bpmDefinition != null && !"".equals(bpmDefinition)){
			String actDefId = bpmDefinition.getActDefId();
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getStartBpmNodeSet(actDefId, false);
			if(bpmNodeSet != null && !"".equals(bpmNodeSet)){
				String formKey = bpmNodeSet.getFormKey();
				BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(formKey);
				if(bpmFormDef != null && !"".equals(bpmFormDef)){
					Long tableId = bpmFormDef.getTableId();
					BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
					if(bpmFormTable != null && !"".equals(bpmFormTable)){
						String tableName = bpmFormTable.getTableName();
						if (!tableName.startsWith(TableModel.CUSTOMER_TABLE_PREFIX)) {
							tableName = TableModel.CUSTOMER_TABLE_PREFIX + tableName;
						}
						bianhao = getByKey(tableName);
					}
				}
			}
		}
		return bianhao;
	}
	
	/**
	 * 根据表名和主键获取一行数据。
	 * @param tableName
	 * @param pk
	 * @return
	 * @throws Exception 
	 */
	public String getByKey(String tableName) throws Exception {
		JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getNewJdbcTemplate("");
		String sql = "select a.f_bianhao from " + tableName + " a  where a.f_bianhao=(select max(f_bianhao) from " + tableName + ")  ";
		Map<String, Object> map = null;
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String bianhao =df.format(day);
		String oldbianhao = null;
		try {
			try {
			map = jdbcTemplate.queryForMap(sql);
			}catch (EmptyResultDataAccessException e) {
				map = null;
			}
			
			if(map==null) {	
				bianhao = bianhao+"0001";
			}else {
				oldbianhao=(String) map.get("F_BIANHAO");
				bianhao=bianhao+String.format("%4d", (Integer.parseInt(oldbianhao.substring(oldbianhao.length()-4))+1)).replace(" ", "0");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bianhao;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "admin/10000000781478.doc";
		String wordName = File.separator;
		System.out.println(wordName);   
	}

}
