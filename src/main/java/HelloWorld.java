// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();

      out.println("<h1>Hello World</h1>");
      out.println("<h2>" + new HelloWorldHelper().getMessage() + "</h2>");
  }

  public void destroy()
  {
      // do nothing.
  }
}