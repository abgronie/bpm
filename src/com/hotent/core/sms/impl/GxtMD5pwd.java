package com.hotent.core.sms.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hotent.platform.web.filter.MD5;

public class GxtMD5pwd {
	public String md5pwd(String i_pwd)
	  {
	    long time = new Date().getTime();
	    Date date = new Date(time);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String time1 = sdf.format(date);
	    int timeNow = Integer.parseInt(time1);
	    int loginpwd = Integer.parseInt(i_pwd);
	    DecimalFormat countFormat = new DecimalFormat("000000");
	    String num = countFormat.format(timeNow & loginpwd);
	    String[] a = new String[6];
	    int y = 0;
	    for (int i = num.length() - 1; i >= num.length() - 6; i--)
	    {
	      a[(5 - y)] = String.valueOf(num.charAt(i));

	      y++;
	    }
	    String c = "";
	    for (int i = 0; i < a.length; i++) {
	      c = c + a[i];
	    }

	    MD5 md5 = new MD5();
	    String strMd5 = md5.getMD5ofStr(c);
	    strMd5 = strMd5.toLowerCase();
	    System.out.println(strMd5);
	    return strMd5;
	  }
}
