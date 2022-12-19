package com.gt.domain.company;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类代码：
 * 属性
 * 构造方法
 * getter，setter方法
 * <p>
 * lombok 插件 : 使用注解的形式替换getter setter，构造方法
 * 如何使用插件
 * 1.安装插件（在工程中引入响应的插件坐标即可）
 * <dependency>
 * <groupId>org.projectlombok</groupId>
 * <artifactId>lombok</artifactId>
 * <version>1.16.16</version>
 * </dependency>
 * 2.使用注解配置
 * 配置到实体类上
 *
 * @setter : setter方法
 * @getter ：getter方法
 * @NoArgsConstructor 无参构造
 * @AllArgsConstructor 满参构造
 * @Data : setter,getter,构造方法
 * <p>
 * 使用jpa操作数据
 * 配置实体类和数据库表的映射关系：jpa注解
 * 1.实体类和表的映射关系
 * 2.字段和属性的映射关系
 * i。主键属性的映射
 * ii。普通属性的映射
 */
@Entity
@Table(name = "co_company")
public class Company implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;
    //ID
    @Id
    private String id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 企业登录账号ID
     */
    private String managerId;
    /**
     * 当前版本
     */
    private String version;
    /**
     * 续期时间
     */
    private Date renewalDate;
    /**
     * 到期时间
     */
    private Date expirationDate;
    /**
     * 公司地区
     */
    private String companyArea;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 营业执照-图片ID
     */
    private String businessLicenseId;
    /**
     * 法人代表
     */
    private String legalRepresentative;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 公司规模
     */
    private String companySize;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 审核状态
     */
    private String auditState;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 当前余额
     */
    private Double balance;
    /**
     * 创建时间
     */
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", managerId='" + managerId + '\'' +
                ", version='" + version + '\'' +
                ", renewalDate=" + renewalDate +
                ", expirationDate=" + expirationDate +
                ", companyArea='" + companyArea + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", businessLicenseId='" + businessLicenseId + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", companySize='" + companySize + '\'' +
                ", industry='" + industry + '\'' +
                ", remarks='" + remarks + '\'' +
                ", auditState='" + auditState + '\'' +
                ", state=" + state +
                ", balance=" + balance +
                ", createTime=" + createTime +
                '}';
    }

    public Company() {
    }

    public Company(String id, String name, String managerId, String version, Date renewalDate, Date expirationDate, String companyArea, String companyAddress, String businessLicenseId, String legalRepresentative, String companyPhone, String mailbox, String companySize, String industry, String remarks, String auditState, Integer state, Double balance, Date createTime) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.version = version;
        this.renewalDate = renewalDate;
        this.expirationDate = expirationDate;
        this.companyArea = companyArea;
        this.companyAddress = companyAddress;
        this.businessLicenseId = businessLicenseId;
        this.legalRepresentative = legalRepresentative;
        this.companyPhone = companyPhone;
        this.mailbox = mailbox;
        this.companySize = companySize;
        this.industry = industry;
        this.remarks = remarks;
        this.auditState = auditState;
        this.state = state;
        this.balance = balance;
        this.createTime = createTime;
    }
}
