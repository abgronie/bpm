package com.hotent.core.fulltext;

import org.apache.lucene.index.IndexWriter;

public abstract interface IOperator
{
  public abstract void addDocument(IndexWriter paramIndexWriter);

  public abstract String[] getFields();
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.IOperator
 * JD-Core Version:    0.6.2
 */