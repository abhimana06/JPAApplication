package com.abhi.jpaaudit.controller;

import com.abhi.jpaaudit.model.Employee;
import com.abhi.jpaaudit.model.InputRequest;
import com.abhi.jpaaudit.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService  employeeService;

    @PostMapping("/addEmployee")
    public String saveEmployee(@RequestBody InputRequest<Employee> request){
        return employeeService.saveEmployee(request);
    }

    @PutMapping("/udpateEmployee/{id}/{salary}")
    public String updateEmployee(@PathVariable int id,@PathVariable long salary,@RequestBody InputRequest<Employee> request){
        return employeeService.updateEmployee(id, salary, request);
    }

    @GetMapping("/getPagination/{page}/{size}")
    public Page<Employee> getpagination(@PathVariable int page,@PathVariable int size){
        return   employeeService.getPagination(page,size);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return  employeeService.findAllUsers();
    }

    @GetMapping("/getAllEmployeesByJob/{job}")
    public List<Employee> findAllUsersByJob(@PathVariable String job){
        return  employeeService.findAllUsersByJob(job);
    }

    @GetMapping("/getAllEmployeesByIgnoreCase/{job}")
    public List<Employee> findAllUsersByJobIgnoreCase(@PathVariable String job){
        return  employeeService.findAllUsersByJobIgnoreCase(job);
    }

    @GetMapping("/getAllEmployeesByNameSalary/{empname}/{salary}")
    public List<Employee> findAllUsersByNameSalary(@PathVariable String empname,@PathVariable long salary){
        return  employeeService.findByMultiCondition(empname, salary);
    }

    @GetMapping("/getCounts/{salary}")
    public long getCounts(@PathVariable long salary){
        return  employeeService.getCounts(salary);
    }

    @GetMapping("/getByCustomQuery/{empname}")
    public Employee findByEmpnameAndDateOfJoining(@PathVariable String empname,@RequestParam Integer empId ) throws Exception{
        return employeeService.findByEmpnameAndDateOfJoining(empname, empId);
    }

    @DeleteMapping("/deleteByEmpname/{empname}")
    public void deleteByName(@PathVariable String empname) throws Exception {
        employeeService.deleteByName(empname);
    }


}
