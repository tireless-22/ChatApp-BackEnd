package com.knk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

// import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
  

class CommonFunctions {


    public static Jws<Claims> parseJwt(String jwtString) {
    String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), 
                                    SignatureAlgorithm.HS256.getJcaName());

    Jws<Claims> jwt = Jwts.parserBuilder()
            .setSigningKey(hmacKey)
            .build()
            .parseClaimsJws(jwtString);

    return jwt;
}


 
//Sample method to construct a JWT
private String createJWT(String id, String issuer, String subject, long ttlMillis) {

    //The JWT signature algorithm we will be using to sign the token
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    //We will sign our JWT with our ApiKey secret
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secretet key");
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //Let's set the JWT Claims
    JwtBuilder builder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer(issuer);
            // .signWith(signatureAlgorithm, signingKey);

    //if it has been specified, let's add the expiration
    if (ttlMillis >= 0) {
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
    }

    //Builds the JWT and serializes it to a compact, URL-safe string
    return builder.compact();
}







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
            st1.setString(1, temp_ph);

            ResultSet rs = st1.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;

    }
    



        public static String add_users_to_groups(String temp_usr, String temp_grp) {
        try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            String query1 = "insert into usergrp(user_id,group_name) values(?,?)";
            PreparedStatement st1 = con.prepareStatement(query1);
            // int n = ph_no;
            st1.setString(1, temp_usr);
            st1.setString(2, temp_grp);

            int count = st1.executeUpdate();

            if (count == 0) {
                return "Occured problem while registering the user";
            } else {
                return "User added to group";
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return "some thing";
    }
    


    public static boolean add_user_to_group(String number,String groupName) {
       try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */



                    // add him to the group
                String query1 = "insert into usergrp(user_id,group_name) values(?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                // int n = ph_no;
                st1.setString(1, number);
                st1.setString(2, groupName);

                int count = st1.executeUpdate();

                if (count == 0) {
                    // System.out.println("something went wrong");
                    //    res.getWriter().println("Something went wrong");
                   } else {
                    // System.out.println("user added to group");
                    //    res.getWriter().println("User added to group");
               
                }





            } catch (Exception e) {
                // res.getWriter().println(e);
                System.out.println(e);
									
            // System.out.println(e);
        }


        return false;

    }





     public static int stringCompare(String str1, String str2)
    {
  
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
  
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);
  
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
  
        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }
  
        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
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
            st1.setString(1, grpName);

            ResultSet rs = st1.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;

    }


     public static int blockOrNot(String temp_num,String number) {
        // we will check if he blocked current user 

        try {
            //********************************************* */
            // jdbc connections
            String url = "jdbc:mysql://localhost:3306/chatapp";
            String mySqlUsername = "sqluser";
            String mySqlPassword = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, mySqlUsername, mySqlPassword);

            //********************************************** */

            // user_main blocked user or not 

            String query = "SELECT * from blockseen where(user_main=? and user=?) ;";
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, number);
            st.setString(2, temp_num);
            ResultSet rs = st.executeQuery();
            // rs.next();

            if (rs.next()) {
                if (rs.getInt(3) == 1) {
                    return 1;
                }

            }

            String query1 = "SELECT * from blockseen where(user_main=? and user=?) ;";
            PreparedStatement st1 = con.prepareStatement(query1);

            st1.setString(1, temp_num);
            st1.setString(2, number);

            ResultSet rs1 = st.executeQuery();

            if (rs1.next()) {
                if (rs1.getInt(3) == 1) {
                    return 2;
                }

            }
            return 3;

        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
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
                // System.out.println(temp_ph + "   " + grp_name);
                
                 
                

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