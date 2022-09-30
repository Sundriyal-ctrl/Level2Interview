package com.studentmanagement;

import com.studentmanagement.studentmodel.StudentDetails;
import com.studentmanagement.studentmodel.StudentMarks;
import com.studentmanagement.studentservice.StudentCrud;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet("/user")
public class admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        StudentCrud studentCrud=new StudentCrud();
        List<StudentDetails> studentDetails=studentCrud.fetchAllStudent();

        out.println("<table border='2'><thead><tr><th>STUDENT_ID</th><th>STUDENT_NAME</th><th>STUDENT_ADDRESS</th>");
       out.println("<th>Student_Phone_Number</th><th>STUDENT_SCHOOL_NAME</th><th>MATHS_MARKS</th>");
       out.println("<th>ENGLISH_MARKS</th><th>SCIENCE_MARKS</th><th>HINDI_MARKS</th><th>SOCIAL_STUDY_MARKS</th><th>UPDATE</th><th>DELETE</th></tr></thead><tbody>");
       Iterator iterator=studentDetails.iterator();
       while(iterator.hasNext()) {
           StudentDetails studentDetails1=(StudentDetails)iterator.next();
           out.println("<tr><td>" + studentDetails1.getStudentId() + "</td><td>" + studentDetails1.getStudentName() + "</td>");
           out.println("<td>" + studentDetails1.getStudentAddress() + "</td><td>" + studentDetails1.getStudentPhoneNumber() + "</td>");
           out.println("<td>" + studentDetails1.getStudentMarks().getEnglishMarks() + "</td>");
           out.println("</td><td>" + studentDetails1.getStudentMarks().getMathsMarks() + "</td>");
           out.println("<td>" + studentDetails1.getStudentMarks().getScienceMarks() + "</td>");
           out.println("<td>" + studentDetails1.getStudentMarks().getHindiMarks() + "</td>");
       out.println("<td>"+studentDetails1.getStudentMarks().getSocialStudyMarks()+"</td>");
       out.println("<td><button><a href='record?id="+studentDetails1.getStudentId()+"&btntype=update'>UPDATE</a></button></td>");
           out.println("<td><button><a href='record?id="+studentDetails1.getStudentId()+"&btntype=delete'>DELETE</a></button></td>");

       }
       out.println("<thead></table>");
        out.println("<td><button><a href='insert.html'>INSERT STUDENT RECORD</a></button></td>");

    }
}
