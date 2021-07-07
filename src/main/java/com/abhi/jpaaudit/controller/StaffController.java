package com.abhi.jpaaudit.controller;

import com.abhi.jpaaudit.model.InputRequest;
import com.abhi.jpaaudit.model.Staff;
import com.abhi.jpaaudit.model.StaffPKId;
import com.abhi.jpaaudit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public String staffWelcome(){
        return  "Welcome to Staff Controller";
    }

    @PostMapping("/addStaff")
    public String saveEmployee(@RequestBody InputRequest<Staff> request) throws Exception {
        return staffService.saveStaff(request);
    }

    @PutMapping("/udpateStaff/{phoneNo}")
    public String updateEmployee(@RequestBody StaffPKId staffPKId, @PathVariable String phoneNo){
        return staffService.updateStaff(staffPKId ,phoneNo);
    }
}
