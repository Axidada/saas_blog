package com.gt.domain.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 */
@Entity
@Table(name = "bs_user")
@ApiModel(description = "用户对象user")
public class User implements Serializable {
    private static final long serialVersionUID = 4297464181093070302L;
    /**
     * ID
     */
    @Id
    @ApiModelProperty(value = "主键", name = "id")
    private String id;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", name = "mobile")
    private String mobile;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", name = "username")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    @ApiModelProperty(value = "启用状态", name = "enableState", required = true)
    private Integer enableState;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "部门ID", name = "companyId")
    private String companyId;
    @ApiModelProperty(value = "部门名字", name = "companyName")
    private String companyName;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID", name = "departmentId")
    private String departmentId;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", name = "timeOfEntry")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    @ApiModelProperty(value = "聘用形式", name = "formOfEmployment")
    private Integer formOfEmployment;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号", name = "workNumber")
    private String workNumber;

    /**
     * 管理形式
     */
    @ApiModelProperty(value = "管理形式", name = "formOfManagement")
    private String formOfManagement;

    /**
     * 工作城市
     */
    @ApiModelProperty(value = "工作城市", name = "workingCity")
    private String workingCity;

    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间", name = "correctionTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    @ApiModelProperty(value = "在职状态", name = "inServiceStatus", required = true)
    private Integer inServiceStatus;
    @ApiModelProperty(value = "部门名称", name = "departmentName")
    private String departmentName;

    /**
     * level
     * String
     * saasAdmin
     * coAdmin
     * user
     */
    @ApiModelProperty(value = "地位等级", name = "level")
    private String level;

    /**
     * JsonIgnore
     * 忽略json转化
     */

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "pe_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

    public User() {
    }

    public User(String id, String mobile, String username, String password, Integer enableState, Date createTime, String companyId, String companyName, String departmentId, Date timeOfEntry, Integer formOfEmployment, String workNumber, String formOfManagement, String workingCity, Date correctionTime, Integer inServiceStatus, String departmentName, Set<Role> roles) {
        this.id = id;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.enableState = enableState;
        this.createTime = createTime;
        this.companyId = companyId;
        this.companyName = companyName;
        this.departmentId = departmentId;
        this.timeOfEntry = timeOfEntry;
        this.formOfEmployment = formOfEmployment;
        this.workNumber = workNumber;
        this.formOfManagement = formOfManagement;
        this.workingCity = workingCity;
        this.correctionTime = correctionTime;
        this.inServiceStatus = inServiceStatus;
        this.departmentName = departmentName;
        this.roles = roles;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnableState() {
        return enableState;
    }

    public void setEnableState(Integer enableState) {
        this.enableState = enableState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(Date timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public Integer getFormOfEmployment() {
        return formOfEmployment;
    }

    public void setFormOfEmployment(Integer formOfEmployment) {
        this.formOfEmployment = formOfEmployment;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getFormOfManagement() {
        return formOfManagement;
    }

    public void setFormOfManagement(String formOfManagement) {
        this.formOfManagement = formOfManagement;
    }

    public String getWorkingCity() {
        return workingCity;
    }

    public void setWorkingCity(String workingCity) {
        this.workingCity = workingCity;
    }

    public Date getCorrectionTime() {
        return correctionTime;
    }

    public void setCorrectionTime(Date correctionTime) {
        this.correctionTime = correctionTime;
    }

    public Integer getInServiceStatus() {
        return inServiceStatus;
    }

    public void setInServiceStatus(Integer inServiceStatus) {
        this.inServiceStatus = inServiceStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
