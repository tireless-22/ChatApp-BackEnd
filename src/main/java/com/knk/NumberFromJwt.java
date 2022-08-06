package com.knk;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.knk.CommonFunctions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.servlet.http.HttpSession;





public class NumberFromJwt extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		

		// HttpSession ses = req.getSession(true);


		// String jwt = (String) req.getSession(false).getAttribute("number");


		
		// System.out.println(jwt);
				String jwt = req.getHeader("number");
		// System.out.println(jwt);
		System.out.println(CommonFunctions.parseJwt(jwt));
		Jws<Claims> data = CommonFunctions.parseJwt(jwt);
		// System.out.println(data.getClass());
		// const jsonData=data.getBody();
		System.out.println(data.getBody());

		





		
// ses.setAttribute("Name","Value");
		





		res.getWriter().println(data.getBody());
		}
}

