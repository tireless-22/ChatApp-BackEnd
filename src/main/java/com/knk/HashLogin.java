package com.knk;
import java.io.Console;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.knk.CommonFunctions;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;






public class HashLogin extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String number=req.getParameter("number");
        String password = req.getParameter("password");
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), 
                            SignatureAlgorithm.HS256.getJcaName());





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
        

            if (rs.next()) {
             
                String lgPassword = rs.getString(3);
                String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(password);
                // System.out.println(lgPassword);
                // System.out.println(password);

                if (CommonFunctions.stringCompare(sha3Hex, lgPassword) == 0) {
                    String jwtToken = Jwts.builder()
                    .claim("number", number)
                    // .setId(UUID.randomUUID().toString())
                    .signWith(hmacKey)
                    .compact();
                    
                    

																	
                    res.getWriter().println(jwtToken);
                
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
