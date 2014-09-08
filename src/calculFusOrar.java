import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet; 
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/fusorar") 

public class calculFusOrar extends HttpServlet{
    
  public int calculeazaDiferentaFus(long long1, String tiplong1, long long2, String tiplong2){
	  if (tiplong1.equals("EST")){
		  if (tiplong2.equals("EST")){
			  return( (int)(long2-long1)/15);
		  }
		  if (tiplong2.equals("VEST")){
			  return( (int)(-long2-long1)/15);
		  }		  
	  }
	else  if (tiplong1.equals("VEST")){
		  if (tiplong2.equals("VEST")){
			  return( (int)(long1-long2)/15);
		  }
		  if (tiplong2.equals("EST")){
			  return( (int)(long1+long2)/15);
		  }
	  }	  
return 0;
  }
public String calculeazaOra(int ora, int minutul, int diferenta){
	if (minutul>9) {return((ora+diferenta + 24)%24 + " : " + minutul);}
	else { return((ora+diferenta + 24)%24 + " : " + "0"+minutul);}
	}
  
  public void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
     String tip=req.getParameter("tip");     
    
    long long1 = Long.parseLong(req.getParameter("long1"));
    long long2 = Long.parseLong(req.getParameter("long2"));
	int ora = Integer.parseInt(req.getParameter("ora"));
    int minutul = Integer.parseInt(req.getParameter("minutul"));
    String tiplong1 = req.getParameter("tiplong1");
    String tiplong2 = req.getParameter("tiplong2");
     

    int rezultat = calculeazaDiferentaFus(long1,tiplong1,long2,tiplong2);
    PrintWriter out=res.getWriter();
    if(tip.equals("text/html")){
      res.setContentType("text/html");
      out.println("<HTML><HEAD><TITLE>");
      out.println("</TITLE></HEAD><BODY><CENTER>");
      out.println("<P>Diferenta de fus este:  "+rezultat);
	  out.println("<P> Ora in punctul 2: " + calculeazaOra(ora, minutul, ((int)rezultat)    ));
      out.println("</CENTER></BODY></HTML>");
    }
    else{
      res.setContentType("text/plain");
      out.println(rezultat);
    }
    out.close();   
  }
  
  public void doPost(HttpServletRequest req,HttpServletResponse res)
      throws ServletException,IOException{
    doGet(req,res);
  } 
}