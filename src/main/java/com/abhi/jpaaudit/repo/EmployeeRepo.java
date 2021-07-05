package com.abhi.jpaaudit.repo;

import com.abhi.jpaaudit.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {


    List<Employee> findByJobField(String jobField);

    List<Employee> findByJobFieldIgnoreCase(String jobField);

    long countBySalary(long salary);

    List<Employee> findByEmpnameAndSalary(String empName, long salary);

    @Transactional
    @Modifying
    void deleteByEmpname(String empName);

    @Query("select e from Employee e where e.empname = :empname and e.empId = :empId")
    Employee findByEmpnameAndDateOfJoining(@Param("empname") String empname,@Param("empId") Integer empId);

}
