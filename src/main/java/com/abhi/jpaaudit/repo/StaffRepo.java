package com.abhi.jpaaudit.repo;

import com.abhi.jpaaudit.model.Staff;
import com.abhi.jpaaudit.model.StaffPKId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, StaffPKId> {


}
