package com.hotent.platform.service.jms.impl;

import java.io.*;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import com.alibaba.fastjson.JSON;
import com.zfsoft.zjsrsms.SubmitReq;
import esign.http.BASE64Encoder;
import esign.util.MD5Util;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.fusesource.hawtbuf.ByteArrayInputStream;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.jms.IMessageHandler;
import com.hotent.core.model.MessageModel;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.MessageLog;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.MessageEngine;
import com.hotent.platform.service.system.MessageLogService;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.util.MessageUtil;
import com.hotent.platform.service.util.ServiceUtil;
import com.zfsoft.util.Tool;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsStatusPuller;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.qcloudsms.SmsMobileStatusPuller;
import com.github.qcloudsms.SmsStatusPullCallbackResult;
import com.github.qcloudsms.SmsStatusPullReplyResult;
import com.github.qcloudsms.SmsVoiceVerifyCodeSender;
import com.github.qcloudsms.SmsVoiceVerifyCodeSenderResult;
import com.github.qcloudsms.SmsVoicePromptSender;
import com.github.qcloudsms.SmsVoicePromptSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import org.json.JSONException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import org.codehaus.xfire.client.Client;

public class SmsMessageHandler implements IMessageHandler {

	private final Log logger = LogFactory.getLog(SmsMessageHandler.class);
	@Resource
	private MessageEngine messageEngine;
	@Resource
	SysTemplateService sysTemplateService;

	@Override
	public String getTitle() {
		return "短信";
		//return ContextUtil.getMessages("message.sms");
	}

	@Override
	public void handMessage(MessageModel model) {
		String strMobile = "";
		if (model.getReceiveUser() != null)
			strMobile =((SysUser) model.getReceiveUser()).getMobile();
		//手机号为空或不是手机号，直接返回
		if (StringUtil.isEmpty(strMobile) || !StringUtil.isMobile(strMobile)) return;
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(strMobile);

		if(StringUtils.isBlank(model.getStartUser())){
			String subject = model.getSubject();
			if(StringUtils.isNotBlank(subject)){
				String [] sub = subject.split("-");
				String startUser = sub[1];
				model.setStartUser(startUser);
			}
		}

		//messageEngine.sendSms(mobiles, MessageUtil.getContent(model,false));
		//sendSmsGZDX(mobiles, model);
		//sendSmsZF(mobiles, MessageUtil.getContent(model,false));
		//sendSmsSHTY(mobiles, MessageUtil.getContent(model,false));
		//sendSmsSDKJZY(mobiles, model);
		//sendSmsZJSR(mobiles, MessageUtil.getContent(model,false));
		//sendSmsZyy(mobiles, MessageUtil.getContent(model,false));
		logger.debug("+++++++++++++++++++++++++++++++SMS++++++++++++++++++++++++++++++++++++++");
	}

	/**
	 * 中医药短信
	 * @param mobiles
	 * @param content
	 * 此方法是调用短信发送接口
	 * 参数：一共有四个，第一个是协议中的loginName，第二个是协议中加过密之后的loginPWD,第三个是短信人号码，第四个是短信内容
	 */
	/*public static void sendSmsZyy(List<String> mobiles, String content){
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)){
			String yhm = "0571026";
			String pwd = "123456";
			String pwdmd5 = MD5(pwd);
			String mobile = "";
			String zyybt = "【中医药办事大厅】";
			String message = zyybt+content;
			Integer state = MessageLog.STATE_SUCCESS;
			MessageLogService messageLogService = (MessageLogService) AppUtil.getBean(MessageLogService.class);
			try {
				//一般如果没有注解targetNamespace的话，默认生成的就是接口这个文件的路径名
				String zyytargetNamespace = AppConfigUtil.get("zyytargetNamespace");
				String zyysmsurl = AppConfigUtil.get("zyysmsurl");
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(new URL(zyysmsurl));
				//指定接口路径，要调用的方法名
				call.setOperationName(new QName(zyytargetNamespace, "simpleCreateMessageWithMobile"));
				//如果没用@WebParam(name="name")来表明参数名，则方法的入参是啥，这边就必须传一样的参数名才行。不然报错。
				call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("in2", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("in3", XMLType.XSD_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.XSD_LONG);
				for (int i = 0; i < mobiles.size(); i++) {
					mobile = mobiles.get(i);
					Object[] obj = new Object[] { yhm, pwdmd5, mobile, message };
					Long result = (Long) call.invoke(obj);
					System.out.println(result);
					if(0 == result){
						state = MessageLog.STATE_FAIL;
					}
					messageLogService.addMessageLog(message, mobile, MessageLog.MOBILE_TYPE, state);
				}

				Client client = new Client(new URL("http://60.191.116.236:8080/gxtMsgService/services/SendMsg?wsdl"));
				Object[] results = client.invoke("sendMsg", new Object[] { yhm, pwdmd5, mobile, message });
				System.out.println((String) results[0]);
			} catch (Exception e) {
				e.printStackTrace();
				state = MessageLog.STATE_FAIL;
				messageLogService.addMessageLog(message, StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
			}
		}
	}*/

