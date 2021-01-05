package com.cos.ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Spliterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax1")
public class Ajax1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ajax1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username : "+username);
		System.out.println("password : "+password);
		
		// 응답하면!!
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSON으로 받기
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		System.out.println("username : "+username);
//		System.out.println("password : "+password);
//		
//		String jsonData = "{\"username\":\"ssar\", \"password\":\"1234\"}";
//
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		out.print(jsonData);
//		out.flush();
		
		//TEXT로 받기
		BufferedReader br=request.getReader();
		String requestData=br.readLine();
		System.out.println(requestData); //username=ssar,password=1234
		String[] data=requestData.split(",");
		//data[0]= username=ssar
		//data[1]= password=1234
		String[] usernameData=data[0].split("=");
		String[] passwordData=data[1].split("=");
		String username=usernameData[1];
		String password=passwordData[1];
		
		UserDto userDto=new UserDto();
		userDto.setUsername(username);
		userDto.setPassword(password);
		String userData="username : "+username+" "+"password : "+password;
		
		PrintWriter out=response.getWriter();
		out.print(userData);
		out.flush();
	}
}
