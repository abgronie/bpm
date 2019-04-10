package com.zfsoft.timetask;
import java.util.Calendar;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hotent.core.scheduler.SchedulerService;

public class TempFileListener
  implements ServletContextListener
{
  private Timer timer;
  private SystemTaskTest systemTask;


  public void contextInitialized(ServletContextEvent sce)
  {
	//通过WebApplication获得定时器服务对象
  	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
  	SchedulerService schedulerService=context.getBean(SchedulerService.class);
  	try {
  		//启动定时计划
		schedulerService.start();
		System.out.println("启动定时计划成功");
	} catch (SchedulerException e) {
		e.printStackTrace();
	}
    this.timer = new Timer();
    this.systemTask = new SystemTaskTest(sce.getServletContext()
      .getRealPath("/"), sce.getServletContext());
    try {
      sce.getServletContext().log("定时器已启动");

      Calendar calendar = Calendar.getInstance();
      calendar.set(11, 24);
      calendar.set(12, 28);
      calendar.set(13, 0);

      Long time = Long.valueOf(Long.parseLong("1000") * 1000L);

      this.timer.schedule(this.systemTask, 10000L, time.longValue());
      sce.getServletContext().log("已经添加任务调度表");
    } catch (Exception localException) {
    }
  }

  public void contextDestroyed(ServletContextEvent sce) {
    try {
      this.timer.cancel();
    }
    catch (Exception localException)
    {
    }
  }
  
  class SystemTaskTest extends TimerTask
  {
    private ServletContext context;
    private String path;


    public SystemTaskTest(String path, ServletContext context)
    {
      this.path = path;
      this.context = context;
    }

    public void run()
    {
      try
      {
        this.context.log("开始执行任务!");

        this.context.log("指定任务执行完成!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}