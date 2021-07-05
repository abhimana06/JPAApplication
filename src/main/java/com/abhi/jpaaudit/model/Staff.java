package com.abhi.jpaaudit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STAFF")
//@IdClass(StaffPKId.class) // type 1 of Composite PrimaryKey
public class Staff {
    /*type 1 of Composite PrimaryKey
    @Id
    private int staffId;
    @Id
    private String email;
     */

    //type 2 of Composite PrimaryKey
    @EmbeddedId
    private StaffPKId staffPKId;

    private int deptId;
    private String name;
    private String phoneNo;
}
