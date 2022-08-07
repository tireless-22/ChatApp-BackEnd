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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class sendGroupMessage extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
      
		      String data = req.getParameter("data");
								String number = req.getParameter("number");
        String guest_group = req.getParameter("guest_group");
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

            // Getting the user if exists()

            
            
                // not blocked condition
                String query1 = "insert into grpsmsg(msg_data,sender_id,grp_name) values(?,?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, data);
                st1.setString(2, number);
                st1.setString(3, guest_group);

                int count = st1.executeUpdate();

																if (count == 0) {
																	   res.getWriter().println("something happend while happened while sending message");
                    // return "Occured problem while registering the user";
                } else {
                    // return "message sent successfully";
                    res.getWriter().println("message sent successfully in group");
                }

            

            

        } catch (Exception e) {
            System.out.println(e);
        }
       

		// res.getWriter().println(number+password);
		}
}
