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




public class sendMessage extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
        String number = req.getParameter("number");
        String temp_num = req.getParameter("guest");
        String data=req.getParameter("data");
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

            
            if (CommonFunctions.blockOrNot(temp_num,number) == 1) {

                // return "You blocked " + temp_num
                //         + " so you can not send message to that user. Unblock him to send the messages";
                // System.out.println("You blocked "+ temp_num);
                System.out.println("you blocked the contact, unblock him to message ");

            } else if (CommonFunctions.blockOrNot(number,temp_num) == 2) {
                // return temp_num + " had blocked you";
                // System.out.println();
                res.getWriter().println("the user had blocked you");

            } else {
                // not blocked condition
                String query1 = "insert into message(data,sender_id,receiver_id) values(?,?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, data);
                st1.setString(2, number);
                st1.setString(3, temp_num);

                int count = st1.executeUpdate();

                if (count == 0) {
                    // return "Occured problem while registering the user";
                } else {
                    // return "message sent successfully";
                    res.getWriter().println("message sent successfully");
                }

            }

            

        } catch (Exception e) {
            System.out.println(e);
        }
       

		// res.getWriter().println(number+password);
		}
}
