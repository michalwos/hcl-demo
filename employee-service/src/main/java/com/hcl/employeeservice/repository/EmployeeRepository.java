package com.hcl.employeeservice.repository;

import com.hcl.employeeservice.domain.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Employee repository
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
