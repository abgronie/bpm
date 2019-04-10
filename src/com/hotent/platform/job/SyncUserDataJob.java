/**  
 * @Title: SyncUserDataJob.java 
 * @Package com.jinfuzi.erp.job 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zyl  
 * @date 2015年5月11日 上午11:30:34 
 * @version V1.0  
 */ 
package com.hotent.platform.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.share.DataSourceHelper;


/** 
 * @ClassName: SyncUserDataJob 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zyl 
 * @date 2015年5月11日 上午11:30:34  
 */

public class SyncUserDataJob extends BaseJob 
{
	
	
    /**
     * @param context
     * @throws Exception 
     * @see com.hotent.core.scheduler.BaseJob#executeJob(org.quartz.JobExecutionContext) 
     */ 
    @Override
    public void executeJob(JobExecutionContext context)
        throws Exception
    {
    	String sql = "select * from sys_user t where t.update > ";
    	List page = JdbcTemplateUtil.getPage("x5", sql, null, new PageBean(1, 20));
    	System.out.println(page);
    	for (Object object : page) {
    		SysUser user = new SysUser();
    		
		}
    }
    
}


