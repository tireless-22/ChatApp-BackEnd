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



public class login extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number=req.getParameter("number");
		String password = req.getParameter("password");




		try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

												//********************************************** */
												String query = "SELECT * from user where(ph_no=?) ;";
            PreparedStatement st = con.prepareStatement(query);
            // System.out.println("check1");

            st.setString(1, number);
            ResultSet rs = st.executeQuery();
            // rs.next();
            // System.out.println("check1");

            if (rs.next()) {
                // existed
                // so we will check if the password is matched or not
                // if  not matched, then we will return 0 and show the user " please check you credentials"
                // if matched we will return 1 and tell the user that "successfull" and we will also put the user id in global state
                // String ph_no = rs.getString(2);
                String lgPassword = rs.getString(3);
                // System.out.println(lgPassword);
                // System.out.println(password);
																if (Integer.parseInt(lgPassword) == Integer.parseInt(password)) {

																	
                    res.getWriter().println(number);
                } else {
                    res.getWriter().println(0);
                }
            }
            else {
                res.getWriter().println(0);
            }

            
            

											} catch (Exception e) {
												res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
