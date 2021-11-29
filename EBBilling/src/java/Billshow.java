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
@WebServlet("/Billshow")
public class Billshow extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
    String cno1;
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
               cno1=req.getParameter("bcno");
             Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="select *from billdetails where consumerno='"+cno1+"' ";
            rs= stmt.executeQuery(Sqlstr);
        if(rs.next())
        {
            out.println("<!DOCTYPE html>");
            out.println("<body bgcolor=lightgreen>");
            out.println("<form method=Post action=Bill>");
            out.println("<h1 align=center>BILL DETAILS</h1>");
            out.println("<br><br><br><br>");
            out.println("<table align=center border=1 cellpadding=10 cellspacing=10 bgcolor=skyblue>");
            out.println("<tr><th>Consumer Name<th>"+rs.getString("consumername")+"</tr>");
           out.println("<tr><th>Consumer No<th>"+rs.getString("consumerno"));
                out.println("</tr>");
            out.println("<tr><th>Which Connection?<th>"+rs.getString("connection")+"</tr>");
            out.println("<tr><th>Unit<th>"+rs.getInt("unit")+"</tr>");
            out.println("<tr><th>Amount<th>"+rs.getFloat("amount")+"</tr>");
            out.println("<tr><th>Date And Time<th>"+rs.getString("date")+"</tr>");
            out.println("<center><tr bgcolor=yellow><th><input type=submit value=PAY><th><a href='index.html'>GO TO HOME</a></center>");
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