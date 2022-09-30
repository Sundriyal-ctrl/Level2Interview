package com.studentmanagement.studentmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subjectId;
    private int mathsMarks;
    private int  englishMarks;
    private int scienceMarks;
    private int hindiMarks;
    private int socialStudyMarks;

}
