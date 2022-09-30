package com.studentmanagement.studentmodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;
    private String studentName;
    private String studentAddress;
    private long studentPhoneNumber;
    private final String SCHOOL_NAME="sgrr public School";
    @OneToOne(cascade = CascadeType.ALL)
    private StudentMarks studentMarks;
}
