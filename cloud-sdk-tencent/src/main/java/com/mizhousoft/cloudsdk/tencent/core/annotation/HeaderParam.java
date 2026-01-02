package com.mizhousoft.cloudsdk.tencent.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Header参数
 *
 * @version
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HeaderParam
{
	/**
	 * 值
	 * 
	 * @return
	 */
	String value();
}
