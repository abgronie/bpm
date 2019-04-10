package com.hotent.platform.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.cache.ICache;
import com.hotent.core.sms.impl.ModemMessage;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.system.SysIndexColumnService;

/**
 * 监控服务器启动和关闭事件。
 * <pre>
 * 1.服务器启动时，调用初始化系统模版事件。
 * 2.服务器关闭是，停止短信猫事件。
 * </pre>
 * @author ray
 *
 */
public class ServerListener implements ServletContextListener {
    	private Log logger = LogFactory.getLog(ServerListener.class);
	public void contextDestroyed(ServletContextEvent event) {
	    ModemMessage.getInstance().stopService();	    
	    logger.debug("[contextDestroyed]停止短信猫服务。");
	    
	    /**
		 * 清理缓存。
		 * 有可能是memcached缓存，在结束时需要清除缓存。
		 */
	    ICache icache=AppUtil.getBean(ICache.class);
		icache.clearAll();
	}

	public void contextInitialized(ServletContextEvent event) {
		logger.debug("---------[contextInitialized]开始初始化表单模版。");
		//初始化系统模版。
		BpmFormTemplateService.initTemplate();
		
		logger.debug("--------[contextInitialized]初始化表单模版成功。");
		
		logger.debug("[contextInitialized]开始初始化首页栏目。");
		SysIndexColumnService.initIndex();

		logger.debug("[contextInitialized]初始化首页栏目成功。");
		
		
		
	}

}
