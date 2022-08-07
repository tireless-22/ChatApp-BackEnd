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



public class startConversation extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number = req.getParameter("number");
		String guest = req.getParameter("guest");
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

            CommonFunctions cf=new CommonFunctions();


            if (!CommonFunctions.user_existed_or_not(guest)) {
													// if the user exist
															res.getWriter().println(0);
                

            } else {
                

                String query1 = "insert into message(data,sender_id,receiver_id) values(?,?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, "Hello there 👋");
                st1.setString(2, number);
                st1.setString(3, guest);

                int count = st1.executeUpdate();

																if (count == 0) {
																	res.getWriter().println(0);
																	
                    // return "Occured problem while registering the user";
                } else {
                    // return "message sent successfully";
                    res.getWriter().println(1);
                }

            }

            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