	/**
	 * 山东科技职业学院(阿里云和电信接口)
	 * @param mobiles
	 * @param model
	 */
	/*public void sendSmsSDKJZY(List<String> mobiles,MessageModel model) {
		String flag = AppConfigUtil.get("appflag");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("flag="+flag);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		if("true".equals(flag)){
			final String sdkjzysmsurl = AppConfigUtil.get("sdkjzysmsurl");
			final String accessKeyId = AppConfigUtil.get("accessKeyId");
			final String accessKeySecret = AppConfigUtil.get("accessKeySecret");
			final String signName = AppConfigUtil.get("signName");
			Integer state = MessageLog.STATE_SUCCESS;
			try {
				String message = MessageUtil.getContent(model,false);
				String dianXin = "";
				for (String mobile : mobiles) {
					String url = "https://cx.shouji.360.cn/phonearea.php?number="+mobile;
					String result = httpPost(url, "");
					JSONObject json = JSONObject.parseObject(result);
					String data = json.getString("data");
					JSONObject jsonData = JSONObject.parseObject(data);
					String sp = jsonData.getString("sp");
					System.out.println("url="+url+";result="+result);
					System.out.println("data="+data+";sp="+sp);

					if("移动".equals(sp) || "联通".equals(sp)){
						//设置超时时间-可自行调整
						System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
						System.setProperty("sun.net.client.defaultReadTimeout", "10000");
						//初始化ascClient需要的几个参数
						final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
						final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
						//替换成你的AK
						//final String accessKeyId = "LTAIEY9XKzztJTZC";//你的accessKeyId,参考本文档步骤2
						//final String accessKeySecret = "8wL5D8Tiq9Uu6oBs5wU6EHUOKt7yIl";//你的accessKeySecret，参考本文档步骤2
//						final String accessKeyId = "AccessKeySecret";
//						final String accessKeySecret ="dZnv6Ra6Pr0xVevi4hBJnIkW2syUfY";
						//初始化ascClient,暂时不支持多region（请勿修改）
						IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
						accessKeySecret);
						DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
						IAcsClient acsClient = new DefaultAcsClient(profile);
						 //组装请求对象
						 SendSmsRequest request = new SendSmsRequest();
						 //使用post提交
 						 request.setMethod(MethodType.POST);
						 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
						 request.setPhoneNumbers(mobile);
						 //必填:短信签名-可在短信控制台中找到
						 //request.setSignName("山科");
						 request.setSignName(signName);
						 //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
						 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
						 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
						 String templateId = "SMS_152283165";
						 if(model.getTemplateMap().get("html").contains("跟进事项通知")){
							templateId = "SMS_152283182";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("事项到期提醒")){
							templateId = "SMS_152288048";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("审批提醒")){
							templateId = "SMS_152283165";
							//request.setTemplateParam("{\"sxmc\":\"测试状态-byq测试流程-超级管理员-2018-12-07 10:35:22\"}");
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("撤销提醒")){
							templateId = "SMS_152283154";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("终止原因")){
							templateId = "SMS_152288016";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("退回提醒")){
							templateId = "SMS_152283145";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("归档提醒")){
							templateId = "SMS_152283143";
							request.setTemplateParam("{\"name\":\""+model.getSubject()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("催办提醒")){
							templateId = "SMS_152288001";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						 }else if(model.getTemplateMap().get("html").contains("任务被查看")){
							templateId = "SMS_152283134";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"czr\":\""+model.getOpinion()+"\"}");
						 }
						 request.setTemplateCode(templateId);

						 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
						 //request.setSmsUpExtendCode("90997");
						 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
						 request.setOutId("yourOutId");
						//请求失败这里会抛ClientException异常
						SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
						if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
							//请求成功
							state = MessageLog.STATE_SUCCESS;
						}else{
							state = MessageLog.STATE_FAIL;
						}
					}else if("电信".equals(sp)){
						dianXin += mobile + ";";
						//String sdkjzysmsurl = AppConfigUtil.get("sdkjzysmsurl");
						String action = "Uid=Zj189&Pwd=zj189xyzx&Timelapse=";
						String resultDX = httpPost(sdkjzysmsurl, action+"&Address="+dianXin+"&Content="+message);
						resultDX = URLDecoder.decode(resultDX, "UTF-8");
						System.out.println("resultDX="+resultDX);
						if(resultDX.contains("号码受限")){
							state = MessageLog.STATE_FAIL;
							message = resultDX;
						}else{
							state = MessageLog.STATE_SUCCESS;
						}
					}
					// 保存发送消息日志
					MessageLogService messageLogService = (MessageLogService) AppUtil.getBean(MessageLogService.class);
					messageLogService.addMessageLog(message, mobile, MessageLog.MOBILE_TYPE, state);
				}
			} catch (Exception e) {
				state = MessageLog.STATE_FAIL;
				e.printStackTrace();
				// 保存发送消息日志
				MessageLogService messageLogService = (MessageLogService) AppUtil
						.getBean(MessageLogService.class);
				messageLogService.addMessageLog("",
								StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
			}
	    }
	}*/

