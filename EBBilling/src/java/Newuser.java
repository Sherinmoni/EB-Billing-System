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
@WebServlet("/Newuser")
public class Newuser extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
    String nid1,npsw1;
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
               nid1=req.getParameter("nid");
         npsw1=req.getParameter("npsw");
         Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="insert into newuser values('"+nid1+"','"+npsw1+"')";
            int S;
        S = stmt.executeUpdate(Sqlstr);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet empdb</title>");            
            out.println("</head>");
            out.println("<body bgcolor=pink>");
            out.println("<center><a href='newuserdetails.html'><button>click and enter your details</button></a></center>");
            out.println("</body>");
            out.println("</html>");
        }
    catch(Exception e)
    {
        out.println("error");
       }
      
    }
}