package com.zfsoft;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.hotent.platform.webservice.api.ProcessService;
import com.hotent.platform.webservice.impl.ProcessServiceImpl;

public class TestBpmx {
	
	public static void main(String[] args) {
		String targetNamespace="http://impl.webservice.platform.hotent.com/";
		//String function ="start";
		//String function ="getMyTaskByAccount";pro
		//String function ="getMyRequestListJson";
		String function ="getTasksByRunId";
		if("start".equalsIgnoreCase(function)){//启动流程
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/ProcessService?wsdl";
			String parameter = "xml";
			
			/*毕（结）业证书遗失办理、学历证明确认*/
			String str= "{\"main\":{\"fields\":{" +
					"\"xm\":\"刘德华\"," +
					"\"lxhm\":\"15397131988\"," +
					"\"xb\":\"1\"," +
					"\"sfzh\":\"15397131988\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"bjyzsysblxlzmqr:3:10000009610014\"  subject=\"毕（结）业证书遗失办理、学历证明确认\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";
			
			/*全国英语等级考试成绩证明（17）
			String str= "{\"main\":{\"fields\":{" +
					"\"xm\":\"刘德华\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"lxhm\":\"15397131988\"," +
					"\"sfyj\":\"否\"," +
					"\"sjr\":\"周杰伦\"," +
					"\"sjrlxdh\":\"13622222222\"," +
					"\"yjdz\":\"台州市\"," +
					"\"ztdz\":\"杭州市\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"qgyydjkscjzm:1:10000000730682\"  subject=\"全国英语等级考试成绩证明\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*全国计算机等级考试合格证书证明（16）
			String str= "{\"main\":{\"fields\":{" +
					"\"xm\":\"刘德华\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"lxhm\":\"15397131988\"," +
					"\"sfyj\":\"是\"," +
					"\"sjr\":\"周杰伦\"," +
					"\"sjrlxdh\":\"13622222222\"," +
					"\"yjdz\":\"台州市\"," +
					"\"ztdz\":\"杭州市\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"qgjsjdjkshgzszm:1:10000000730712\"  subject=\"全国计算机等级考试合格证书证明\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*对自考合格课程跨省转移的确认（07）
			String str= "{\"main\":{\"fields\":{" +
					"\"zp\":\"bpm/123456/99999.png\"," +
					"\"ksxm\":\"刘德华\"," +
					"\"xb\":\"1\"," +
					"\"csrq\":\"2017-06-25\"," +
					"\"lxhm\":\"15397131988\"," +
					"\"sfzh\":\"身份证号\"," +
					"\"zwszycc\":\"在我省专业层次\"," +
					"\"zjszkzhm\":\"浙江省准考证号码\"," +
					"\"zjszydm\":\"浙江省专业代码\"," +
					"\"zjszymc\":\"浙江省专业名称\"," +
					"\"zcyy\":\"转出原因\"," +
					"\"zrzycc\":\"转入专业层次\"," +
					"\"zrdzkzhm\":\"转入地准考证号码\"," +
					"\"zrszydm\":\"转入省专业代码\"," +
					"\"zrdsf\":\"转入地省份\"," +
					"\"zrdzymc\":\"转入地专业名称\"," +
					"\"hj\":\"农村\"," +
					"\"zzmm\":\"政治面貌\"," +
					"\"mz\":\"民族\"," +
					"\"zy\":\"职业\"," +
					"\"xl\":\"学历\"}}," +
					"\"sub\":[{\"tableName\": \"zckcxxb\","
								+ "\"fields\": [{\"zckcdm\": \"课程代码\","
												+ "\"zckcmc\": \"课程名称\","
												+ "\"zckcxf\": \"课程学分\","
												+ "\"zckccj\": \"课程成绩\","
												+ "\"zckchgsj\": \"课程合格时间\"},"
												+ "{\"zckcxh\": \"111qqq\","
												+ "\"zckcdm\": \"222qq\","
												+ "\"zckcmc\": \"333qqq\","
												+ "\"zckcxf\": \"444qqq\","
												+ "\"zckccj\": \"555qqq\","
												+ "\"zckchgsj\": \"666qqq\"}]"
							+ "}]," +
					"\"opinion\":[]}";
			String cs="<req actDefId=\"dzkhgkckszydqr:1:10000002270166\"  subject=\"对自考合格课程跨省转移的确认\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*对自考考生免考课程的确认（08）
			String str= "{\"main\":{\"fields\":{" +
					"\"zkzh\":\"准考证号\"," +
					"\"xm\":\"姓名\"," +
					"\"lxhm\":\"联系电话\"," +
					"\"ysxzy\":\"原所学专业\"," +
					"\"ybyxx\":\"原毕业学校\"," +
					"\"xlcc\":\"学历层次\"," +
					"\"sbzy\":\"申报专业\"," +
					"\"xb\":\"2\"," +
					"\"csrq\":\"出生日期\"," +
					"\"sfzh\":\"身份证号\"}}," +
					"\"sub\":["
							+ "{\"tableName\": \"zkhgkc\",\"fields\": [{\"hgkcmc\": \"合格课程名称1\"},{\"hgkcmc\": \"合格课程名称2\"}]},"
							+"{\"tableName\": \"sqmkkcqk\","
							+ "\"fields\": [{\"sqmkkcdm\": \"申请免考课程代码\","
											+ "\"sqmkkcmc\": \"申请免考课程名称\","
											+ "\"ysxkccj\": \"原所学课程成绩\","
											+ "\"ysxkcxs\": \"原所学课程学时\","
											+ "\"ysxkc\": \"原所学课程\"},"
											+ "{\"sqmkkcdm\": \"111qqq\","
											+ "\"sqmkkcmc\": \"222qq\","
											+ "\"ysxkccj\": \"333qqq\","
											+ "\"ysxkcxs\": \"444qqq\","
											+ "\"ysxkc\": \"555qqq\"}]}"
							+ "]," +
					"\"opinion\":[]}";
			String cs="<req actDefId=\"dzkksmkkcdqr:1:10000000840005\"  subject=\"对自考考生免考课程的确认\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*普通话水平测试等级证书申领表（06）
			String str= "{\"main\":{\"fields\":{" +
					"\"xb\":\"女\"," +
					"\"xm\":\"刘德华\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"lxhm\":\"13612345678\"," +
					"\"ycsjg\":\"原测试机构\"," +
					"\"yzsbh\":\"原证书编号\"," +
					"\"ycssj\":\"2017-06-25\"," +
					"\"yfzsj\":\"2017-06-26\"," +
					"\"cj\":\"成绩\"," +
					"\"dj\":\"等级\"," +
					"\"sfyj\":\"是\"," +
					"\"sjr\":\"收件人\"," +
					"\"sjrlxdh\":\"收件人联系电话\"," +
					"\"yjdz\":\"邮寄地址\"," +
					"\"ztdz\":\"自提地址\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"pthspcsdjzsslb:1:10000000730692\"  subject=\"普通话水平测试等级证书申领表\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*普通话水平测试等级证明补发（18）
			String str= "{\"main\":{\"fields\":{" +
					"\"xb\":\"男\"," +
					"\"xm\":\"刘德华\"," +
					"\"lxhm\":\"13656565656\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"ycsjg\":\"原测试机构\"," +
					"\"yzsbh\":\"原证书编号\"," +
					"\"ycssj\":\"2017-06-25\"," +
					"\"yfzsj\":\"2017-06-26\"," +
					"\"cj\":\"成绩\"," +
					"\"dj\":\"等级\"," +
					"\"sfyj\":\"否\"," +
					"\"sjr\":\"收件人\"," +
					"\"sjrlxdh\":\"收件人联系电话\"," +
					"\"yjdz\":\"邮寄地址\"," +
					"\"ztdz\":\"自提地址\"," +
					"\"nf\":\"1\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"pthspcsdjzmbf:1:10000000730702\"  subject=\"普通话水平测试等级证明补发\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*浙江省普通高中学业水平考试成绩证明／浙江省普通高中会考成绩证明（13）
			String str= "{\"main\":{\"fields\":{" +
					"\"sqdzmlx\":\"2\"," +
					"\"xm\":\"刘德华123\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"hkzkzhm\":\"会考准考证号码\"," +
					"\"lxhm\":\"13656565656\"," +
					"\"sfyj\":\"否\"," +
					"\"sjr\":\"收件人\"," +
					"\"sjrlxdh\":\"收件人联系电话\"," +
					"\"yjdz\":\"邮寄地址\"," +
					"\"ztdz\":\"自提地址\"," +
					"\"nf\":\"年份\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"zjsptgzxyspkscjzm:1:10000000730336\"  subject=\"浙江省普通高中学业水平考试成绩证明／浙江省普通高中会考成绩证明\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*浙江省普通高校招生考试成绩证明／浙江省单独考试招生（原高职单考单招）考试成绩证明（14）
			String str= "{\"main\":{\"fields\":{" +
					"\"sqdzmlx\":\"1\"," +
					"\"xm\":\"刘德华\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"lxhm\":\"13656565656\"," +
					"\"sfyj\":\"是\"," +
					"\"sjr\":\"收件人\"," +
					"\"sjrlxdh\":\"收件人联系电话\"," +
					"\"yjdz\":\"邮寄地址\"," +
					"\"ztdz\":\"自提地址\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"zjsptgxzskscjzm:1:10000000730505\"  subject=\"浙江省普通高校招生考试成绩证明/浙江省单独考试招生（原高职单考单招）考试成绩证明\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			/*浙江省自学考试合格成绩证明／毕业证明（15）
			String str= "{\"main\":{\"fields\":{" +
					"\"sqdzmlx\":\"2\"," +
					"\"xm\":\"刘德华\"," +
					"\"sfzh\":\"42012345678\"," +
					"\"zkzhm\":\"zkzhm42012345678\"," +
					"\"lxhm\":\"13656565656\"," +
					"\"sfyj\":\"否\"," +
					"\"sjr\":\"收件人\"," +
					"\"sjrlxdh\":\"收件人联系电话\"," +
					"\"cxmm\":\"123456\"," +
					"\"nf\":\"1\"," +
					"\"zmfj\":\"证明附件\"," +
					"\"yjdz\":\"邮寄地址\"," +
					"\"ztdz\":\"自提地址\"}}," +
					"\"sub\":[],\"opinion\":[]}";
			String cs="<req actDefId=\"zjszxkshgcjzm:2:10000000900006\"  subject=\"浙江省自学考试合格成绩证明／毕业证明\" account=\"admin\" >" +
					"<data>" +
					"<![CDATA["+str+"]]>" +
					"</data>" +
					"</req>";*/
			
			bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}/*else if("getFormHtml".equalsIgnoreCase(function)){
			String endpointAddress = "http://10.71.33.71:8080/bpmx/service/FormService?wsdl";
			String parameter ="formInfo";
			String cs="{" +
					"\"actDefId\": \"zjsptgzkscjzm:2:10000000100210\"," +
					"\"ctxPath\": \"/bpmx\"," +
					"\"account\": \"admin\""
					+ "}";
					bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}*/else if("getMyRequestListJson".equalsIgnoreCase(function)){//获取我的请求
//			String endpointAddress = "http://122.224.218.35:8088/bpmx/service/FlowService?wsdl";
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/FlowService?wsdl";
			String parameter ="json";
			String cs="{" +
					"account: \"admin\"," +
					"classType:\"\"," +
					"currentPage:\"1\"," +
					"pageSize: \"20\"," +
					"inOrNot: \"true\"," +
					"subject: \"\""
					+ "}";
			 bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}else if("getMyTaskByAccount".equalsIgnoreCase(function)){//获取我的待办
//			String endpointAddress = "http://122.224.218.35:8088/bpmx/service/FlowService?wsdl";
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/FlowService?wsdl";
			String parameter ="json";
			String cs="{" +
					"account: \"admin\"," +
					"taskNodeName:\"\"," +
					"subject: \"\"," +
					"processName:\"\"," +
					"orderField:\"\"," +
					"orderSeq:\"\"," +
					"currentPage:\"1\"," +
					"pageSize: \"8\""
					+ "}" ;
					bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}else if("getAlreadyMattersListJson".equalsIgnoreCase(function)){//获取我的已办
//			String endpointAddress = "http://122.224.218.35:8088/bpmx/service/FlowService?wsdl";
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/FlowService?wsdl";
			String parameter ="json";
			String cs="{" +
					"account: \"admin\"," +
					"classType:\"\"," +
					"currentPage:\"1\"," +
					"pageSize: \"20\"," +
					"inOrNot: \"true\""
					+ "}";
					bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}else if("getMyCompletedListJson".equalsIgnoreCase(function)){//获取我的办结
//			String endpointAddress = "http://122.224.218.35:8088/bpmx/service/FlowService?wsdl";
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/FlowService?wsdl";
			String parameter ="json";
			String cs="{" +
					"account: \"admin\"," +
					"classType:\"\"," +
					"currentPage:\"1\"," +
					"pageSize: \"20\"," +
					"inOrNot: \"true\"," +
					"subject: \"\""
					+ "}";
					bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}else if("getTasksByRunId".equalsIgnoreCase(function)){
			String endpointAddress = "http://10.71.33.190:8089/bpmx/service/ProcessService?wsdl";
			String parameter ="runId";
			String cs="10000009430344";
			bpmxWSDL(endpointAddress,function,targetNamespace,parameter,cs);
		}
	}
	
	private static String bpmxWSDL(String endpointAddress,String function,String targetNamespace,String parameter,String cs){
		Service service = new Service();
		Call call;
		String str ="";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpointAddress));
			call.setOperationName(new QName(targetNamespace, function));
			call.addParameter(parameter, org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(endpointAddress+function);
			call.setTimeout(9000000);
			str = (String) call.invoke(new Object[] {cs});
			System.out.println(str);
		} catch (ServiceException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return str;
	}
//http://10.71.33.71:8080/bpmx/weixin
//http://10.71.33.71:8080/bpmx/weixin/login.html
//http://10.71.33.71:8080/bpmx/weixin/index.html?v=1.0
}