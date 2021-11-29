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
@WebServlet("/Login")
public class Login extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
    String userid1,psw1;
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
               userid1=req.getParameter("userid");
         psw1=req.getParameter("psw");
         Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="select *from Newuser where Userid='"+userid1+"' and Password='"+psw1+"'";
            rs= stmt.executeQuery(Sqlstr);
        if(rs.next())
        {
          out.println("<body bgcolor=pink>");
            out.println("<h1>login sucessfully</h1>");
            out.println("<center><a href='consumerche.html'><button>WELCOME AND CLICK</button></a></center>");
        }
        else{
            out.println("<center><a href='newuser.html'><button>click and enter your details</button></a></center>");
            out.println("<h1>go to new user</h1>");
        }
        }
    catch(Exception e)
    {
        out.println("error");
       }
      
    }
}