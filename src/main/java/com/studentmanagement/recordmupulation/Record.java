package com.studentmanagement.recordmupulation;

import com.studentmanagement.connectionprovider.ConnectionProvider;
import com.studentmanagement.studentmodel.StudentDetails;
import com.studentmanagement.studentmodel.StudentMarks;
import com.studentmanagement.studentservice.StudentCrud;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/record")
public class Record extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Opreationtype=req.getParameter("btntype");
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        StudentCrud studentCrud=new StudentCrud();
        if(Opreationtype.equals("insert"))
        {
            StudentDetails studentDetails=new StudentDetails();
            String name=req.getParameter("name");
            String address=req.getParameter("address");
            Long number=Long.parseLong(req.getParameter("number"));
            int maths=Integer.parseInt(req.getParameter("maths"));
            int hindi=Integer.parseInt(req.getParameter("hindi"));
            int science=Integer.parseInt(req.getParameter("science"));
            int english=Integer.parseInt(req.getParameter("english"));
            int sst=Integer.parseInt(req.getParameter("sst"));
            for(int i=0;i<name.length();i++)
            {
                if(Character.isDigit(name.charAt(i)))
                {
                    out.println("Name Should Not be Digit");
                    RequestDispatcher requestDispatcher=req.getRequestDispatcher("user");
                    requestDispatcher.include(req,resp);
                    break;
                }
            }
            if(maths<0 && english<0 && hindi<0 && science<0 && sst<0)
            {
                out.println("Marks should be positive");
                RequestDispatcher requestDispatcher=req.getRequestDispatcher("user");
                requestDispatcher.include(req,resp);
            }
            else {
                StudentDetails studentDetails1=new StudentDetails();
                studentDetails1.setStudentName(name);
                studentDetails1.setStudentAddress(address);
                studentDetails1.setStudentPhoneNumber(number);
                StudentMarks studentMarks=new StudentMarks();
                studentMarks.setHindiMarks(hindi);
                studentMarks.setEnglishMarks(english);
                studentMarks.setScienceMarks(science);
                studentMarks.setSocialStudyMarks(sst);
                studentMarks.setMathsMarks(maths);


                studentDetails1.setStudentMarks(studentMarks);
                String status=studentCrud.insertStudentRecord(studentDetails1);
                out.println(status);
                RequestDispatcher requestDispatcher=req.getRequestDispatcher("user");
                requestDispatcher.include(req,resp);
            }
        }
        else if(Opreationtype.equals("delete")) {
               String status=studentCrud.deleteStudentDetails(Long.parseLong(req.getParameter("id")));
               out.println(status);
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("user");
            requestDispatcher.include(req,resp);
        }
        else if(Opreationtype.equals("update"))
        {
            try(Session session= ConnectionProvider.getConnection().openSession()){
               StudentDetails studentDetails=session.get(StudentDetails.class,Long.parseLong(req.getParameter("id")));
               out.println("<form action='updatestudent.jsp'><input type='text' value='"+studentDetails.getStudentName()+"' readonly/>" +
                       "<input type='text' value='"+studentDetails.getStudentMarks().getEnglishMarks()+"' name='english'/>" +
                       "<input type='text' value='"+studentDetails.getStudentMarks().getMathsMarks()+"' name='maths'/>" +
                       "<input type='text' value='"+studentDetails.getStudentMarks().getScienceMarks()+"' name='science'/>" +
                       "<input type='text' value='"+studentDetails.getStudentMarks().getSocialStudyMarks()+"' name='sst'/>" +
                       "<input type='text' value='"+studentDetails.getStudentMarks().getHindiMarks()+"' name='hindi'/>" +
                       "<input type='hidden' value='"+req.getParameter("id")+"' name='id'/>"+
                       "<input type='submit' value='Update'/></form>");
            }
    }
}}
