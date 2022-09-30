package com.studentmanagement.studentservice;

import com.studentmanagement.studentmodel.StudentDetails;
import org.hibernate.Session;
import com.studentmanagement.connectionprovider.ConnectionProvider;

import java.util.List;

public class StudentCrud {
    //Add student
    public String insertStudentRecord(StudentDetails studentDetails)
    {
        try(Session session=ConnectionProvider.getConnection().openSession())
        {
            long status=(long)session.save(studentDetails);
            if(status>0)
            {
                session.beginTransaction().commit();
                return "Student Inserted";
            }
            else {
                session.beginTransaction().rollback();
                return "Student Not Inserted";
            }
        }
    }

    //Update Student

    public String updateStudentDetails(StudentDetails studentDetails)
    {
        try(Session session=ConnectionProvider.getConnection().openSession()) {
             session.update(studentDetails);
             session.beginTransaction().commit();
             return "Student Updated";
        }
    }

    //Delete Student

    public String deleteStudentDetails(Long id)
    {
        try(Session session=ConnectionProvider.getConnection().openSession()) {
          StudentDetails studentDetails=session.get(StudentDetails.class,id);
          session.delete(studentDetails);
          session.beginTransaction().commit();
          return "Student id "+id+"  is deleted";
        }
    }

    //fetch All Student Details

    public List<StudentDetails> fetchAllStudent()
    {
        try(Session session=ConnectionProvider.getConnection().openSession()) {
            List<StudentDetails> studentDetails=session.createQuery("From StudentDetails").list();
           return studentDetails;
        }
    }



}
