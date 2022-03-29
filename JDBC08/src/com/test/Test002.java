/*=====================================================
    Test002.java
    - CallableStatement 를 활용한 SQL 구문 전송 실습
=====================================================*/
package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					//쿼리문 준비 check~!!!
					//커서로 처리하기 때문에 매개변수를 넘겨준다.
					String sql = "{call PRC_MEMBERSELECT(?)}";
					
					//CallableStatement 작업 객체 생성 check ~!!!
					CallableStatement cstmt = conn.prepareCall(sql);
					
					//check~!!!
					//프로시저 내부에서 sys_refcursor 를 사용하고 있기 때문에
					//OracleTypes.CURSOR 를 사용하기 위한 등록 과정이 필요한 상황.
					//1. Project Explorer 상에서 해당 프로젝트에서 마우스 우클릭.
					//   > Build Path > Configure Build Path > Libraries 탭 선택
					//   > 우측 Add External JAR 버튼 클릭 → 『ojdbc6.jar』 파일 추가등록
					//										   (외부 jar 파일 연결)
					//2. 『import oracle.jdbc.OracleTypes;』 구문 추가 등록
					
					
					
					//※registerOutParameter() 메소드는 SQL 타입을 사용
					//  OracleTypes.CURSOR → SQL 타입
					//  OracleTypes.CURSOR 사용하면
					//  import oracle.jdbc.OracleTypes; 가 되어야 하는데
					//  이걸 하려면 ojdbc6.jar를 추가 등록해야한다.
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					//getObject인 이유 : 커서로 객체를 주고 받아서
					//타입은 Object이지만 resultset임..
					//ResultSet rs = cstmt.getObject(1);
					
					//얘는 오류임. 
					/*
					<ResultSet> : 하나의 띠로 넘어옴.
					▼
					-------------------------------------------------
					|1 남주혁 010-1111-1111 | 2 백이진 010-2222-2222| 
					-------------------------------------------------
					그래서 rs.next()
					*/
					
					/*
					<Object> : Object가 더 커서 다운캐스팅하면 된다.
					-----------------------------------------------------
					|  ------------------------------------------------- |
					|  |1 남주혁 010-1111-1111 | 2 백이진 010-2222-2222| | 
					|  ------------------------------------------------- |
					-----------------------------------------------------
					*/
					//다운캐스팅
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid,name,tel);
						System.out.println(str);
					}
					rs.close();
					cstmt.close();
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			DBConn.close();
			System.out.println("\n 데이터베이스 닫힘~!!!");
			System.out.println("프로그램 종료됨~!!!!");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
/*
데이터베이스 연결 성공~!!!
  1     이호석 010-1111-1111
  2     남주혁 010-2222-2222
  3     강태무 010-3333-3333
  4     백이진 010-4444-4444

 데이터베이스 닫힘~!!!
프로그램 종료됨~!!!!
*/
