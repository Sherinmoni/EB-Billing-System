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
@WebServlet("/Newuserdetails")
public class Newuserdetails extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
    String cna1,dob1,gen1,ad1,sta1,con1;
    int cno1,pinco1,mno1;
    Connection Con;
        ResultSet rs;
        Statement stmt;
        String Sqlstr;
        res.setContentType("text/html");
       try (PrintWriter out = res.getWriter()) {
               cna1=req.getParameter("cna");
         cno1=Integer.parseInt(req.getParameter("cno"));
          dob1=req.getParameter("dob");
           gen1=req.getParameter("gen");
           ad1=req.getParameter("ad");
            pinco1=Integer.parseInt(req.getParameter("pinco"));
            sta1=req.getParameter("sta");
            con1=req.getParameter("con");
             mno1=Integer.parseInt(req.getParameter("mno"));
         Class.forName("com.mysql.jdbc.Driver");
           Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBBilling","root","sa123");
           stmt=Con.createStatement();
           Sqlstr="insert into userdetails values('"+cna1+"','"+cno1+"','"+dob1+"','"+gen1+"','"+ad1+"','"+pinco1+"','"+sta1+"','"+con1+"','"+mno1+"')";
            int S;
        S = stmt.executeUpdate(Sqlstr);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet empdb</title>");            
            out.println("</head>");
            out.println("<body bgcolor=pink>");
            out.println("<center><a href='login.html'><button>click and enter your details</button></a></center>");
            out.println("</body>");
            out.println("</html>");
        }
    catch(Exception e)
    {
        out.println("error");
       }
      
    }
}