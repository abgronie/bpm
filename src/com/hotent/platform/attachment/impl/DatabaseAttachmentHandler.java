package com.hotent.platform.attachment.impl;

import java.io.InputStream;
import java.io.OutputStream;

import com.hotent.core.util.FileUtil;
import com.hotent.platform.attachment.AttachmentHandler;
import com.hotent.platform.model.system.SysFile;

public class DatabaseAttachmentHandler implements AttachmentHandler{

	public String getType() {
		return "database";
	}

	public void remove(SysFile sysFile) throws Exception {
		// 数据库存放附件模式，直接删除数据库中的记录即可
	}

	public void upload(SysFile sysFile, InputStream inputStream) throws Exception {
		// 上传附件时，将附件字节流设置到SysFile的FileBlob属性中
		sysFile.setFileBlob(FileUtil.readByte(inputStream));
	}

	public void download(SysFile sysFile, OutputStream outStream) throws Exception {
		//获取附件字节数组
		byte[] fileBlob = sysFile.getFileBlob();
		try{
			outStream.write(fileBlob);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if (outStream != null) {
				outStream.close();
				outStream = null;
			}
		}
	}
}
