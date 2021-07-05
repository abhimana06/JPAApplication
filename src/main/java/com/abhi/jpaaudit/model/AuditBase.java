package com.abhi.jpaaudit.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class AuditBase {

    @Column(name = "CREATED_BY", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "CREATED_DATE", updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "MODIFIED_BY")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    private Date modifiedDate;
}
