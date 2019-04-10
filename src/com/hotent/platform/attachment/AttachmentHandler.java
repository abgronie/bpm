package com.hotent.platform.attachment;

import java.io.InputStream;
import java.io.OutputStream;

import com.hotent.platform.model.system.SysFile;

public interface AttachmentHandler {
	/**
	 * 获取附件处理器类型
	 * @return
	 */
	String getType();
	/**
	 * 删除附件
	 * @param sysFile
	 * @throws Exception
	 */
	void remove(SysFile sysFile) throws Exception;
	/**
	 * 上传附件
	 * @param sysFile
	 * @param inputStream
	 * @throws Exception
	 */
	void upload(SysFile sysFile, InputStream inputStream) throws Exception;
	/**
	 * 下载附件
	 * @param sysFile
	 * @param outStream
	 * @throws Exception
	 */
	void download(SysFile sysFile, OutputStream outStream) throws Exception;
}
