package com.abhi.jpaaudit.service;

import com.abhi.jpaaudit.model.Employee;
import com.abhi.jpaaudit.model.InputRequest;
import com.abhi.jpaaudit.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public String saveEmployee(InputRequest<Employee> request){
        String currentUser = request.getLoggedInUser();
        request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
        Employee employee = request.getEmployee();
        employee.setCreatedBy(currentUser);
        employeeRepo.save(employee);
        return "Employee " + employee.getEmpname() + " saved successfully";
    }

    @Transactional
    public String updateEmployee(int id, long salary,InputRequest<Employee> request){
        String currentUser = request.getLoggedInUser();
        request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
        Employee employee = employeeRepo.findById(id).get();
        if(employee!=null){
            employee.setModifiedBy(currentUser);
            employee.setSalary(salary);
            employeeRepo.saveAndFlush(employee);
            return "Employee " + employee.getEmpname() + " updated successfully";
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<Employee> findAllUsers(){
        return employeeRepo.findAll();
    }

    public List<Employee> findAllUsersByJob(String jobField){
        return employeeRepo.findByJobField(jobField);
    }

    public List<Employee> findAllUsersByJobIgnoreCase(String jobField){
        return employeeRepo.findByJobFieldIgnoreCase(jobField);
    }

    public long getCounts(long salary){
        return employeeRepo.countBySalary(salary);
    }

    public List<Employee> findByMultiCondition(String empName, long salary){
        return employeeRepo.findByEmpnameAndSalary(empName, salary);
    }

    public Page<Employee> getPagination(int page, int size){
        return employeeRepo.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public void deleteByName(String empName) throws Exception{
        try{
            employeeRepo.deleteByEmpname(empName);
        }catch(Exception ex){
            throw new RuntimeException("Employee not found");
        }

    }

    public Employee findByEmpnameAndDateOfJoining(String empname, Integer empId){
        try{
            Employee employee = employeeRepo.findByEmpnameAndDateOfJoining(empname,empId);
            return employee;
        }catch (Exception ex){
            throw new RuntimeException("Employee not found");
        }
    }
}
