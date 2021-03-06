/*======================
     Test003.java
	- 데이터베이스 연결 실습
	- 데이터 입력 실습
========================*/

package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test003
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		// 연결 객체 생성(구성) - 빨간줄
		Connection conn = DBConn.getConnection();
		
		if (conn == null)
		{
			System.out.println("데이터베이스 연결 실패~!!!");
			System.exit(0);
		}
		//System.out.println("데이터베이스 연결 성공~!!!");
		
		try
		{
			// 작업 객체 구성(준비)
			Statement stmt = conn.createStatement();
			//                     빨간줄 매달다.
			//빨간줄에 파란색작업 객체를 매달았음.
			
			//※ 데이터 입력 쿼리 실행 과정
			//   한 번 실행하면 다시 실행하지 못하는 상황이다.
			//   기본키 제약조건이 설정되어 있으므로
			//   동일한 키 값이 중복될 수 없기 때문이다.
			
			//한번만 가능 insert 구문에 pk때문에
			// 쿼리문 구성(준비) 작업객체안에 쪽지
			String sql ="INSERT INTO TBL_MEMBER(SID,NAME,TEL) VALUES(2, '홍은혜','010-4139-4969')";
			//-- 주의. 쿼리문 끝에 『;』 붙이지 않는다.
			//-- 주의. 자바에서 실행한 DML 구문은 내부적으로 자동 COMMIT 된다.
			//         (오라클에서 DML구문은 수동 COMMIT)
			//-- 주의. 오라클에서 트랜잭션 처리가 끝나지 않으면
			//         데이터 액션 처리가 이루어지지 않는다.
			
			//stmt.executeQuery()
			//오라클 내부적으로 뭔가 바뀌지 않는다.(select)
			//stmt.executeUpdate()
			//오라클 내부적으로 뭔가 바뀐다. (insert, update, delete)
			
			
			int result = stmt.executeUpdate(sql);
			// -- 적용된 행의 갯수 반환
			//    update 쿼리 날렸는데 where 조건 없으면
			//    두개의 행 바뀌니까 2반환.
			
			if (result > 0)
			{
				System.out.println("데이터 입력 성공");
			}
			else
			{
				System.out.println("입력 실패 ㅜㅜ");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		DBConn.close();
		// -- 리소스 반납(연결 종료)
	}
}
//실행 결과
/*
 데이터 입력 성공
 */