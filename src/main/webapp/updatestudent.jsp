<%@ page import="com.studentmanagement.studentmodel.*,com.studentmanagement.studentservice.*,org.hibernate.Session" %>
<html>
<head>
</head>
<body>
<%
 long id=Long.parseLong(request.getParameter("id"));
 String maths=request.getParameter("maths");
 String english=request.getParameter("english");
 String hindi=request.getParameter("hindi");
 String science=request.getParameter("science");
 String sst=request.getParameter("sst");
 StudentCrud studentcurd=new StudentCrud();
 try(Session session=ConnectionProvider.getConnection().openSession())
 {
   StudentDetails studentDetails=session.get(StudentDetails.class,id);
   StudentMarks studentMarks=studentDetails.getStudentMarks();

                   studentMarks.setHindiMarks(hindi);
                   studentMarks.setEnglishMarks(english);
                   studentMarks.setScienceMarks(science);
                   studentMarks.setSocialStudyMarks(sst);
                   studentMarks.setMathsMarks(maths);
                   studentDetails.setStudentMarks(studentMarks);
   String status=studentcurd.updateStudentDetails(studentDetails);
   RequestDispatcher requestDispatcher=req.getRequestDispatcher("user");
                   requestDispatcher.include(req,resp);
 }
%>
</body>
</html>