package com.abhi.jpaaudit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class       Employee extends AuditBase implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "EMPID")
    private Integer empId;

    @Column(name = "EMPNAME")
    private String empname;

    @Column(name = "SALARY")
    private long salary;

    @Column(name="DOJ")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy")
    private Date doj;

    @Column(name = "JOB_FIELD")
    private String jobField;


}
