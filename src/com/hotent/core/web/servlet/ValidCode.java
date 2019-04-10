/*     */ package com.hotent.core.web.servlet;
/*     */ 
/*     */ import com.sun.jimi.core.Jimi;
/*     */ import com.sun.jimi.core.JimiException;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class ValidCode extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   public static String SessionName_Randcode = "randcode";
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  53 */     super.destroy();
/*     */   }
/*     */ 
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  60 */     response.setHeader("Pragma", "No-cache");
/*  61 */     response.setHeader("Cache-Control", "no-cache");
/*  62 */     response.setDateHeader("Expires", 0L);
/*  63 */     response.setContentType("image/jpeg");
/*     */ 
/*  65 */     int width = 60; int height = 26;
/*  66 */     BufferedImage image = new BufferedImage(width, height, 1);
/*     */ 
/*  68 */     Graphics g = image.getGraphics();
/*     */ 
/*  70 */     Random random = new Random();
/*     */ 
/*  72 */     g.setColor(getRandColor(155, 254));
/*     */ 
/*  74 */     g.fillRect(0, 0, width, height);
/*     */ 
/*  76 */     g.setFont(new Font("Times New Roman", 0, 18));
/*     */ 
/*  81 */     g.setColor(getRandColor(160, 220));
/*  82 */     for (int i = 0; i < 155; i++)
/*     */     {
/*  84 */       int x = random.nextInt(width);
/*  85 */       int y = random.nextInt(height);
/*  86 */       int xl = random.nextInt(12);
/*  87 */       int yl = random.nextInt(12);
/*  88 */       g.drawLine(x, y, x + xl, y + yl);
/*     */     }
/*     */ 
/*  91 */     String sRand = "";
/*  92 */     for (int i = 0; i < 4; i++)
/*     */     {
/*  94 */       String rand = String.valueOf(random.nextInt(10));
/*  95 */       sRand = sRand + rand;
/*     */ 
/*  97 */       g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
/*  98 */       g.drawString(rand, 13 * i + 6, 20);
/*     */     }
/*     */ 
/* 101 */     request.getSession().setAttribute(SessionName_Randcode, sRand);
/*     */ 
/* 103 */     g.dispose();
/*     */ 
/* 108 */     ServletOutputStream os = response.getOutputStream();
/*     */     try
/*     */     {
/* 111 */       Jimi.putImage("image/jpeg", image, os);
/*     */     } catch (JimiException e) {
/* 113 */       e.printStackTrace();
/*     */     }
/* 115 */     os.flush();
/* 116 */     os.close();
/* 117 */     os = null;
/* 118 */     response.flushBuffer();
/*     */   }
/*     */ 
/*     */   private Color getRandColor(int fc, int bc)
/*     */   {
/* 124 */     Random random = new Random();
/* 125 */     if (fc > 255)
/* 126 */       fc = 255;
/* 127 */     if (bc > 255)
/* 128 */       bc = 255;
/* 129 */     int r = fc + random.nextInt(bc - fc);
/* 130 */     int g = fc + random.nextInt(bc - fc);
/* 131 */     int b = fc + random.nextInt(bc - fc);
/* 132 */     return new Color(r, g, b);
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void init()
/*     */     throws ServletException
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.servlet.ValidCode
 * JD-Core Version:    0.6.2
 */