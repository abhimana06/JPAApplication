package com.abhi.jpaaudit.controller;

import com.abhi.jpaaudit.model.Employee;
import com.abhi.jpaaudit.model.InputRequest;
import com.abhi.jpaaudit.model.Staff;
import com.abhi.jpaaudit.model.StaffPKId;
import com.abhi.jpaaudit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/addStaff")
    public String saveEmployee(@RequestBody InputRequest<Staff> request) throws Exception {
        return staffService.saveStaff(request);
    }

    @PutMapping("/udpateStaff/{phoneNo}")
    public String updateEmployee(@RequestBody StaffPKId staffPKId, @PathVariable String phoneNo){
        return staffService.updateStaff(staffPKId ,phoneNo);
    }
}
