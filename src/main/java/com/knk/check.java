package com.knk;

// package check1;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class check extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {

		res.getWriter().println("Hello this is from check");
		}
}