package com.abhi.jpaaudit.service;

import com.abhi.jpaaudit.model.InputRequest;
import com.abhi.jpaaudit.model.Staff;
import com.abhi.jpaaudit.model.StaffPKId;
import com.abhi.jpaaudit.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public String saveStaff(InputRequest<Staff> request) throws Exception{
        Staff staff = request.getEmployee();
        if(staff!=null){
            staffRepo.save(staff);
            return "staff saved successfully";
        }else{
            throw new RuntimeException();
        }
    }

    public String updateStaff(StaffPKId staffPKId, String phoneNo) {
        Staff staff = staffRepo.findById(staffPKId).orElseThrow(() -> new RuntimeException());
        staff.setPhoneNo(phoneNo);
        staffRepo.save(staff);
        return "update successFull";
    }
}
