/*     */ package com.hotent.core.bpm.graph.activiti;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;

/*     */ import javax.imageio.ImageIO;

/*     */ import org.activiti.engine.ActivitiException;
/*     */ import org.activiti.engine.impl.util.IoUtil;
/*     */ import org.activiti.engine.impl.util.ReflectUtil;

/*     */ 
/*     */ import com.hotent.core.api.bpm.model.ITaskOpinion;
/*     */ import com.hotent.core.api.util.PropertyUtil;
/*     */ import com.hotent.core.util.FontUtil;
/*     */ 
/*     */ public class ProcessDiagramCanvas
/*     */ {
/*  41 */   protected static final Logger LOGGER = Logger.getLogger(ProcessDiagramCanvas.class.getName());
/*     */   protected static final int ARROW_WIDTH = 5;
/*     */   protected static final int CONDITIONAL_INDICATOR_WIDTH = 16;
/*     */   protected static final int MARKER_WIDTH = 12;
/*  46 */   protected static Color DEFAULT_COLOR = new Color(0, 0, 0);
/*  47 */   protected static Color START_COLOR = new Color(113, 146, 75);
/*  48 */   protected static Color END_COLOR = new Color(210, 112, 0);
/*     */   protected static Color POOL_BOUNDARY_COLOR;
/*     */   protected static Color LANE_BOUNDARY_COLOR;
/*     */   protected static Color POOL_BACKGROUP_COLOR;
/*     */   protected static Color LANE_BACKGROUP_COLOR;
/*  53 */   protected static Color TASK_COLOR = new Color(255, 255, 204);
/*  54 */   protected static Color EVENT_BOUNDARY_COLOR = new Color(255, 255, 255);
/*  55 */   protected static Color CONDITIONAL_INDICATOR_COLOR = new Color(255, 255, 255);
/*     */ 
/*  57 */   protected static Color HIGHLIGHT_COLOR = Color.RED;
/*     */ 
/*  59 */   protected static Stroke THIN_TASK_BORDER_STROKE = new BasicStroke(1.0F);
/*  60 */   protected static Stroke THICK_TASK_BORDER_STROKE = new BasicStroke(3.0F);
/*  61 */   protected static Stroke THICK2_TASK_BORDER_STROKE = new BasicStroke(2.0F);
/*  62 */   protected static Stroke GATEWAY_TYPE_STROKE = new BasicStroke(3.0F);
/*  63 */   protected static Stroke END_EVENT_STROKE = new BasicStroke(3.0F);
/*  64 */   protected static Stroke START_EVENT_STROKE = new BasicStroke(3.0F);
/*  65 */   protected static Stroke MULTI_INSTANCE_STROKE = new BasicStroke(1.3F);
/*  66 */   protected static Stroke LABEL_STROKE = new BasicStroke(1.0F);
/*     */ 
/*  68 */   protected static int ICON_SIZE = 16;
/*     */   protected static Image USERTASK_IMAGE;
/*     */   protected static Image SCRIPTTASK_IMAGE;
/*     */   protected static Image SERVICETASK_IMAGE;
/*     */   protected static Image RECEIVETASK_IMAGE;
/*     */   protected static Image SENDTASK_IMAGE;
/*     */   protected static Image MANUALTASK_IMAGE;
/*     */   protected static Image TIMER_IMAGE;
/*     */   protected static Image ERROR_THROW_IMAGE;
/*     */   protected static Image ERROR_CATCH_IMAGE;
/*     */   protected static Image CALLACTIVITY_IMAGE;
/*  79 */   protected int canvasWidth = -1;
/*  80 */   protected int canvasHeight = -1;
/*  81 */   protected int minX = -1;
/*  82 */   protected int minY = -1;
/*     */   protected BufferedImage processDiagram;
/*     */   protected Graphics2D g;
/*     */   protected FontMetrics fontMetrics;
/*     */   protected boolean closed;
/*  88 */   protected static Map<Short, Color> colorsMap = new HashMap();
/*     */ 
/*     */   public ProcessDiagramCanvas(int width, int height) {
/*  91 */     this.canvasWidth = width;
/*  92 */     this.canvasHeight = height;
/*  93 */     this.processDiagram = new BufferedImage(width, height, 2);
/*  94 */     this.g = this.processDiagram.createGraphics();
/*  95 */     this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*  97 */     this.g.setPaint(Color.black);
/*     */ 
/*  99 */     Font font = FontUtil.getFont("宋体", Integer.valueOf(1), Integer.valueOf(12));
/* 100 */     this.g.setFont(font);
/* 101 */     this.fontMetrics = this.g.getFontMetrics();
/*     */   }
/*     */ 
/*     */   public ProcessDiagramCanvas(int width, int height, int minX, int minY) {
/* 105 */     this(width, height);
/* 106 */     this.minX = minX;
/* 107 */     this.minY = minY;
/*     */   }
/*     */ 
/*     */   public InputStream generateImage(String imageType) {
/* 111 */     if (this.closed) {
/* 112 */       throw new ActivitiException("ProcessDiagramGenerator already closed");
/*     */     }
/*     */ 
/* 116 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     try {
/* 118 */       this.minX = (this.minX <= 5 ? 5 : this.minX);
/* 119 */       this.minY = (this.minY <= 5 ? 5 : this.minY);
/* 120 */       BufferedImage imageToSerialize = this.processDiagram;
/* 121 */       if ((this.minX >= 0) && (this.minY >= 0)) {
/* 122 */         imageToSerialize = this.processDiagram.getSubimage(this.minX - 5, this.minY - 5, this.canvasWidth - this.minX + 5, this.canvasHeight - this.minY + 5);
/*     */       }
/*     */ 
/* 127 */       ImageIO.write(imageToSerialize, imageType, out);
/*     */     } catch (IOException e) {
/* 129 */       throw new ActivitiException("Error while generating process image", e);
/*     */     }
/*     */     finally {
/* 132 */       IoUtil.closeSilently(out);
/*     */     }
/* 134 */     return new ByteArrayInputStream(out.toByteArray());
/*     */   }
/*     */ 
/*     */   public void close() {
/* 138 */     this.g.dispose();
/* 139 */     this.closed = true;
/*     */   }
/*     */ 
/*     */   public void drawNoneStartEvent(String name, int x, int y, int width, int height)
/*     */   {
/* 144 */     drawStartEvent(name, x, y, width, height, null);
/*     */   }
/*     */ 
/*     */   public void drawTimerStartEvent(String name, int x, int y, int width, int height)
/*     */   {
/* 149 */     drawStartEvent(name, x, y, width, height, TIMER_IMAGE);
/*     */   }
/*     */ 
/*     */   public void drawStartEvent(String name, int x, int y, int width, int height, Image image)
/*     */   {
/* 154 */     Paint originalPaint = this.g.getPaint();
/* 155 */     Stroke originalStroke = this.g.getStroke();
/* 156 */     this.g.setPaint(START_COLOR);
/* 157 */     this.g.setStroke(START_EVENT_STROKE);
/* 158 */     this.g.draw(new Ellipse2D.Double(x, y, width, height));
/* 159 */     if (image != null)
/* 160 */       this.g.drawImage(image, x, y, width, height, null);
/* 161 */     this.g.setStroke(originalStroke);
/* 162 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawNoneEndEvent(String name, int x, int y, int width, int height)
/*     */   {
/* 167 */     Paint originalPaint = this.g.getPaint();
/* 168 */     Stroke originalStroke = this.g.getStroke();
/* 169 */     this.g.setPaint(END_COLOR);
/* 170 */     this.g.setStroke(END_EVENT_STROKE);
/*     */ 
/* 172 */     this.g.draw(new Ellipse2D.Double(x, y, width, height));
/* 173 */     this.g.setStroke(originalStroke);
/* 174 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawErrorEndEvent(String name, int x, int y, int width, int height)
/*     */   {
/* 180 */     drawNoneEndEvent(name, x, y, width, height);
/* 181 */     this.g.drawImage(ERROR_THROW_IMAGE, x + 3, y + 3, width - 6, height - 6, null);
/*     */   }
/*     */ 
/*     */   public void drawCatchingEvent(int x, int y, int width, int height, Image image)
/*     */   {
/* 187 */     Ellipse2D outerCircle = new Ellipse2D.Double(x, y, width, height);
/* 188 */     int innerCircleX = x + 3;
/* 189 */     int innerCircleY = y + 3;
/* 190 */     int innerCircleWidth = width - 6;
/* 191 */     int innerCircleHeight = height - 6;
/* 192 */     Ellipse2D innerCircle = new Ellipse2D.Double(innerCircleX, innerCircleY, innerCircleWidth, innerCircleHeight);
/*     */ 
/* 195 */     Paint originalPaint = this.g.getPaint();
/* 196 */     this.g.setPaint(EVENT_BOUNDARY_COLOR);
/* 197 */     this.g.fill(outerCircle);
/*     */ 
/* 199 */     this.g.setPaint(originalPaint);
/* 200 */     this.g.draw(outerCircle);
/* 201 */     this.g.draw(innerCircle);
/*     */ 
/* 203 */     this.g.drawImage(image, innerCircleX, innerCircleY, innerCircleWidth, innerCircleHeight, null);
/*     */   }
/*     */ 
/*     */   public void drawCatchingTimerEvent(int x, int y, int width, int height)
/*     */   {
/* 208 */     drawCatchingEvent(x, y, width, height, TIMER_IMAGE);
/*     */   }
/*     */ 
/*     */   public void drawCatchingErroEvent(int x, int y, int width, int height) {
/* 212 */     drawCatchingEvent(x, y, width, height, ERROR_CATCH_IMAGE);
/*     */   }
/*     */ 
/*     */   public void drawSequenceflow(int srcX, int srcY, int targetX, int targetY, boolean conditional)
/*     */   {
/* 217 */     Line2D.Double line = new Line2D.Double(srcX, srcY, targetX, targetY);
/* 218 */     this.g.draw(line);
/* 219 */     drawArrowHead(line);
/*     */ 
/* 221 */     if (conditional)
/* 222 */       drawConditionalSequenceFlowIndicator(line);
/*     */   }
/*     */ 
/*     */   public void drawSequenceflowWithoutArrow(int srcX, int srcY, int targetX, int targetY, boolean conditional)
/*     */   {
/* 227 */     Line2D.Double line = new Line2D.Double(srcX, srcY, targetX, targetY);
/* 228 */     this.g.draw(line);
/*     */ 
/* 230 */     if (conditional)
/* 231 */       drawConditionalSequenceFlowIndicator(line);
/*     */   }
/*     */ 
/*     */   public void drawSequenceflow(List<Point2D.Double> points) {
/* 235 */     for (int i = 0; i < points.size() - 1; i++) {
/* 236 */       Line2D.Double line = new Line2D.Double(((Point2D.Double)points.get(i)).getX(), ((Point2D.Double)points.get(i)).getY(), ((Point2D.Double)points.get(i + 1)).getX(), ((Point2D.Double)points.get(i + 1)).getY());
/*     */ 
/* 239 */       this.g.draw(line);
/* 240 */       if (i == points.size() - 2)
/* 241 */         drawArrowHead(line);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void drawSequenceflowWidthLabel(BPMNEdge bpmnEdge)
/*     */   {
/* 248 */     drawSequenceflow(bpmnEdge.getPoints());
/* 249 */     int flag = 0;
/* 250 */     DirectionType directionType = bpmnEdge.getDirection();
/* 251 */     if (directionType == DirectionType.UpToDown)
/* 252 */       flag = 1;
/* 253 */     else if (directionType == DirectionType.DownToUp)
/* 254 */       flag = 2;
/* 255 */     else if (directionType == DirectionType.LeftToRight)
/* 256 */       flag = 3;
/* 257 */     else if (directionType == DirectionType.RightToLef) {
/* 258 */       flag = 4;
/*     */     }
/* 260 */     drawSequenceflowLabel(bpmnEdge.getName(), (int)bpmnEdge.getMidpoint().getX(), (int)bpmnEdge.getMidpoint().getY(), flag);
/*     */   }
/*     */ 
/*     */   public void drawSequenceflowLabel(String name, int x, int y, int flag)
/*     */   {
/* 283 */     if (name == null) {
/* 284 */       return;
/*     */     }
/* 286 */     int drawX = x; int drawY = y;
/* 287 */     switch (flag) {
/*     */     case 1:
/* 289 */       drawX = x + this.g.getFontMetrics().getHeight() / 2;
/* 290 */       drawY = y;
/* 291 */       break;
/*     */     case 2:
/* 293 */       drawX = x - this.g.getFontMetrics().stringWidth(name) - this.g.getFontMetrics().getHeight() / 2;
/*     */ 
/* 295 */       drawY = y + this.g.getFontMetrics().getHeight();
/* 296 */       break;
/*     */     case 3:
/* 298 */       drawX = x - this.g.getFontMetrics().stringWidth(name) / 2;
/* 299 */       drawY = y - this.g.getFontMetrics().getHeight() / 2;
/* 300 */       break;
/*     */     case 4:
/* 302 */       drawX = x - this.g.getFontMetrics().stringWidth(name) / 2;
/* 303 */       drawY = y + this.g.getFontMetrics().getHeight();
/*     */     }
/*     */ 
/* 306 */     Paint originalPaint = this.g.getPaint();
/* 307 */     Stroke originalStroke = this.g.getStroke();
/* 308 */     this.g.setPaint(new Color(80, 160, 240));
/* 309 */     this.g.setStroke(LABEL_STROKE);
/* 310 */     this.g.drawString(name, drawX, drawY);
/* 311 */     this.g.setStroke(originalStroke);
/* 312 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawArrowHead(Line2D.Double line) {
/* 316 */     int doubleArrowWidth = 10;
/* 317 */     Polygon arrowHead = new Polygon();
/* 318 */     arrowHead.addPoint(0, 0);
/* 319 */     arrowHead.addPoint(-5, -doubleArrowWidth);
/* 320 */     arrowHead.addPoint(5, -doubleArrowWidth);
/*     */ 
/* 322 */     AffineTransform transformation = new AffineTransform();
/* 323 */     transformation.setToIdentity();
/* 324 */     double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
/* 325 */     transformation.translate(line.x2, line.y2);
/* 326 */     transformation.rotate(angle - 1.570796326794897D);
/*     */ 
/* 328 */     AffineTransform originalTransformation = this.g.getTransform();
/* 329 */     this.g.setTransform(transformation);
/* 330 */     this.g.fill(arrowHead);
/* 331 */     this.g.setTransform(originalTransformation);
/*     */   }
/*     */ 
/*     */   public void drawConditionalSequenceFlowIndicator(Line2D.Double line) {
/* 335 */     int horizontal = 11;
/* 336 */     int halfOfHorizontal = horizontal / 2;
/* 337 */     int halfOfVertical = 8;
/*     */ 
/* 339 */     Polygon conditionalIndicator = new Polygon();
/* 340 */     conditionalIndicator.addPoint(0, 0);
/* 341 */     conditionalIndicator.addPoint(-halfOfHorizontal, halfOfVertical);
/* 342 */     conditionalIndicator.addPoint(0, 16);
/* 343 */     conditionalIndicator.addPoint(halfOfHorizontal, halfOfVertical);
/*     */ 
/* 345 */     AffineTransform transformation = new AffineTransform();
/* 346 */     transformation.setToIdentity();
/* 347 */     double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
/* 348 */     transformation.translate(line.x1, line.y1);
/* 349 */     transformation.rotate(angle - 1.570796326794897D);
/*     */ 
/* 351 */     AffineTransform originalTransformation = this.g.getTransform();
/* 352 */     this.g.setTransform(transformation);
/* 353 */     this.g.draw(conditionalIndicator);
/*     */ 
/* 355 */     Paint originalPaint = this.g.getPaint();
/* 356 */     this.g.setPaint(CONDITIONAL_INDICATOR_COLOR);
/* 357 */     this.g.fill(conditionalIndicator);
/*     */ 
/* 359 */     this.g.setPaint(originalPaint);
/* 360 */     this.g.setTransform(originalTransformation);
/*     */   }
/*     */ 
/*     */   public void drawTask(String name, int x, int y, int width, int height) {
/* 364 */     drawTask(name, x, y, width, height, false);
/*     */   }
/*     */ 
/*     */   protected void drawTask(String name, int x, int y, int width, int height, boolean thickBorder)
/*     */   {
/* 369 */     Paint originalPaint = this.g.getPaint();
/* 370 */     this.g.setPaint(TASK_COLOR);
/*     */ 
/* 372 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/*     */ 
/* 374 */     this.g.fill(rect);
/* 375 */     this.g.setPaint(originalPaint);
/*     */ 
/* 377 */     if (thickBorder) {
/* 378 */       Stroke originalStroke = this.g.getStroke();
/* 379 */       this.g.setStroke(THICK_TASK_BORDER_STROKE);
/* 380 */       this.g.draw(rect);
/* 381 */       this.g.setStroke(originalStroke);
/*     */     } else {
/* 383 */       this.g.draw(rect);
/*     */     }
/*     */ 
/* 394 */     if (name != null) {
/* 395 */       String[] nameAry = fitTextToWidthAndBreak(name, width);
/* 396 */       int offsetY = this.fontMetrics.getHeight();
/* 397 */       int textY = y + (height - this.fontMetrics.getHeight()) / 2;
/* 398 */       for (int i = 0; i < nameAry.length; i++) {
/* 399 */         String l = nameAry[i];
/* 400 */         int textX = x + (width - this.fontMetrics.stringWidth(l)) / 2;
/* 401 */         this.g.drawString(l, textX, textY + i * offsetY);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private String[] fitTextToWidthAndBreak(String original, int width) {
/* 407 */     String line1 = "";
/* 408 */     String tmp1 = "";
/* 409 */     String line2 = "";
/*     */ 
/* 411 */     int maxWidth = width - 10;
/*     */ 
/* 413 */     if (this.fontMetrics.stringWidth(original) <= maxWidth) {
/* 414 */       return new String[] { original };
/*     */     }
/*     */ 
/* 419 */     while (this.fontMetrics.stringWidth(tmp1) <= maxWidth) {
/* 420 */       line1 = tmp1;
/* 421 */       tmp1 = original.substring(0, line1.length() + 1);
/*     */     }
/*     */ 
/* 424 */     line2 = original.substring(line1.length(), original.length());
/*     */ 
/* 427 */     if (this.fontMetrics.stringWidth(line2) <= maxWidth) {
/* 428 */       return new String[] { line1, line2 };
/*     */     }
/*     */ 
/* 434 */     while ((this.fontMetrics.stringWidth(line2 + "...") > maxWidth) && (line2.length() > 0)) {
/* 435 */       line2 = line2.substring(0, line2.length() - 1);
/*     */     }
/*     */ 
/* 438 */     if (!line2.equals(original)) {
/* 439 */       line2 = line2 + "...";
/*     */     }
/*     */ 
/* 442 */     return new String[] { line1, line2 };
/*     */   }
/*     */ 
/*     */   protected String fitTextToWidth(String original, int width)
/*     */   {
/* 449 */     String text = original;
/* 450 */     String line1 = "";
/* 451 */     String tmp1 = "";
/* 452 */     String line2 = "";
/*     */ 
/* 454 */     int maxWidth = width - 10;
/*     */ 
/* 456 */     if (this.fontMetrics.stringWidth(original) <= maxWidth) {
/* 457 */       return original;
/*     */     }
/*     */ 
/* 460 */     while (this.fontMetrics.stringWidth(tmp1) <= maxWidth) {
/* 461 */       line1 = tmp1;
/* 462 */       tmp1 = original.substring(0, line1.length() + 1);
/*     */     }
/*     */ 
/* 465 */     line2 = original.substring(line1.length(), original.length());
/* 466 */     while ((this.fontMetrics.stringWidth(line2 + "...") > maxWidth) && (line2.length() > 0)) {
/* 467 */       line2 = line2.substring(0, line2.length() - 1);
/*     */     }
/*     */ 
/* 470 */     if (!line2.equals(original)) {
/* 471 */       line2 = line2 + "...";
/*     */     }
/*     */ 
/* 474 */     return line1 + "\r\n" + line2;
/*     */   }
/*     */ 
/*     */   public void drawUserTask(String name, int x, int y, int width, int height)
/*     */   {
/* 491 */     drawTask(name, x, y, width, height);
/* 492 */     int ix = x + (width - ICON_SIZE) / 2;
/* 493 */     int iy = y + height - ICON_SIZE;
/* 494 */     this.g.drawImage(USERTASK_IMAGE, ix, iy, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawScriptTask(String name, int x, int y, int width, int height)
/*     */   {
/* 501 */     drawTask(name, x, y, width, height);
/* 502 */     this.g.drawImage(SCRIPTTASK_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawServiceTask(String name, int x, int y, int width, int height)
/*     */   {
/* 507 */     drawTask(name, x, y, width, height);
/* 508 */     this.g.drawImage(SERVICETASK_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawReceiveTask(String name, int x, int y, int width, int height)
/*     */   {
/* 513 */     drawTask(name, x, y, width, height);
/* 514 */     this.g.drawImage(RECEIVETASK_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawSendTask(String name, int x, int y, int width, int height)
/*     */   {
/* 519 */     drawTask(name, x, y, width, height);
/* 520 */     this.g.drawImage(SENDTASK_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawManualTask(String name, int x, int y, int width, int height)
/*     */   {
/* 525 */     drawTask(name, x, y, width, height);
/* 526 */     this.g.drawImage(MANUALTASK_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   public void drawExpandedSubProcess(String name, int x, int y, int width, int height)
/*     */   {
/* 532 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/*     */ 
/* 534 */     this.g.draw(rect);
/*     */ 
/* 536 */     String text = fitTextToWidth(name, width);
/* 537 */     this.g.drawString(text, x + 10, y + 15);
/*     */   }
/*     */ 
/*     */   public void drawCollapsedSubProcess(String name, int x, int y, int width, int height)
/*     */   {
/* 542 */     drawCollapsedTask(name, x, y, width, height, false);
/*     */   }
/*     */ 
/*     */   public void drawCollapsedCallActivity(String name, int x, int y, int width, int height)
/*     */   {
/* 547 */     drawCollapsedTask(name, x, y, width, height, false);
/* 548 */     this.g.drawImage(CALLACTIVITY_IMAGE, x + 7, y + 7, ICON_SIZE, ICON_SIZE, null);
/*     */   }
/*     */ 
/*     */   protected void drawCollapsedTask(String name, int x, int y, int width, int height, boolean thickBorder)
/*     */   {
/* 555 */     drawTask(name, x, y, width, height, thickBorder);
/*     */   }
/*     */ 
/*     */   public void drawCollapsedMarker(int x, int y, int width, int height) {
/* 559 */     int rectangleWidth = 12;
/* 560 */     int rectangleHeight = 12;
/* 561 */     Rectangle rect = new Rectangle(x + (width - rectangleWidth) / 2, y + height - rectangleHeight - 3, rectangleWidth, rectangleHeight);
/*     */ 
/* 563 */     this.g.draw(rect);
/*     */ 
/* 565 */     Line2D.Double line = new Line2D.Double(rect.getCenterX(), rect.getY() + 2.0D, rect.getCenterX(), rect.getMaxY() - 2.0D);
/*     */ 
/* 567 */     this.g.draw(line);
/* 568 */     line = new Line2D.Double(rect.getMinX() + 2.0D, rect.getCenterY(), rect.getMaxX() - 2.0D, rect.getCenterY());
/*     */ 
/* 570 */     this.g.draw(line);
/*     */   }
/*     */ 
/*     */   public void drawActivityMarkers(int x, int y, int width, int height, boolean multiInstanceSequential, boolean multiInstanceParallel, boolean collapsed)
/*     */   {
/* 576 */     if (collapsed) {
/* 577 */       if ((!multiInstanceSequential) && (!multiInstanceParallel)) {
/* 578 */         drawCollapsedMarker(x, y, width, height);
/*     */       } else {
/* 580 */         drawCollapsedMarker(x - 6 - 2, y, width, height);
/* 581 */         if (multiInstanceSequential)
/* 582 */           drawMultiInstanceMarker(true, x + 6 + 2, y, width, height);
/* 583 */         else if (multiInstanceParallel)
/* 584 */           drawMultiInstanceMarker(false, x + 6 + 2, y, width, height);
/*     */       }
/*     */     }
/* 587 */     else if (multiInstanceSequential)
/* 588 */       drawMultiInstanceMarker(false, x, y, width, height);
/* 589 */     else if (multiInstanceParallel)
/* 590 */       drawMultiInstanceMarker(true, x, y, width, height);
/*     */   }
/*     */ 
/*     */   public void drawGateway(String name, int x, int y, int width, int height) {
/* 594 */     Polygon rhombus = new Polygon();
/* 595 */     rhombus.addPoint(x, y + height / 2);
/* 596 */     rhombus.addPoint(x + width / 2, y + height);
/* 597 */     rhombus.addPoint(x + width, y + height / 2);
/* 598 */     rhombus.addPoint(x + width / 2, y);
/* 599 */     this.g.draw(rhombus);
/*     */ 
/* 601 */     if (name != null) {
/* 602 */       int textX = x + width / 4 - this.fontMetrics.stringWidth(name) - this.fontMetrics.getHeight() / 4;
/*     */ 
/* 604 */       int textY = y + height / 4 - this.fontMetrics.getHeight() / 4;
/* 605 */       this.g.drawString(name, textX, textY);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void drawParallelGateway(String name, int x, int y, int width, int height)
/*     */   {
/* 611 */     drawGateway(name, x, y, width, height);
/* 612 */     Stroke orginalStroke = this.g.getStroke();
/* 613 */     this.g.setStroke(GATEWAY_TYPE_STROKE);
/* 614 */     Line2D.Double line = new Line2D.Double(x + 10, y + height / 2, x + width - 10, y + height / 2);
/*     */ 
/* 616 */     this.g.draw(line);
/* 617 */     line = new Line2D.Double(x + width / 2, y + height - 10, x + width / 2, y + 10);
/*     */ 
/* 619 */     this.g.draw(line);
/* 620 */     this.g.setStroke(orginalStroke);
/*     */   }
/*     */ 
/*     */   public void drawExclusiveGateway(String name, int x, int y, int width, int height)
/*     */   {
/* 625 */     drawGateway(name, x, y, width, height);
/*     */ 
/* 627 */     int quarterWidth = width / 4;
/* 628 */     int quarterHeight = height / 4;
/*     */ 
/* 630 */     Stroke orginalStroke = this.g.getStroke();
/* 631 */     this.g.setStroke(GATEWAY_TYPE_STROKE);
/* 632 */     Line2D.Double line = new Line2D.Double(x + quarterWidth + 3, y + quarterHeight + 3, x + 3 * quarterWidth - 3, y + 3 * quarterHeight - 3);
/*     */ 
/* 635 */     this.g.draw(line);
/* 636 */     line = new Line2D.Double(x + quarterWidth + 3, y + 3 * quarterHeight - 3, x + 3 * quarterWidth - 3, y + quarterHeight + 3);
/*     */ 
/* 638 */     this.g.draw(line);
/*     */ 
/* 640 */     this.g.setStroke(orginalStroke);
/*     */   }
/*     */ 
/*     */   public void drawInclusiveGateway(String name, int x, int y, int width, int height)
/*     */   {
/* 645 */     drawGateway(name, x, y, width, height);
/*     */ 
/* 647 */     int diameter = width / 2;
/*     */ 
/* 649 */     Stroke orginalStroke = this.g.getStroke();
/* 650 */     this.g.setStroke(GATEWAY_TYPE_STROKE);
/* 651 */     Ellipse2D.Double circle = new Ellipse2D.Double((width - diameter) / 2 + x, (height - diameter) / 2 + y, diameter, diameter);
/*     */ 
/* 653 */     this.g.draw(circle);
/* 654 */     this.g.setStroke(orginalStroke);
/*     */   }
/*     */ 
/*     */   public void drawMultiInstanceMarker(boolean sequential, int x, int y, int width, int height)
/*     */   {
/* 659 */     int rectangleWidth = 12;
/* 660 */     int rectangleHeight = 12;
/* 661 */     int lineX = x + (width - rectangleWidth) / 2 + ICON_SIZE;
/* 662 */     int lineY = y + height - rectangleHeight - 3;
/*     */ 
/* 664 */     Stroke orginalStroke = this.g.getStroke();
/* 665 */     this.g.setStroke(MULTI_INSTANCE_STROKE);
/*     */ 
/* 667 */     if (sequential) {
/* 668 */       this.g.draw(new Line2D.Double(lineX, lineY, lineX, lineY + rectangleHeight));
/*     */ 
/* 670 */       this.g.draw(new Line2D.Double(lineX + rectangleWidth / 2, lineY, lineX + rectangleWidth / 2, lineY + rectangleHeight));
/*     */ 
/* 672 */       this.g.draw(new Line2D.Double(lineX + rectangleWidth, lineY, lineX + rectangleWidth, lineY + rectangleHeight));
/*     */     }
/*     */     else {
/* 675 */       this.g.draw(new Line2D.Double(lineX, lineY, lineX + rectangleWidth, lineY));
/*     */ 
/* 677 */       this.g.draw(new Line2D.Double(lineX, lineY + rectangleHeight / 2, lineX + rectangleWidth, lineY + rectangleHeight / 2));
/*     */ 
/* 679 */       this.g.draw(new Line2D.Double(lineX, lineY + rectangleHeight, lineX + rectangleWidth, lineY + rectangleHeight));
/*     */     }
/*     */ 
/* 683 */     this.g.setStroke(orginalStroke);
/*     */   }
/*     */ 
/*     */   public void drawHighLight(int x, int y, int width, int height) {
/* 687 */     Paint originalPaint = this.g.getPaint();
/* 688 */     Stroke originalStroke = this.g.getStroke();
/*     */ 
/* 690 */     this.g.setPaint(HIGHLIGHT_COLOR);
/* 691 */     this.g.setStroke(THICK_TASK_BORDER_STROKE);
/*     */ 
/* 693 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/*     */ 
/* 695 */     this.g.draw(rect);
/*     */ 
/* 697 */     this.g.setPaint(originalPaint);
/* 698 */     this.g.setStroke(originalStroke);
/*     */   }
/*     */ 
/*     */   public void drawHighLight(int x, int y, int width, int height, Short status) {
/* 702 */     Paint originalPaint = this.g.getPaint();
/* 703 */     Stroke originalStroke = this.g.getStroke();
/* 704 */     Color color = (Color)colorsMap.get(status);
/* 705 */     this.g.setPaint(color);
/* 706 */     if (status != ITaskOpinion.STATUS_INIT)
/* 707 */       this.g.setStroke(THICK_TASK_BORDER_STROKE);
/*     */     else {
/* 709 */       this.g.setStroke(THIN_TASK_BORDER_STROKE);
/*     */     }
/*     */ 
/* 712 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/*     */ 
/* 714 */     this.g.draw(rect);
/*     */ 
/* 716 */     this.g.setPaint(originalPaint);
/* 717 */     this.g.setStroke(originalStroke);
/*     */   }
/*     */ 
/*     */   public void drawHighLight(int x, int y, int width, int height, String colorStr)
/*     */   {
/* 722 */     boolean isNormal = false;
/* 723 */     if (colorStr == null) {
/* 724 */       colorStr = "#000000";
/* 725 */       isNormal = true;
/*     */     }
/* 727 */     Color color = Color.decode(colorStr);
/* 728 */     Paint originalPaint = this.g.getPaint();
/* 729 */     Stroke originalStroke = this.g.getStroke();
/*     */ 
/* 731 */     this.g.setPaint(color);
/* 732 */     if (isNormal)
/* 733 */       this.g.setStroke(THICK2_TASK_BORDER_STROKE);
/*     */     else {
/* 735 */       this.g.setStroke(THICK_TASK_BORDER_STROKE);
/*     */     }
/*     */ 
/* 739 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/* 740 */     this.g.draw(rect);
/*     */ 
/* 742 */     this.g.setPaint(originalPaint);
/* 743 */     this.g.setStroke(originalStroke);
/*     */   }
/*     */ 
/*     */   public void drawHPool(String name, int x, int y, int width, int height) {
/* 747 */     Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
/* 748 */     Paint originalPaint = this.g.getPaint();
/*     */ 
/* 750 */     this.g.setPaint(POOL_BACKGROUP_COLOR);
/* 751 */     RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(x, y, 20.0D, height, 0.0D, 0.0D);
/* 752 */     this.g.fill(roundRectangle2D);
/*     */ 
/* 754 */     Stroke originalStroke = this.g.getStroke();
/* 755 */     this.g.setPaint(POOL_BOUNDARY_COLOR);
/* 756 */     this.g.draw(rect);
/*     */ 
/* 760 */     int textLen = this.fontMetrics.stringWidth(name);
/* 761 */     int textX = x + this.fontMetrics.getHeight() / 2;
/* 762 */     int textY = y + height / 2 - textLen / 2;
/*     */ 
/* 764 */     AffineTransform oldAffineTransform = this.g.getTransform();
/* 765 */     AffineTransform newAffineTransform = AffineTransform.getRotateInstance(-1.570796326794897D, textX, textY);
/*     */ 
/* 767 */     this.g.setTransform(newAffineTransform);
/* 768 */     this.g.setPaint(DEFAULT_COLOR);
/* 769 */     this.g.drawString(name, textX - textLen, textY + this.fontMetrics.getHeight() / 2);
/* 770 */     this.g.setTransform(oldAffineTransform);
/* 771 */     this.g.setStroke(originalStroke);
/* 772 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawHLane(String name, int x, int y, int width, int height)
/*     */   {
/* 777 */     Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
/* 778 */     Paint originalPaint = this.g.getPaint();
/*     */ 
/* 780 */     this.g.setPaint(LANE_BACKGROUP_COLOR);
/* 781 */     RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(x, y, 20.0D, height, 0.0D, 0.0D);
/* 782 */     this.g.fill(roundRectangle2D);
/*     */ 
/* 784 */     Stroke originalStroke = this.g.getStroke();
/* 785 */     this.g.setPaint(LANE_BOUNDARY_COLOR);
/* 786 */     this.g.draw(rect);
/*     */ 
/* 789 */     int textLen = this.fontMetrics.stringWidth(name);
/* 790 */     int textX = x + this.fontMetrics.getHeight() / 2;
/* 791 */     int textY = y + height / 2 - textLen / 2;
/*     */ 
/* 793 */     AffineTransform oldAffineTransform = this.g.getTransform();
/* 794 */     AffineTransform newAffineTransform = AffineTransform.getRotateInstance(-1.570796326794897D, textX, textY);
/*     */ 
/* 797 */     this.g.setTransform(newAffineTransform);
/* 798 */     this.g.setPaint(DEFAULT_COLOR);
/* 799 */     this.g.drawString(name, textX - textLen, textY + this.fontMetrics.getHeight() / 2);
/* 800 */     this.g.setTransform(oldAffineTransform);
/* 801 */     this.g.setStroke(originalStroke);
/* 802 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawVPool(String name, int x, int y, int width, int height)
/*     */   {
/* 807 */     Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
/* 808 */     Paint originalPaint = this.g.getPaint();
/* 809 */     Stroke originalStroke = this.g.getStroke();
/*     */ 
/* 811 */     this.g.setPaint(POOL_BACKGROUP_COLOR);
/* 812 */     RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(x, y, width, 20.0D, 0.0D, 0.0D);
/* 813 */     this.g.fill(roundRectangle2D);
/*     */ 
/* 815 */     this.g.setPaint(POOL_BOUNDARY_COLOR);
/* 816 */     this.g.draw(rect);
/*     */ 
/* 820 */     name = fitTextToWidth(name, width);
/* 821 */     int textLen = this.fontMetrics.stringWidth(name);
/* 822 */     int textY = y + this.fontMetrics.getHeight();
/* 823 */     int textX = x + width / 2 - textLen / 2;
/* 824 */     this.g.setPaint(DEFAULT_COLOR);
/* 825 */     this.g.drawString(name, textX, textY);
/* 826 */     this.g.setStroke(originalStroke);
/* 827 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public void drawVLane(String name, int x, int y, int width, int height)
/*     */   {
/* 832 */     Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
/* 833 */     Paint originalPaint = this.g.getPaint();
/* 834 */     Stroke originalStroke = this.g.getStroke();
/*     */ 
/* 836 */     this.g.setPaint(LANE_BACKGROUP_COLOR);
/* 837 */     RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(x, y, width, 20.0D, 0.0D, 0.0D);
/* 838 */     this.g.fill(roundRectangle2D);
/*     */ 
/* 840 */     this.g.setPaint(LANE_BOUNDARY_COLOR);
/* 841 */     this.g.draw(rect);
/*     */ 
/* 843 */     name = fitTextToWidth(name, width);
/* 844 */     int textLen = this.fontMetrics.stringWidth(name);
/* 845 */     int textY = y + this.fontMetrics.getHeight();
/* 846 */     int textX = x + width / 2 - textLen / 2;
/* 847 */     this.g.setPaint(DEFAULT_COLOR);
/* 848 */     this.g.drawString(name, textX, textY);
/* 849 */     this.g.setStroke(originalStroke);
/* 850 */     this.g.setPaint(originalPaint);
/*     */   }
/*     */ 
/*     */   public static Color getColor(String colorString)
/*     */   {
/* 923 */     String tempCOLOR = PropertyUtil.getByAlias(colorString);
/* 924 */     Color color = null;
/* 925 */     if ((!"".equals(tempCOLOR)) && (tempCOLOR != null)) {
/* 926 */       String[] COLOR_Strings = tempCOLOR.split(",");
/* 927 */       color = new Color(Integer.parseInt(COLOR_Strings[0]), Integer.parseInt(COLOR_Strings[1]), Integer.parseInt(COLOR_Strings[2]));
/*     */     }
/*     */ 
/* 930 */     return color;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/* 855 */       USERTASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/user.png"));
/*     */ 
/* 858 */       SCRIPTTASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/script.png"));
/*     */ 
/* 861 */       SERVICETASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/service.png"));
/*     */ 
/* 864 */       RECEIVETASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/receive.png"));
/*     */ 
/* 867 */       SENDTASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/send.png"));
/*     */ 
/* 870 */       MANUALTASK_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/manual.png"));
/*     */ 
/* 873 */       TIMER_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/timer.png"));
/*     */ 
/* 876 */       ERROR_THROW_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/error_throw.png"));
/*     */ 
/* 879 */       ERROR_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/error_catch.png"));
/*     */ 
/* 882 */       CALLACTIVITY_IMAGE = ImageIO.read(ReflectUtil.getResourceAsStream("com/hotent/core/bpm/graph/image/call_activity.png"));
/*     */ 
/* 886 */       colorsMap.put(ITaskOpinion.STATUS_INIT, Color.darkGray);
/* 887 */       colorsMap.put(ITaskOpinion.STATUS_AGREE, Color.green);
/* 888 */       colorsMap.put(ITaskOpinion.STATUS_ABANDON, Color.orange);
/* 889 */       colorsMap.put(ITaskOpinion.STATUS_CHECKING, Color.red);
/* 890 */       colorsMap.put(ITaskOpinion.STATUS_REFUSE, Color.blue);
/* 891 */       colorsMap.put(ITaskOpinion.STATUS_REJECT, new Color(138, 9, 2));
/* 892 */       colorsMap.put(ITaskOpinion.STATUS_RECOVER, new Color(2, 59, 98));
/* 893 */       colorsMap.put(ITaskOpinion.STATUS_PASSED, new Color(51, 136, 72));
/* 894 */       colorsMap.put(ITaskOpinion.STATUS_NOT_PASSED, new Color(130, 183, 215));
/*     */ 
/* 898 */       Color pool_backgroup_color_temp = getColor("POOL_BACKGROUP_COLOR");
/* 899 */       Color pool_boundary_color_temp = getColor("LANE_BOUNDARY_COLOR");
/*     */ 
/* 901 */       if (pool_backgroup_color_temp != null) {
/* 902 */         POOL_BACKGROUP_COLOR = pool_backgroup_color_temp;
/* 903 */         LANE_BACKGROUP_COLOR = pool_backgroup_color_temp;
/*     */       } else {
/* 905 */         POOL_BACKGROUP_COLOR = new Color(211, 215, 212);
/* 906 */         LANE_BACKGROUP_COLOR = new Color(211, 215, 212);
/*     */       }
/*     */ 
/* 909 */       if (pool_boundary_color_temp != null) {
/* 910 */         POOL_BOUNDARY_COLOR = pool_boundary_color_temp;
/* 911 */         LANE_BOUNDARY_COLOR = pool_boundary_color_temp;
/*     */       } else {
/* 913 */         POOL_BOUNDARY_COLOR = new Color(138, 140, 142);
/* 914 */         LANE_BOUNDARY_COLOR = new Color(138, 140, 142);
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 918 */       LOGGER.warning("Could not load image for process diagram creation: " + e.getMessage());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.activiti.ProcessDiagramCanvas
 * JD-Core Version:    0.6.2
 */