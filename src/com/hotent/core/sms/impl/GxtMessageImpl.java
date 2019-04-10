package com.hotent.core.sms.impl;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.sms.IShortMessage;
import com.hotent.core.sms.impl.SendMsgStub.SendMsgResponse;
import com.hotent.core.util.AppConfigUtil;

public class GxtMessageImpl implements IShortMessage {
	 private static GxtMessageImpl instance;
	 private static Lock lock = new ReentrantLock();
	 protected Logger logger = LoggerFactory.getLogger(GxtMessageImpl.class);
	 String smsUrl;
	 String smsUserID;
	 String smsPassword;
	 private static final SendMsgStub sendMsgStub = getServicePD();
		
		public static final String QUEUE_ID_NAME = "queueIDName";
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/**
		 * 得到远程连接的映射
		 * 
		 * @return
		 */
		private static SendMsgStub getServicePD() {
			SendMsgStub stock = null;
			try {
				stock = new SendMsgStub();
			} catch (Exception e) {
				e.getStackTrace();
			}
			return stock;
		}
	public boolean sendSms(List<String> paramList, String paramString) {
		initial();
		boolean res = false;
	    String telemobileStr = getMobileList(paramList);
	    try {
//	      Client client = new Client(new URL(this.smsUrl));
//	      Object[] result1 = client.invoke("sendMsg", new Object[] { this.smsUserID, this.smsPassword, telemobileStr,paramString });
//	      System.out.println("###########################");
//	      System.out.println("result:"+result1[0]);
//	      System.out.println("###########################");
//	      System.out.println("result:"+result1.toString()+"***result.size"+result1.length);
//	      logger.error(result1[0].toString());
	      SendMsgStub.SendMsg sendParam = new SendMsgStub.SendMsg();
	      sendParam.setIn0(this.smsUserID);
	      sendParam.setIn1(this.smsPassword);
	      sendParam.setIn2(telemobileStr);
	      sendParam.setIn3(paramString);
	      SendMsgResponse sendResponse = null;
	      // 远程调用
			try {
				sendResponse = sendMsgStub.sendMsg(sendParam);
			} catch (Exception e) {
				throw new Exception("远程接口调用失败...." + e.getMessage(), e);
			}

			// 返回结果
		  String orderCallerResult = sendResponse.getOut();
		  
	      if (orderCallerResult.equals("00000"))
	        res = true;
	    }
	    catch (MalformedURLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return Boolean.valueOf(res);
	}
	
	public void initial(){
		this.smsUrl =  AppConfigUtil.get("smsUrl");
		this.smsUserID =  AppConfigUtil.get("smsUserID");
		String PWD =  AppConfigUtil.get("smsPassword");
		GxtMD5pwd md5 = new GxtMD5pwd();
		this.smsPassword  = md5.md5pwd(PWD);
	 }
	
	public static GxtMessageImpl getInstance(){
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null)
					instance = new GxtMessageImpl();
			} finally {
				lock.unlock();
			}
		}
		return instance;
	   }
	 public boolean isMobile(String mobiles) {
		    String regexMobile = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
		    Pattern p = Pattern.compile(regexMobile);
		    Matcher m = p.matcher(mobiles);
		    return m.matches();
		}

	 public String getMobileList(List<String> tmpMobiles) {
		String[] array = new String[tmpMobiles.size()] ;
	    String newStrJoin = ""; 
        for (int i = 0; i < tmpMobiles.size(); i++) {
          if (tmpMobiles.get(i).length() == 11 && (isMobile(tmpMobiles.get(i)))) {
        	  array[i]=tmpMobiles.get(i);
          }
        }
        newStrJoin = StringUtils.join(array, ",");
	    return newStrJoin;
	  }
	 public static void main(String[] args ){
		 List<String> list = new ArrayList<String>();
		 list.add("13656561657");
		 GxtMessageImpl gxt = new GxtMessageImpl();
		 GxtMD5pwd md5 = new GxtMD5pwd();
		 System.out.println(md5.md5pwd("12345678"));
		 System.out.println(true||false);
	 }
}