package com.gwssi.struct.utils;

import java.io.PrintStream;

/**
 * 报表异常信息类
 * @author WeiQiang
 * @date 2010-12-27 下午09:23:17
 */
public class ReportException extends RuntimeException {

	private static final long serialVersionUID = 7693849152033781081L;
	
	/**
	 * 单元格ID
	 */
	private String cellId = null;
	
	/**
	 * 错误信息
	 */
	private String errorMsg = null;
	
	/**
	 * 错误原因
	 */
	private Throwable cause = null;

	public ReportException() {
		super("report error");
	}

	public ReportException(String paramString) {
		super(paramString);

		this.errorMsg = paramString;
	}
	
	public ReportException(Throwable e) {
        super(e);
        
        this.cause = e;
    }

	public ReportException(String paramString, Throwable paramThrowable) {
		super(paramString);

		this.errorMsg = paramString;
		this.cause = paramThrowable;
	}

	public synchronized Throwable getCause() {
		if (this.cause == this)
			return null;
		return this.cause;
	}

	public String getErrorMsg() {
		if (this.errorMsg == null)
			return "";
		return this.errorMsg;
	}

	public String getMessage() {
		return toString();
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream paramPrintStream) {
//		synchronized (paramPrintStream) {
			super.printStackTrace(paramPrintStream);
			return;
//		}
	}

	public void setCellId(String paramString) {
		this.cellId = paramString;
	}

	public void setErrorMsg(String paramString) {
		this.errorMsg = paramString;
	}

	public String toString() {
	    
		StringBuilder buf = new StringBuilder(128);
		
		if (this.cellId != null) {
			buf.append("[").append(this.cellId).append("]");
		}
		
		if (this.errorMsg != null) {
		    if (buf.length() > 0) {
                buf.append(" ");
            }
			buf.append("").append(this.errorMsg);
		}
		
		if (this.cause != null) {
			if (buf.length() > 0) {
				buf.append(" ");
			}
			buf.append("来源：");
			
			if (this.cause.getMessage() == null) {
				buf.append(this.cause.getClass().getName());
			} else {
				buf.append(this.cause.getMessage());
			}
		}
		
		return buf.toString();
	}
}
