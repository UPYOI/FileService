package com.songyutong.fileupload.log;

import java.lang.annotation.*;

/**
 * 日志方法
 *
 * @author ：songyutong
 * @date ：2024/1/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface LogMethod {

    /**
     * 方法描述
     */
    String desc() default "";

    /**
     * 是否展示方法耗时
     */
    boolean showTimeConsuming() default true;
}
