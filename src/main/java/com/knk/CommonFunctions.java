package com.knk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class CommonFunctions {
    public static boolean user_existed_or_not(String temp_ph) {
          try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            
            

                String query1 = "select * from  user where ph_no=?";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1,temp_ph);
                

                ResultSet rs = st1.executeQuery();

                if (rs.next()) {
                    return true;
                }
                else {
                    return false;
                }

            

        } catch (Exception e) {
            System.out.println(e);
        }

       
        return false;
         







        

    }

    public static boolean group_name_exist_or_not(String grpName) {

        try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            
            

                String query1 = "select * from  grps where grp_name=?";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1,grpName);
                

                ResultSet rs = st1.executeQuery();

                if (rs.next()) {
                    return true;
                }
                else {
                    return false;
                }

            

        } catch (Exception e) {
            System.out.println(e);
        }

       
        return false;
         




        
    }

    public static boolean user_belongs_to_group_or_not(String temp_ph, String grp_name) {

        try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            
            

                String query1 = "select * from  usergrp where user_id=? and group_name=?";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, temp_ph);
                st1.setString(2, grp_name);
                System.out.println(temp_ph + "   " + grp_name);
                
                 
                

                ResultSet rs = st1.executeQuery();

                // rs.next();

                if (rs.next()) {
                    return true;

                }
                else {
                    return false;
                }

              
            

        } catch (Exception e) {
            System.out.println(e);
        }



       

        return true;
         
    }
    
    public static boolean admin_or_not(String grpName) {
        try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            
            

                String query1 = "select * from  grps where grp_name=?";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1,grpName);
                

                ResultSet rs = st1.executeQuery();

                rs.next();
                String s1 = rs.getString(3);
                String s2 = current_user();
                // System.out.println(s1+s2);
        
                


                if (s1.endsWith(s2)) {
                    // System.out.println("checker");
                    return true;

                }
                else {
                    return false;
                }

               

            

        } catch (Exception e) {
            System.out.println(e);
        }

       
        return false;
         
      

        
    }


    public static boolean current_user_is_there_or_not() {
        // if (App.currentUser == 0) {
        //     return false;

        // }
        return true;

    }

    public static String  current_user(){
					// return String.valueOf(App.currentUser);
					// for now it is like this, after that we will fetch the data from the user from the cookies
					return "something";
    }
    
}