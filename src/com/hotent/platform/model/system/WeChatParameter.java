package com.hotent.platform.model.system;

import java.util.HashMap;
import java.util.Map;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:WeChat Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liuyg
 * 创建时间:2017-08-22
 */
public class WeChatParameter extends BaseModel
{
	/**
	 * 是否支持微信
	 * 1  表示支持
	 * 0  表示不支持
	 */
	public static final String WeChat_SupportWeiXin="supportWeiXin";
	/**
	 * 平台地址
	 */
	public static final String WeChat_ServerUrl="serverUrl";
	/**
	 * 企业ID
	 */
	public static final String WeChat_Corpid="corpid";
	/**
	 * 通讯录同步凭证密钥
	 */
	public static final String WeChat_CorpSecret="corpsecret";
	/**
	 * 应用ID
	 */
	public static final String WeChat_AgentId="agentid";
	/**
	 * 应用的凭证密钥
	 */
	public static final String WeChat_AgentSecret="agentSecret";
	
	/**
	 * 是否支持微信
	 */
	protected String  supportWeiXin;
	/**
	 * 平台地址
	 */
	protected String  serverUrl;
	/**
	 * 企业ID
	 */
	protected String  corpid;
	/**
	 * 通讯录同步凭证密钥
	 */
	protected String  corpsecret;
	/**
	 * 应用ID
	 */
	protected String  agentid;
	/**
	 * 应用的凭证密钥
	 */
	protected String  agentSecret;
	
	
	public String getSupportWeiXin() {
		return supportWeiXin;
	}
	public void setSupportWeiXin(String supportWeiXin) {
		this.supportWeiXin = supportWeiXin;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getCorpsecret() {
		return corpsecret;
	}
	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getAgentSecret() {
		return agentSecret;
	}
	public void setAgentSecret(String agentSecret) {
		this.agentSecret = agentSecret;
	}
	
	public Map<String,String> toMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("supportWeiXin", this.supportWeiXin);
		map.put("corpid", this.corpid);
		map.put("serverUrl", this.serverUrl);
		map.put("corpsecret", this.corpsecret);
		map.put("agentid", this.agentid);
		map.put("agentSecret", this.agentSecret);
		return map;
	}
	
}