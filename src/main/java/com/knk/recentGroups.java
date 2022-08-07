package com.knk;
import java.io.Console;
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

 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class recentGroups extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number=req.getParameter("number");
		// String password = req.getParameter("password");




		try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

												//********************************************** */
		    						String query = "SELECT * from usergrp where (user_id=?);";
            PreparedStatement st = con.prepareStatement(query);
            // System.out.println("check1");

												st.setString(1, number);
												// st.setString(2, number);
												ResultSet rs = st.executeQuery();
												

												ArrayList<String> recentMsgs = new ArrayList<String>();
												
     
												while (rs.next()) {
													recentMsgs.add(rs.getString(2));

													// System.out.println(rs.getString(1));
												}
												
													GsonBuilder gsonBuilder = new GsonBuilder();
		
													Gson gson = gsonBuilder.create();
 
													String JSONObject = gson.toJson(recentMsgs);
													res.getWriter().println(JSONObject);
												
            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
