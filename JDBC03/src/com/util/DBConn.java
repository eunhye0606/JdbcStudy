/*============================
	DBConn.java
	- try ~ catch
=============================*/

package com.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 변수선언
	private static Connection dbconn;
	///////
	
	// 메소드정의1
	public static Connection getConnection()
				  //////////
	{
		if (dbconn == null)
		///////////////////
		{
			//변수 선언
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
						  ////////////////
			String user = "scott";
			String pw = "tiger";
			
			//클래스찾아줘
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
								//////『:』아니고 『.』임
						             
			}
			catch (ClassNotFoundException e)
			{
				System.out.println(e.toString());
				e.printStackTrace();
			}
			
			try
			{
				dbconn = DriverManager.getConnection(url, user, pw);
				                      ///////////////
			}
			catch (SQLException e)
			{
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		
		return dbconn;
	}
	
	
	// 메소드정의2
	public static Connection getConnection(String url, String user, String pw)
	{
		if (dbconn == null)
		{
			//클래스 찾아줘
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				dbconn = DriverManager.getConnection(url, user, pw);
			}
			catch (SQLException e)
			{

				e.printStackTrace();
			}
		}
		return dbconn;
		
	}
	
	//연결해제 메소드정의
	public static void close()
	{
		if (dbconn != null)
		{
			try
			{
				if (!dbconn.isClosed())
				{
					dbconn.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		//dbconn null값으로 초기화.
		dbconn = null;
		//////////////
	}

}
