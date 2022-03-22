/*================================================================
 	MemberDAO.java
 	-<데이터베이스에 엑세스하는 기능> → DBConn을 활용(전담 계층)
 	
 	-	데이터를 입력하는 기능 → insert
 	-	인원수를 조회하는 기능 → select
 		즉, 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능
 	-	전체데이터를 조회하는 기능 → select
 		즉, 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능
 =================================================================*/

package com.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

/*
import java.sql.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;
*/
public class MemberDAO
{
	//DAO → 그 회사를 전담하는 사람이 지정되는거.
	// Database에서 문제,새로뭐만들기 등 할 때 DAO만 건들면됨.
	
	//①주요 속성 선언 → DB 연결 객체
	private Connection conn;
	// Connection을 단독으로 보내거나 했으면 getter/setter(private)해.
	
	
	
	
	
	//②생성자 정의(사용자 정의 생성자)
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	
	//③ 메소드 정의 → (1). 데이터를 입력하는 기능 → insert
	//   insert → executeUpdate() → 적용된 행 수 반환.
	public int void add(MemberDTO dto)
	{
		//반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		//작업 객체 생성
		//(생성된 작업객체에게 넘겨줄) 쿼리문 준비
		//작업 객체를 활용하여 쿼리문 실행(전달):execute
		//사용한 리소스 반납
		//최종 결과값 반환(적용된 행의 갯수)
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// 내가한거
//-----------------------------------------------------------

/*
 * Connection conn;
 * 
 * // 생성자 public MemberDAO() throws ClassNotFoundException, SQLException { conn
 * = DBConn.getConnection();
 * 
 * if (conn != null) { try { System.out.println("데이터베이스 연결 성공");
 * 
 * } catch (Exception e) { System.out.println(e.toString()); } } else
 * System.out.println("데이터베이스 연결 실패"); }
 * 
 * //1. 데이터 입력하기 public void insert() { try { //Scanner 인스턴스 생성 Scanner sc = new
 * Scanner(System.in); //MemberDTO 인스턴스 생성 MemberDTO mdto = new MemberDTO();
 * 
 * while(true) {
 * 
 * System.out.print("이름 전화번호 입력 : "); mdto.setName(sc.next());
 * mdto.setTel(sc.next());
 * 
 * //작업객체 생성 Statement stmt = conn.createStatement();
 * 
 * //쿼리문 준비 String sql = String.
 * format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '%s','%s')"
 * ,mdto.getName(),mdto.getTel());
 * 
 * //쿼리 제대로 됐는지 확인 int temp = stmt.executeUpdate(sql);
 * 
 * if (temp > 0) { System.out.println("데이터 입력 성공"); //break; }
 * 
 * //반복문 나가기
 * 
 * 
 * if (mdto.getName().equals(".")) { break; }
 * 
 * } } catch (Exception e) { System.out.println("여기가문젭니다.");
 * System.out.println(e.toString()); }
 * 
 * 
 * }
 * 
 * //2. 전체 멤버 수 조회하기 public int count() throws SQLException { //작업객체 생성
 * Statement stmt = conn.createStatement(); int maxNum = 999;
 * 
 * //쿼리문 준비 String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
 * 
 * ResultSet rs = stmt.executeQuery(sql); //쿼리문 실행 while (rs.next()) { maxNum =
 * rs.getInt("COUNT"); //System.out.println(maxNum); } rs.close();
 * 
 * 
 * return maxNum; }
 * 
 * 
 * //3. 멤버 한명 한명 조회하기 public void select() throws SQLException { try { //작업객체 생성
 * Statement stmt = conn.createStatement();
 * 
 * //쿼리문 준비 String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
 * 
 * ResultSet rs = stmt.executeQuery(sql);
 * 
 * while (rs.next()) {
 * 
 * //MemberDTO 객체 생성 MemberDTO mDTO = new MemberDTO();
 * 
 * String name = rs.getString("NAME"); String tel = rs.getString("TEL"); String
 * sid = rs.getString("SID");
 * 
 * String result = String.format("%3s %5s %8s", sid,name, tel);
 * 
 * System.out.println(result); }
 * 
 * rs.close(); } catch (Exception e) { System.out.println(e.toString()); }
 * 
 * }
 */
//-----------------------------------------------------------
}
