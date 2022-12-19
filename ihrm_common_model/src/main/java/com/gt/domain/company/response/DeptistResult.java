package com.gt.domain.company.response;


import com.gt.domain.company.Company;
import com.gt.domain.company.Department;

import java.util.List;

public class DeptistResult {
    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> depts;

    public DeptistResult(Company company, List depts) {
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();//公司联系人
        this.depts = depts;
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

    public String getCompanyManage() {
        return companyManage;
    }

    public void setCompanyManage(String companyManage) {
        this.companyManage = companyManage;
    }

    public List<Department> getDepts() {
        return depts;
    }

    public void setDepts(List<Department> depts) {
        this.depts = depts;
    }

    @Override
    public String toString() {
        return "DeptistResult{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyManage='" + companyManage + '\'' +
                ", depts=" + depts +
                '}';
    }
}
