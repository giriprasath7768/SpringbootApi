package com.api.demo.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.api.demo.Entity.User;



public class Restclient {
	
	private static final  String Get_user_Endpoint_URL="http://localhost:8080/api/users";
	private static final String get_userByID_Endpoint_URL="http://localhost:8080/api/users/{id}";
	private static RestTemplate rt=new RestTemplate();
	private static String url="jdbc:mysql://localhost:3306/batch2";
	private static String username="root";
	private static String password="";
	
	public static void main(String[] args) {
		//getUsers();
		getUserByID();
		
	}
	
	
	private static void getUsers()
	{
		try {
			//step 1
			Connection con =DriverManager.getConnection(url,username,password);
			
			//step2
			HttpHeaders header=new HttpHeaders();
			header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String>entity=new HttpEntity<String>("parameters",header);
			
			//step3
			ResponseEntity<List<User>>result=rt.exchange(Get_user_Endpoint_URL, HttpMethod.GET, entity,new ParameterizedTypeReference<List<User>>(){
			});
			
			List<User>userdata=result.getBody();
			
			for (User usdata : userdata) {
				long userid=usdata.getId();
				String name=usdata.getFirstName();
				System.out.println(name);
				String email=usdata.getEmail();
				System.out.println(email);
				Statement stmt=con.createStatement();
				String query="insert into usertest value("+userid+",'"+name+"','"+email+"')";
				stmt.executeUpdate(query);
				
			}
			System.out.println("Data added successfully");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getUserByID()
	{
		Map<String,String>params=new HashMap<String,String>();
		params.put("id","3");
		 User usdata=rt.getForObject(get_userByID_Endpoint_URL, User.class, params);
		 
		 long userid=usdata.getId();
		    System.out.println(userid);
			String name=usdata.getFirstName();
			System.out.println(name);
			String email=usdata.getEmail();
			System.out.println(email);
		 
		 
	}
	
	
	
	

}
