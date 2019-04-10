package com.zfsoft.rabbitmq.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.util.AppConfigUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChannelUtils {
	public static Channel getChannelInstance(String connectionDescription) {
		try {
			ConnectionFactory connectionFactory = getConnectionFactory();
			Connection connection = connectionFactory.newConnection(connectionDescription);
			return connection.createChannel();
		} catch (Exception e) {
			throw new RuntimeException("获取Channel连接失败");
		}
	}

	private static ConnectionFactory getConnectionFactory() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 配置连接信息
		connectionFactory.setHost("localhost");
		// connectionFactory.setPort(15672);
		//connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 网络异常自动连接恢复
		connectionFactory.setAutomaticRecoveryEnabled(true);
		// 每10秒尝试重试连接一次
		connectionFactory.setNetworkRecoveryInterval(10000);

		// 设置ConnectionFactory属性信息
		Map<String, Object> connectionFactoryPropertiesMap = new HashMap();
		connectionFactoryPropertiesMap.put("principal", "RobertoHuang");
		connectionFactoryPropertiesMap.put("description", "RGP订单系统V2.0");
		connectionFactoryPropertiesMap.put("emailAddress", "RobertoHuang@foxmail.com");
		connectionFactory.setClientProperties(connectionFactoryPropertiesMap);

		return connectionFactory;
	}
	
	/**
	 * 操作代码:1:申请:Apply;2:待办:Todo;已办:Done;3:草稿:Draft;4:办结:Transferred;5:消息：Msg
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void test(String yhm, String operate) throws IOException, TimeoutException{
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("yhm", yhm);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("Msg");
        if("1".equals(operate)){
            jsonArray.add("Apply");
        }else if("2".equals(operate)){
        	jsonArray.add("Todo");
        	jsonArray.add("Done");
        	jsonArray.add("Transferred");
        }else if("3".equals(operate)){
        	jsonArray.add("Draft");
        }
        jsonObject.put("operate", jsonArray);
        System.out.println("Sending message...");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 配置连接信息
		String host = AppConfigUtil.get("messagehost");
		String username = AppConfigUtil.get("messageusername");
		String password = AppConfigUtil.get("messagepassword");
		connectionFactory.setHost(host);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);

		// 网络异常自动连接恢复
		connectionFactory.setAutomaticRecoveryEnabled(true);
		// 每10秒尝试重试连接一次
		connectionFactory.setNetworkRecoveryInterval(10000);

		Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
		
        channel.exchangeDeclare("Wisdom_Portal_Topic_Exchange","topic",true);
        channel.basicPublish("Wisdom_Portal_Topic_Exchange","com.zfsoft.bpmx",null, JSON.toJSONString(jsonObject).getBytes());
        System.out.println(" [x] Sent");
        channel.close();
        connection.close();
	}
	
}
