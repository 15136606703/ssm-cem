package com.zh.crm.settings.domain;

public class User {

    /*
    * 关于字符串中变现的日期及时间
    * 市场上的两种方式：
    *   日期：年月日 yyyy-MM-dd 10位的字符串 char(10)
    *   日期+时间 年月日时分秒 19位字符串
    * */

    /*
    * 关于登陆
    *   验证账号密码
    *   User user = select * from tbl_user where loginAct=? and loginPwd=?
    *   如果user对象为空账号密码错误，不为空继续验证字段信息
    *   从user中get expireTime验证失效时间 lockState验证锁定状态 allowIps验证ip地址
    * */
    private String id;//编号主键
    private String loginAct;//登陆账号
    private String name;//用户的真实姓名
    private String loginPwd;//密码
    private String email;//邮箱
    private String expireTime;//失效时间
    private String lockState;// 锁定状态 0锁定1启用
    private String deptno;// 部门编号
    private String allowIps;//允许访问的ip地址
    private String createTime;//创建时间
    private String createBy;//创建人
    private String editTime;//修改时间
    private String editBy;//修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginAct='" + loginAct + '\'' +
                ", name='" + name + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", lockState='" + lockState + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowIps='" + allowIps + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}
