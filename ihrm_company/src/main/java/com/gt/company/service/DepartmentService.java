package com.gt.company.service;

import com.gt.common.service.BaseService;
import com.gt.common.utils.IdWorker;
import com.gt.company.dao.DepartmentDao;
import com.gt.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService extends BaseService {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存部门
     *
     * @param department
     */
    public void save(Department department) {
        String id = idWorker.nextId() + "";
        department.setId(id);
        departmentDao.save(department);
    }

    /**
     * 更新部门
     */
    public void update(Department department) {
        Department dept = departmentDao.findById(department.getId()).get();
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        departmentDao.save(dept);
    }

    /**
     * 删除部门
     */
    public void deleteById(String id) {
        departmentDao.deleteById(id);
    }

    /**
     * 根据Id查询
     */
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }

    /**
     * 查询全部
     */

    public List<Department> findAll(String companyId) {
        return departmentDao.findAll(getSpec(companyId));
    }

}
