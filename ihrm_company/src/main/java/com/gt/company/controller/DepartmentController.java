package com.gt.company.controller;

import com.gt.common.controller.BaseController;
import com.gt.common.entity.Result;
import com.gt.common.entity.ResultCode;
import com.gt.company.service.CompanyService;
import com.gt.company.service.DepartmentService;
import com.gt.domain.company.Company;
import com.gt.domain.company.Department;
import com.gt.domain.company.response.DeptistResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    /**
     * 保存
     */
    @PostMapping(value = "/department")
    public Result save(@RequestBody Department department) {
        department.setCompanyId(companyId);
        departmentService.save(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询全部
     */
    @GetMapping(value = "/department")
    public Result findAll() {
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        DeptistResult deptistResult = new DeptistResult(company, list);
        return new Result(ResultCode.SUCCESS, deptistResult);
    }

    /**
     * 根据ID查询department
     */
    @GetMapping(value = "/department/{id}")
    public Result findById(@PathVariable(value = "id") String id) {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS, department);
    }


    /**
     * 修改Department
     */
    @PutMapping(value = "/department/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody Department department) {
        //1.设置修改的部门id
        department.setId(id);
        //2.调用service更新
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除
     */
    @DeleteMapping(value = "/department/{id}")
    public Result delete(@PathVariable(value = "id") String id) {
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