	/*public void sendSmsSDKJZY(List<String> mobiles,MessageModel model) {
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)){
			final String sdkjzysmsurl = AppConfigUtil.get("sdkjzysmsurl");
			final String accessKeyId = AppConfigUtil.get("accessKeyId");
			final String accessKeySecret = AppConfigUtil.get("accessKeySecret");
			final String signName = AppConfigUtil.get("signName");
			Integer state = MessageLog.STATE_SUCCESS;
			try {
				String message = MessageUtil.getContent(model,false);
				for (String mobile : mobiles) {
						//设置超时时间-可自行调整
						System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
						System.setProperty("sun.net.client.defaultReadTimeout", "10000");
						//初始化ascClient需要的几个参数
						final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
						final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
						//替换成你的AK
//						final String accessKeyId = "LTAIEY9XKzztJTZC";//你的accessKeyId,参考本文档步骤2
//						final String accessKeySecret = "8wL5D8Tiq9Uu6oBs5wU6EHUOKt7yIl";//你的accessKeySecret，参考本文档步骤2
//						final String accessKeyId = "AccessKeySecret";
//						final String accessKeySecret ="dZnv6Ra6Pr0xVevi4hBJnIkW2syUfY";
						//初始化ascClient,暂时不支持多region（请勿修改）
						IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
								accessKeySecret);
						DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
						IAcsClient acsClient = new DefaultAcsClient(profile);
						//组装请求对象
						SendSmsRequest request = new SendSmsRequest();
						//使用post提交
						request.setMethod(MethodType.POST);
						//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
						request.setPhoneNumbers(mobile);
						//必填:短信签名-可在短信控制台中找到
						//request.setSignName("山科");
						request.setSignName(signName);
						//必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
						//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
						//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
						String templateId = "SMS_152283165";
						if(model.getTemplateMap().get("html").contains("跟进事项通知")){
							templateId = "SMS_152283182";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("事项到期提醒")){
							templateId = "SMS_152288048";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("审批提醒")){
							templateId = "SMS_152283165";
							//request.setTemplateParam("{\"sxmc\":\"测试状态-byq测试流程-超级管理员-2018-12-07 10:35:22\"}");
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("撤销提醒")){
							templateId = "SMS_152283154";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("终止原因")){
							templateId = "SMS_152288016";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("退回提醒")){
							templateId = "SMS_152283145";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("归档提醒")){
							templateId = "SMS_152283143";
							request.setTemplateParam("{\"name\":\""+model.getSubject()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("催办提醒")){
							templateId = "SMS_152288001";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"yx\":\""+model.getOpinion()+"\"}");
						}else if(model.getTemplateMap().get("html").contains("任务被查看")){
							templateId = "SMS_152283134";
							request.setTemplateParam("{\"sxmc\":\""+model.getSubject()+"\", \"czr\":\""+model.getOpinion()+"\"}");
						}
						request.setTemplateCode(templateId);

						//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
						//request.setSmsUpExtendCode("90997");
						//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
						request.setOutId("yourOutId");
						//请求失败这里会抛ClientException异常
						SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
						if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
							//请求成功
							state = MessageLog.STATE_SUCCESS;
						}else{
							state = MessageLog.STATE_FAIL;
						}

						// 保存发送消息日志
						MessageLogService messageLogService = (MessageLogService) AppUtil.getBean(MessageLogService.class);
						messageLogService.addMessageLog(message, mobile, MessageLog.MOBILE_TYPE, state);
				}
			} catch (Exception e) {
				state = MessageLog.STATE_FAIL;
				e.printStackTrace();
				// 保存发送消息日志
				MessageLogService messageLogService = (MessageLogService) AppUtil
						.getBean(MessageLogService.class);
				messageLogService.addMessageLog("",
						StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("state="+state);
			System.out.println("STATE_SUCCESS = 1");
			System.out.println("STATE_FAIL = 0");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}*/

