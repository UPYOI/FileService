package com.songyutong.fileupload.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本响应类
 *
 * @author: songyutong
 * @date: 2024/1/25
 */
@Data
public class BaseResponse implements Serializable {

    /**
     * 单个结果返回
     */
    private Object data;

    /**
     * 返回码
     */
    private String retCode = "00";

    /**
     * 返回消息
     */
    private String retMsg = "处理成功";

    public static final String SUCCESS = "00";
    public static final String FAIL = "10";
    public static final String ROWS = "rows";
    public static final String TOTAL = "total";

    /**
     * 默认请求失败
     *
     * @param msg 失败消息
     */
    public BaseResponse(String msg) {
        setRetCode(FAIL);
        setRetMsg(msg);
    }

    /**
     * 默认请求失败
     */
    public BaseResponse() {
        setRetCode(FAIL);
        setRetMsg("处理失败");
    }

    public void buildSuccess() {
        setRetCode(SUCCESS);
        setRetMsg("处理成功");
    }

}
