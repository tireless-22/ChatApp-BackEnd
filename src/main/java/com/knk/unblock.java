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




public class unblock extends HttpServlet{
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
																		// a column  existed
																		// we have to update existing row
																		String user_main = rs.getString(1);
																		String user = rs.getString(2);
																		// System.out.println(rs.getString(1));

																		String query1 = "update blockseen set block=0 where user_main=? AND user=?";
																		PreparedStatement st1 = con.prepareStatement(query1);
																		// int n = ph_no;
																		st1.setString(1, user_main);
																		st1.setString(2, user);

																		int count = st1.executeUpdate();

																		if (count == 0) {
																			res.getWriter().println("Occured problem while unblocking the user");


																						// return "Occured problem while blocking the user";
																					} else {
																						res.getWriter().println("User unblocked");
																			
																						// return "User blocked";
																		}

														} else {
																		// did not existed
																		// we have to insert new row

																		String query1 = "insert into blockseen(user_main,user,block) values(?,?,?)";
																		PreparedStatement st1 = con.prepareStatement(query1);
																		// int n = ph_no;
																		st1.setString(1, number);
																		st1.setString(2, guest);
																		st1.setInt(3,0);

																		int count = st1.executeUpdate();

																	if (count == 0) {
																			res.getWriter().println("Occured problem while unblocking the user");


																						// return "Occured problem while blocking the user";
																					} else {
																						res.getWriter().println("User unblocked");
																			
																						// return "User blocked";
																		}

														}

            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }


		// res.getWriter().println(number+password);
		}
}