	/**
	 * 上海体育学院(http接口)
	 * @param mobiles
	 * @param content
	 */
	/*public static void sendSmsSHTY(List<String> mobiles, String content) {
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)){
			// 保存发送消息日志
			Boolean res = true;
			MessageLogService messageLogService = (MessageLogService) AppUtil.getBean(MessageLogService.class);
			Integer state = MessageLog.STATE_SUCCESS;
			//String shtybt = AppConfigUtil.get("shtybt");
			String shtybt = "【上体办事大厅】";
			String message = shtybt+content;
			try {
				//一般如果没有注解targetNamespace的话，默认生成的就是接口这个文件的路径名
		        String shtytargetNamespace = AppConfigUtil.get("shtytargetNamespace");
		        String shtysmsurl = AppConfigUtil.get("shtysmsurl");
		        Service service = new Service();
		        Call call = (Call) service.createCall();
		        call.setTargetEndpointAddress(new URL(shtysmsurl));
		        //指定接口路径，要调用的方法名
		        call.setOperationName(new QName(shtytargetNamespace, "simpleCreateMessageWithMobile"));
		        //如果没用@WebParam(name="name")来表明参数名，则方法的入参是啥，这边就必须传一样的参数名才行。不然报错。
		        call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);
		        call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);
		        call.setReturnType(XMLType.XSD_LONG);
		        String mobile = "";sendMessage
		        for (int i = 0; i < mobiles.size(); i++) {
		        	mobile = mobiles.get(i);
		        	Object[] obj = new Object[] { mobile, message };
		        	Long result = (Long) call.invoke(obj);
		        	System.out.println(result);
		        	if(0 == result){
		        		state = MessageLog.STATE_FAIL;
		        	}
		        	messageLogService.addMessageLog(message, mobile, MessageLog.MOBILE_TYPE, state);
				}
			} catch (Exception e) {
				e.printStackTrace();
				state = MessageLog.STATE_FAIL;
				res = false;
				messageLogService.addMessageLog(message, StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
			}
	    }
	}*/

	/**
	 * 公司短信接口(http接口)
	 * @param mobiles
	 * @param content
	 */
	/*public static void sendSmsZF(List<String> mobiles, String content) {
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)){
			Integer state = MessageLog.STATE_SUCCESS;
			String message = "【正方软件】"+content;
			String zfsmsurl = AppConfigUtil.get("zfsmsurl");
			String action = "action=send&userid=3185&account=zhengfang&password=zf201805&sendTime=&extno=";
			String result = httpPost(zfsmsurl, action+"&mobile="+mobiles+"&content="+message);

			try {
				Document document = new SAXReader().read(new ByteArrayInputStream(result.getBytes("utf-8")));
				Map<Integer, String> map = new HashMap<Integer, String>();
				int i = 0;
				Element element = document.getRootElement();
				Iterator iterator=element.elementIterator();
				while(iterator.hasNext()){
					i += 1;
					Element ele=(Element) iterator.next();
					map.put(i, ele.getText());
				}
				System.out.println("returnstatus="+map.get(1));
				System.out.println("message="+map.get(2));
				System.out.println("remainpoint="+map.get(3));
				System.out.println("taskID="+map.get(4));
				System.out.println("successCounts="+map.get(5));
				if("Faild".equals(map.get(1))){
					state = MessageLog.STATE_FAIL;
					message = map.get(2);
				}
				System.out.println("短信发送成功！！！！！！！！！！");
				System.out.println("短信发送成功！！！！！！！！！！");
				System.out.println("短信发送成功！！！！！！！！！！");
			} catch (DocumentException e) {
				e.printStackTrace();
				state = MessageLog.STATE_FAIL;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				state = MessageLog.STATE_FAIL;
			}
			// 保存发送消息日志
			MessageLogService messageLogService = (MessageLogService) AppUtil
					.getBean(MessageLogService.class);
			messageLogService.addMessageLog(message,
							StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
	    }
	}*/

