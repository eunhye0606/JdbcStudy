/*===============
	   DBConn
	- try~catch
/*===============*/
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn 
{
	//주요 변수 선언
	private static Connection dbconn;
	
	
	// 메소드 정의 1
	public static Connection getConnection()
	{
		try
		{
			if (dbconn == null)
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pw = "tiger";
				               
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbconn = DriverManager.getConnection(url, user, pw);
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return dbconn;
	}
	
	// 메소드 정의 2
	public static Connection getConnection(String url, String user, String pw)
	{
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbconn = DriverManager.getConnection(url, user, pw);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return dbconn;
		
	}
	
	// 닫는 메소드 정의
	public void close()
	{
		if (dbconn != null)
		{
			try
			{
				if (!dbconn.isClosed())
				{
					dbconn.close();
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		dbconn = null;
	}

}
