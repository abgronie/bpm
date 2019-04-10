package com.zfsoft.rabbitmq.demo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.zfsoft.rabbitmq.utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 * 
 * @author byq
 *
 */
public class MessageProducer {
	public static void main(String[] args) throws IOException, TimeoutException {
		Channel channel = ChannelUtils.getChannelInstance("RGP订单系统消息生产者");

		// 声明交换机 (交换机名, 交换机类型, 是否持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
		channel.exchangeDeclare("roberto.order", BuiltinExchangeType.DIRECT, true, false, false,
				new HashMap<String, Object>());

		// 设置消息属性 发布消息 (交换机名, Routing key, 可靠消息相关属性 后续会介绍, 消息属性, 消息体);
		AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().deliveryMode(2).contentType("UTF-8")
				.build();
		channel.basicPublish("roberto.order", "add", false, basicProperties, "订单信息".getBytes());
	}
}
