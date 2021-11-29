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
@WebServlet("/Bill")
public class Bill extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="select *from billdetails";
            rs= stmt.executeQuery(Sqlstr);
        if(rs.next())
        {
            out.println("<!DOCTYPE html>");
            out.println("<body bgcolor=lightgreen>");
            out.println("<h5 align=right><a href=index.html>Home</a></h5>");
            out.println("<form method=Post action=Bill>");
            out.println("<br><br><br><br>");
            out.println("<table align=center  cellpadding=10 cellspacing=10 bgcolor=white>");
            out.println("<tr><th colspan=2><h1 align=center>ELECTRICITY BILL</h1><hr>");
            out.println("<tr><th>Consumer Name<th>"+rs.getString("consumername")+"</tr>");
            out.println("<tr><th>Consumer No<th>"+rs.getString("consumerno"));
            out.println("</tr>");
            out.println("<tr><th>Which Connection?<th>"+rs.getString("connection")+"</tr>");
            out.println("<tr><th>Unit<th>"+rs.getInt("unit")+"</tr>");
            out.println("<tr><th>Amount<th>"+rs.getFloat("amount")+"</tr>");
            out.println("<tr><th>Date And Time<th>"+rs.getString("date")+"</tr>");
            out.println("<tr><th colspan=2><h3 align=center><hr>Payment Sucessfully<br>Thank You Come Again!!!!</h3>");
            out.println("<center><a href='index.html'><button>Print</button></a></center>");
            out.println("</form>");
            out.println("</body>");
        }
        else{
            out.println("<h1>CONSUMER IS NOT FOUND</h1>");
            out.println("<center><a href='newuser.html'><button>GO TO NEW USER</button></a></center>");
           
        }
        }
    catch(Exception e)
    {
        out.println("error");
       }
      
    }
}