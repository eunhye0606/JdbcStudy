/* ==========================
 	DBConn.java
 	- 예외 처리 throws
 ==========================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	//1. Connection 변수 선언
	private static Connection dbconn;
	
	//2. 메소드정의1
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//연결되어있는지 확인
		if (dbconn == null)
		{
			//변수 선언
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pw = "tiger";
			
			//클래스 찾아줘
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbconn = DriverManager.getConnection(url, user, pw);
		}
		return dbconn;
	}
	
	//2. 메소드 정의2
	public static Connection getConnection(String url,String user,String pw) throws ClassNotFoundException, SQLException
	{
		if (dbconn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbconn = DriverManager.getConnection(url, user, pw);
		}
		
		return dbconn;
	}
	
	//3. 연결해제 메소드 정의
	public static void close() throws SQLException
	{
		if (dbconn != null)
		{
			if (!dbconn.isClosed())
			{
				
				dbconn.close();				
			}
		}
		//dbconn 을 null 값으로 초기화.
		dbconn = null;
	}
}

