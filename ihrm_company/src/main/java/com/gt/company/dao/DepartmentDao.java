package com.gt.company.dao;

import com.gt.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentDao extends JpaRepository<Department, String>, JpaSpecificationExecutor<Department> {

}
