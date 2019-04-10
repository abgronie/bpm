package com.zfsoft;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hotent.core.util.AppConfigUtil;

public class OraBackup {
	
	   /**
	     * Java代码实现Oracle数据库导出
	     * 
	     * @author GaoHuanjie
	     * @param userName 进入数据库所需要的用户名
	     * @param password 进入数据库所需要的密码
	     * @param SID 用户所在的SID
	     * @param savePath 数据库导出文件保存路径
	     * @param fileName 数据库导出文件文件名
	     * @return 返回true表示导出成功，否则返回false。
	     */  
	    public static boolean exportDatabaseTool(String userName, String password, String SID, String savePath, String fileName) throws InterruptedException {
	        File saveFile = new File(savePath);
	        if (!saveFile.exists()) {// 如果目录不存在
	            saveFile.mkdirs();// 创建文件夹
	        }
	        try {
	            Process process = Runtime.getRuntime().exec("exp " + userName + "/" + password + "@" + SID + " file=" + savePath + "/" + fileName + ".dmp");
	            if(process.waitFor() == 0){//0 表示线程正常终止。 
	                return true;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public static void main(String[] args) throws InterruptedException {
	    	/*String username = AppConfigUtil.get("jdbc.username");
	    	String password = AppConfigUtil.get("jdbc.password");
	    	String sid = AppConfigUtil.get("jdbc.sid");
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    	String date = sdf.format(new Date());
	        if (exportDatabaseTool(username, password, sid, "d:/BackupDatabase", username+"_"+date)) {
	            System.out.println("数据库成功备份！！！");
	        } else {
	            System.out.println("数据库备份失败！！！");
	        }*/
	    	
	    	
	    	
	    }
}