	/**
	 * 贵州大学短信接口(阿里云)
	 */
	/*public void sendSmsGZDX(List<String> mobiles,MessageModel model) {
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)){
			Integer state = MessageLog.STATE_SUCCESS;
			int appid = Integer.parseInt(AppConfigUtil.get("appid"));
			String appkey = AppConfigUtil.get("appkey");
	    	// 短信应用SDK AppID
			//int appid = 1400113985; // 1400开头
			// 短信应用SDK AppKey
			//String appkey = "bb6605e87c05117ce4ab57cccd4d6734";
			// 需要发送短信的手机号码
			//String[] phoneNumbers = {"13656561657"};
			// 短信模板ID，需要在短信应用中申请
			int templateId = 157308; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
			// 签名
			String smsSign = "贵州大学协同办公";
			String[] params = {model.getSubject()};

			if(model.getTemplateMap().get("html").contains("跟进事项通知")){
				templateId = 157319;
			}else if(model.getTemplateMap().get("html").contains("事项到期提醒")){
				templateId = 157315;
			}else if(model.getTemplateMap().get("html").contains("流程节点无人员")){
				templateId = 157311;
			}else if(model.getTemplateMap().get("html").contains("审批提醒")){
				templateId = 157308;
			}else if(model.getTemplateMap().get("html").contains("撤销提醒")){
				templateId = 157304;
				params = new String[]{model.getSubject(),model.getOpinion()};
			}else if(model.getTemplateMap().get("html").contains("终止原因")){
				templateId = 157297;
				params = new String[]{model.getSubject(),model.getOpinion()};
			}else if(model.getTemplateMap().get("html").contains("退回提醒")){
				templateId = 157294;
				params = new String[]{model.getSubject(),model.getOpinion()};
			}else if(model.getTemplateMap().get("html").contains("归档提醒")){
				templateId = 157292;
			}else if(model.getTemplateMap().get("html").contains("催办提醒")){
				templateId = 157290;
			}else if(model.getTemplateMap().get("html").contains("任务被查看")){
				templateId = 157288;
				params = new String[]{model.getSubject(),model.getOpinion()};
			}

			try {
			    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			    if(mobiles.size() > 0){
			    	for (int i = 0; i < mobiles.size(); i++) {
			    		String phoneNumbers = mobiles.get(i);
			    		SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers,templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
			    		System.out.print(result);
					}
			    }
			} catch (HTTPException e) {
			    // HTTP响应码错误
			    e.printStackTrace();
			    state = MessageLog.STATE_FAIL;
			} catch (JSONException e) {
			    // json解析错误
			    e.printStackTrace();
			    state = MessageLog.STATE_FAIL;
			} catch (IOException e) {
			    // 网络IO错误
			    e.printStackTrace();
			    state = MessageLog.STATE_FAIL;
			}
			// 保存发送消息日志
			MessageLogService messageLogService = (MessageLogService) AppUtil
					.getBean(MessageLogService.class);
			messageLogService.addMessageLog(params[0],
							StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
	    }
	}*/

