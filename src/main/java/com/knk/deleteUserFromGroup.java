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



public class deleteUserFromGroup extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String number = req.getParameter("guest");
		String group_name=req.getParameter("groupName");
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

									
														// if the user did not exist 
														// executeUpdate()

														String query1 = "delete from usergrp where(user_id=? and group_name=?)";
														PreparedStatement st1 = con.prepareStatement(query1);
														// int n = ph_no;
														st1.setString(1,number);
														st1.setString(2, group_name);

														// st1.setString(2, password);

														int count = st1.executeUpdate();
														// ArrayList<String> res=new ArrayList<String>();
														

														// Gson gson = new Gson();
										
														

														if (count == 0) {

																		res.getWriter().println("something wrong happened");
																		return ;
														} else {
																	res.getWriter().println("user deleted from Group");
														}

            

            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
