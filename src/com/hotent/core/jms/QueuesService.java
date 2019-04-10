/*     */ package com.hotent.core.jms;
/*     */ 
/*     */ import com.hotent.core.util.AppConfigUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Resource;
/*     */ import javax.jms.ConnectionFactory;
/*     */ import javax.jms.Session;
/*     */ import org.apache.activemq.ActiveMQSession;
/*     */ import org.apache.activemq.broker.jmx.BrokerViewMBean;
/*     */ import org.apache.activemq.broker.jmx.DestinationViewMBean;
/*     */ import org.apache.activemq.broker.jmx.QueueViewMBean;
/*     */ import org.apache.activemq.command.ActiveMQDestination;
/*     */ import org.apache.activemq.pool.PooledSession;
/*     */ import org.apache.activemq.web.MessageQuery;
/*     */ import org.apache.activemq.web.QueueBrowseQuery;
/*     */ import org.apache.activemq.web.RemoteJMXBrokerFacade;
/*     */ import org.apache.activemq.web.SessionPool;
/*     */ import org.apache.activemq.web.config.SystemPropertiesConfiguration;
/*     */ 
/*     */ public class QueuesService
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   protected ConnectionFactory simpleJmsConnectionFactory;
/*     */   private RemoteJMXBrokerFacade brokerFacade;
/*  37 */   private String jmxHostIp = "127.0.0.1";
/*  38 */   private String jmxPort = "1099";
/*     */ 
/*     */   private RemoteJMXBrokerFacade getBrokerFacade() {
/*  41 */     if (this.brokerFacade == null) {
/*  42 */       this.brokerFacade = new RemoteJMXBrokerFacade();
/*  43 */       this.jmxHostIp = AppConfigUtil.get("jms.ip");
/*  44 */       this.jmxPort = AppConfigUtil.get("jmx.port", this.jmxPort);
/*  45 */       System.setProperty("webconsole.jmx.url", "service:jmx:rmi:///jndi/rmi://" + this.jmxHostIp + ":" + this.jmxPort + "/jmxrmi");
/*     */ 
/*  47 */       SystemPropertiesConfiguration configuration = new SystemPropertiesConfiguration();
/*     */ 
/*  49 */       this.brokerFacade.setConfiguration(configuration);
/*     */     }
/*  51 */     return this.brokerFacade;
/*     */   }
/*     */ 
/*     */   private QueueViewMBean getQueue(String name)
/*     */     throws Exception
/*     */   {
/*  61 */     QueueViewMBean qvb = null;
/*  62 */     qvb = (QueueViewMBean)getDestinationByName(getQueues(), name);
/*  63 */     return qvb;
/*     */   }
/*     */ 
/*     */   private DestinationViewMBean getDestinationByName(Collection<? extends DestinationViewMBean> collection, String name)
/*     */   {
/*  73 */     Iterator iter = collection.iterator();
/*  74 */     while (iter.hasNext()) {
/*  75 */       DestinationViewMBean destinationViewMBean = (DestinationViewMBean)iter.next();
/*  76 */       if (name.equals(destinationViewMBean.getName())) {
/*  77 */         return destinationViewMBean;
/*     */       }
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   private SessionPool getSessionPool()
/*     */     throws Exception
/*     */   {
/*  89 */     SessionPool sp = new SessionPool();
/*  90 */     sp.setConnectionFactory(this.simpleJmsConnectionFactory);
/*  91 */     sp.setConnection(sp.getConnection());
/*  92 */     Session session = sp.borrowSession();
/*  93 */     ActiveMQSession am = null;
/*  94 */     if ((session instanceof ActiveMQSession)) {
/*  95 */       am = (ActiveMQSession)session;
/*     */     }
/*  97 */     if ((session instanceof PooledSession)) {
/*  98 */       PooledSession pooledSession = (PooledSession)session;
/*  99 */       am = pooledSession.getInternalSession();
/*     */     }
/* 101 */     sp.returnSession(am);
/* 102 */     return sp;
/*     */   }
/*     */ 
/*     */   public Collection<QueueViewMBean> getQueues() throws Exception {
/* 106 */     return getBrokerFacade().getQueues();
/*     */   }
/*     */ 
/*     */   public void purgeDestination(String JMSDestination)
/*     */     throws Exception
/*     */   {
/* 115 */     getBrokerFacade().purgeQueue(ActiveMQDestination.createDestination(JMSDestination, (byte)1));
/*     */   }
/*     */ 
/*     */   public void removeMessage(String JMSDestination, String messageId)
/*     */     throws Exception
/*     */   {
/* 125 */     QueueViewMBean queueView = getQueue(JMSDestination);
/* 126 */     queueView.removeMessage(messageId);
/*     */   }
/*     */ 
/*     */   public QueueBrowseQuery getQueueBrowseQuery(String JMSDestination)
/*     */     throws Exception
/*     */   {
/* 136 */     QueueBrowseQuery qbq = new QueueBrowseQuery(getBrokerFacade(), getSessionPool());
/* 137 */     qbq.setJMSDestination(JMSDestination);
/* 138 */     return qbq;
/*     */   }
/*     */ 
/*     */   public MessageQuery getMessageQuery(String id, String JMSDestination)
/*     */     throws Exception
/*     */   {
/* 149 */     MessageQuery mq = new MessageQuery(getBrokerFacade(), getSessionPool());
/* 150 */     mq.setJMSDestination(JMSDestination);
/* 151 */     mq.setId(id);
/* 152 */     return mq;
/*     */   }
/*     */ 
/*     */   public void removeDestinationQueue(String JMSDestination)
/*     */     throws Exception
/*     */   {
/* 161 */     getBrokerFacade().getBrokerAdmin().removeQueue(JMSDestination);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.QueuesService
 * JD-Core Version:    0.6.2
 */