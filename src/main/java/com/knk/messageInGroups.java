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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;




public class messageInGroups extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String grp_name = req.getParameter("guest_group");
		// String guest_group = req.getParameter("guest_group");
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
		    						String query = "SELECT * FROM chatapp.grpsmsg where(grp_name=?)";
            PreparedStatement st = con.prepareStatement(query);
            // System.out.println("check1");

												st.setString(1, grp_name);
												// st.setString(2, guest);
												// st.setString(3, guest);
												// st.setString(4, number);
												
												ResultSet rs = st.executeQuery();
												JsonArray jsonArray = new JsonArray();

												while (rs.next()) {
												JsonObject row=new JsonObject();

												row.addProperty("msg_id", rs.getInt(1));
												row.addProperty("msg_data", rs.getString(2));
												row.addProperty("sender_id", rs.getString(3));
												row.addProperty("grp_name", rs.getString(4));
												jsonArray.add(row);
												}
												
													
 
														res.getWriter().println(jsonArray);
												 
            } catch (Exception e) {
                res.getWriter().println(e);
									
            // System.out.println(e);
        }



		}
}