	/**
	 * 浙江树人短信接口(http接口)
	 */
	/*public void sendSmsZJSR(List<String> mobiles, String content){
		String flag = AppConfigUtil.get("appflag");
		if("true".equals(flag)) {
			Integer state = MessageLog.STATE_SUCCESS;
			InputStream inStream = null;
			String norsubmitUrl = AppConfigUtil.get("zjsrorsubmitUrl");
			String apId = AppConfigUtil.get("zjsrapId");
			String ecName = AppConfigUtil.get("zjsrecName");
			String secretKey = AppConfigUtil.get("zjsrsecretKey");
			String sign = AppConfigUtil.get("zjsrsign");
			String addSerial = AppConfigUtil.get("zjsraddSerial");
			try {
				// 1验证参数
				SubmitReq submitReq = new SubmitReq();
				submitReq.setApId(apId);
				submitReq.setEcName(ecName);
				submitReq.setSecretKey(secretKey);
				submitReq.setContent(content);
				submitReq.setMobiles(mobiles.get(0));
				submitReq.setSign(sign);
				submitReq.setAddSerial(addSerial);

				// 设置签名(算法：将ecName,apId,secretKey,mobiles,content,sign,addSerial字段值顺序拼接，然后通过md5(32位小写)计算
				StringBuffer strBuffer = new StringBuffer("");
				strBuffer.append(ecName);
				strBuffer.append(apId);
				strBuffer.append(secretKey);
				strBuffer.append(mobiles.get(0));
				strBuffer.append(content);
				strBuffer.append(sign);
				strBuffer.append(addSerial);
				submitReq.setMac(GetMd5(strBuffer.toString()));

				// 转换为json字符串
				String jsonStr = JSON.toJSONString(submitReq);

				// base64加密参数
				String encode = new BASE64Encoder().encode(jsonStr.getBytes("utf-8"));

				//post 提交数据
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(norsubmitUrl);

				StringEntity s = new StringEntity(encode, "utf-8");
				s.setContentEncoding("HTTP.UTF_8");
				s.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				post.setEntity(s);

				//解析返回结果
				HttpResponse httpResponse = client.execute(post);
				inStream = httpResponse.getEntity().getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					strber.append(line + "\n");
				}
				//返回结果
				logger.info("返回结果：" + strber.toString());

				JSONObject jsonData = JSONObject.parseObject(strber.toString());
				if (!jsonData.getBoolean("success")) {
					// 保存发送消息日志
					state = MessageLog.STATE_FAIL;
					content = jsonData.getString("msgGroup");
				}

			} catch (Exception e) {
				state = MessageLog.STATE_FAIL;
				e.printStackTrace();
			} finally {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// 保存发送消息日志
			MessageLogService messageLogService = (MessageLogService) AppUtil.getBean(MessageLogService.class);
			messageLogService.addMessageLog(content, mobiles.get(0), MessageLog.MOBILE_TYPE, state);
		}
	}*/

	@Override
	public boolean getIsDefaultChecked() {
		return false;
	}

	public static String httpPost(String url, String par){
		String result = "";
		HttpURLConnection httpConn = null;
		try {
			URL urlObject = new URL(url);
			HttpURLConnection.setFollowRedirects(true);
			httpConn = (HttpURLConnection)urlObject.openConnection();
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			//httpConn.setRequestProperty("Host", "[图片]exmail.qq.com");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			System.out.println(par);
			if (!Tool.isNull(par)) {
				httpConn.getOutputStream().write(par.getBytes("UTF-8"));
			}

			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				result = result + inputLine;
			}
			in.close();
		}
		catch (Exception e) {
			try {
				InputStreamReader isr = new InputStreamReader(httpConn.getErrorStream(), "GBK");
				BufferedReader in = new BufferedReader(isr);
				String inputLine;
				while ((inputLine = in.readLine()) != null)
				{
					result = result + inputLine;
				}
				in.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

		return result;
	}

	/**
	 * loginPWD是最初分配的密码，调用接口时要进行MD5加密
	 * 此方法是MD5加密算法
	 */
	public static String MD5(String loginPWD) {
		String backString = "";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String day = format.format(date);
		String checkString = String.valueOf(((Integer.parseInt(day)) & (Integer.parseInt(loginPWD))));
		if (checkString.length() < 8) {
			for (int i = 0; i < 8 - checkString.length(); i++) {
				checkString = "0" + checkString;
			}
		}
		MessageDigest md5 = null;
		try {
			char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
					'd', 'e', 'f' };
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(checkString.substring(2).getBytes());
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			backString = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return backString;
	}

	// 浙江树人用
	public static String GetMd5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchAlgorithmException {
		// 得到一个信息摘要器
		MessageDigest digest = MessageDigest.getInstance("md5");
		byte[] b = digest.digest(str.getBytes("utf-8"));
		// 把没一个byte 做一个与运算 0xff;
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}

		// 标准的md5加密后的结果
		return buf.toString();
	}

	public static void main(String[] args) {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("13656561657");
		//sendSmsZyy(mobiles, "短信测试");
	}

}
