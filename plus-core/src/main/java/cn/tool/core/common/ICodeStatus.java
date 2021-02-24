package cn.tool.core.common;

public interface ICodeStatus {
	/**
	 * Return the integer value of this status code.
	 */
	String value();
	
	/**
	 * Return the reason phrase of this status code.
	 */
	String getReasonPhrase();
}