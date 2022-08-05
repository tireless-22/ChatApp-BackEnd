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



public class AddUser extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number=req.getParameter("number");
		String groupName = req.getParameter("groupName");




		try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            CommonFunctions cf=new CommonFunctions();


            if (CommonFunctions.user_existed_or_not(number)) {


                if (CommonFunctions.user_belongs_to_group_or_not(number, groupName)) {
                    res.getWriter().println("User already existed in the group");

                }
                else {
                    // add him to the group
                String query1 = "insert into usergrp(user_id,group_name) values(?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, number);
                st1.setString(2, groupName);

                int count = st1.executeUpdate();

                if (count == 0) {
                       res.getWriter().println("Something went wrong");
                   } else {
                       res.getWriter().println("User added to group");
               
                }





                }
													
            } else {
                res.getWriter().println("User not found");
               

            }

            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
