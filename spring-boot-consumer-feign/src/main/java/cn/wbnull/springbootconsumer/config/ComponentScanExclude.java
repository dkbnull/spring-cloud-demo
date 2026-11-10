package cn.wbnull.springbootconsumer.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * \@ComponentScan 忽略扫描注解
 *
 * @author dukunbiao(null)  2019-04-25
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScanExclude {
}
