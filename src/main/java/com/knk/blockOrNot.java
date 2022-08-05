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




public class blockOrNot extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number=req.getParameter("number");
		String guest = req.getParameter("guest");




		try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

												//********************************************** */
													String query = "SELECT * from blockseen where(user_main=? AND user=?) ;";
														PreparedStatement st = con.prepareStatement(query);
													

														// String compKey = currUser + temp_ph;
														st.setString(1, number);
														st.setString(2, guest);
														ResultSet  rs = st.executeQuery();

														//******************************************* */
														if (rs.next()) {
															res.getWriter().println(rs.getString(3));

																	

																		

														} else {
																		// did not existed
																		// we have to insert new row

																		res.getWriter().println(0);

														}

            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
