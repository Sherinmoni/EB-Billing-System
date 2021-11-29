import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Date;
import java.text.SimpleDateFormat;
@WebServlet("/Payment")
public class Payment extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
     int Rcno1,pr1,cr1,uc;
    String conn1,Rcna1;
    float sum;
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
                 Rcna1=req.getParameter("Rcna");
               Rcno1=Integer.parseInt(req.getParameter("Rcno"));
                conn1=req.getParameter("conn");
                 pr1=Integer.parseInt(req.getParameter("pr"));
                 cr1=Integer.parseInt(req.getParameter("cr"));
                 uc=pr1-cr1;
                 if(conn1.equals("domestic"))
                 {
                     if(uc<=100)
                         sum=uc*1;
                     else if(uc>=100&&uc<=200)
                         sum=100+(uc-100)*2;
                     else if(uc>=201&&uc<=500)
                         sum=100+(uc-100)*3;
                     else
                         sum=100+(uc-100)*4;
                 }
                 else
                 {
                 if(uc<=100)
                     sum=uc*2;
                     else if(uc>=100&&uc<=200)
                         sum=100+(uc-100)*3;
                     else if(uc>=201&&uc<=500)
                         sum=100+(uc-100)*4;
                     else
                         sum=100+(uc-100)*5;
               }
                 SimpleDateFormat formatter= new SimpleDateFormat("dd/mm/yyyy hh:ss:mm");
                 Date date=new Date();
                 String a=formatter.format(date);
         Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="insert into billdetails values('"+Rcna1+"','"+Rcno1+"','"+conn1+"','"+pr1+"','"+cr1+"','"+uc+"','"+sum+"','"+a+"')";
            int S;
        S = stmt.executeUpdate(Sqlstr);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Bill show</title>");            
            out.println("</head>");
            out.println("<body bgcolor=pink>");
            out.println("<center><a href='billshow.html'><button>CLICK AND PAY YOUR BILL</button></a></center>");
            out.println("</body>");
            out.println("</html>");
        }
    catch(Exception e)
    {
        out.println("error");
       }
      
    }
}