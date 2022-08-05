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



public class createAGroup extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String admin=req.getParameter("number");
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


            if (CommonFunctions.group_name_exist_or_not(groupName)) {
													// if the user exist
				res.getWriter().println("Group name already exists");
                

            } else {
                // if the user did not exist 
                // executeUpdate()

                String query1 = "insert into grps(grp_name,grp_auth) values(?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
        
                st1.setString(1, groupName);
                st1.setString(2, admin);

                int count = st1.executeUpdate();
   
 
                if (count == 0) {

                    res.getWriter().println("some problem");
                    return ;
                } else {
                   res.getWriter().println("Group created");
                }

            }

        } catch (Exception e) {
            res.getWriter().println(e);

            // System.out.println(e);
        }
        CommonFunctions.add_users_to_groups(admin, groupName);


		// res.getWriter().println(number+password);
		}
}
