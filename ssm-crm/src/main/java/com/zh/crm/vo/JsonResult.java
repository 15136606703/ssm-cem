package com.zh.crm.vo;

import java.io.Serializable;

/**
 * @Classname JsonResult
 * @Date 2021-1-7 10:02
 * @Created by 张浩
 *
 * 统一返回数据vo类
 */
public class JsonResult implements Serializable {
    /*
    * serialVersionUID 用来表明类的不同版本间的兼容性
    * 序列化的时候，被序列化的类要有一个唯一标记。客户端和服务端必须需要同一个对象，serialVersionUID的唯一值判定其为同一个对象
    * */
    private static final long serialVersionUID = 8676392564995403365L;
    public static String STATE_SUCCESS = "1";
    public static String STATE_ERROR = "-1";

    private String state; //状态
    private String message; //消息
    private Object data; //数据

    public JsonResult() {
    }

    public JsonResult(String state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
