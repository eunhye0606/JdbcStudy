/*==============================
	MemberDAO.java
=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : 1
==================================*/
package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	//주요 변수 선언
	Connection conn;
	
	//0. 데이터베이스 연결기능
	public void connection()
	{
		conn = DBConn.getConnection();
	}//end connection()
	
	
	//1. 직원 정보 입력(Process에서 do ~ while 에서 부서번호 범위 ㄴㄴ 인거는 에러 발생하도록)
	public int insert(MemberDTO dto) throws SQLException
	{
		//(1).주요 변수 선언
		int result = 0;
		
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3).쿼리문 준비
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
				+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s', %d, '%s', %d, %d, %d, %d)"
									, dto.getName(),dto.getSsn(),dto.getIbsadate(),dto.getCity(),dto.getTel()
									, dto.getBuseo(),dto.getJikwi(),dto.getBasicpay(),dto.getSudang());
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		//(5).리소스 반납
		stmt.close();
		
		//(6).값 반환
		return result;
	}//end insert()
	
	
	
	
	//2. 직원 전체 출력
	public ArrayList<MemberDTO> selectAll(int num) throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
		String strNum = "";
		
		//(1)-(1). 매개변수 : 1 → "사번" , ... 5→ 급여 내림차순
		switch (num)
		{
		case 1: strNum = "EMP_ID";
			break;
		case 2: strNum = "EMP_NAME";
			break;
		case 3: strNum = "BUSEO_ID";
			break;
		case 4: strNum = "JIKWI_ID";
			break;
		case 5: strNum = "PAY DESC";
			break;
		}
		
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3).쿼리문 준비
		String sql = String.format("SELECT E.EMP_ID, E.EMP_NAME, E.SSN, E.IBSADATE"
				+ ", C.CITY_NAME,E.TEL,B.BUSEO_NAME ,J.JIKWI_NAME"
				+ ", E.BASICPAY, E.SUDANG, (E.BASICPAY+E.SUDANG) AS PAY"
				+ " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID JOIN TBL_JIKWI J"
				+ " ON E.JIKWI_ID = J.JIKWI_ID JOIN TBL_CITY C"
				+ " ON E.CITY_ID = C.CITY_ID"
				+ " ORDER BY '%s'" 
				,strNum);
		
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getString("E.EMP_ID"));
			dto.setName((rs.getString("E.EMP_NAME")));
			dto.setSsn((rs.getString("E.SSN")));
			dto.setIbsadate((rs.getString("E.IBSADATE")));
			dto.setCity((rs.getString("C.CITY_NAME")));
			dto.setTel((rs.getString("E.TEL")));
			dto.setBuseo((rs.getString("B.BUSEO_NAME")));
			dto.setJikwi((rs.getString("J.JIKWI_NAME")));
			dto.setBasicpay(rs.getString("E.BASICPAY"));
			dto.setSudang(rs.getString("E.SUDANG"));
			dto.setPay(rs.getString("E.PAY"));
			
			
			array.add(dto);
		}
		
		//(5).리소스 반납
		rs.close();
		stmt.close();
		
		//(6).값 반환	
		return array;
	}//end selectAll()

	
	//3. 직원 검색 출력(사번)
	public ArrayList<MemberDTO> selectChoice(int id) throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
		
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3).쿼리문 준비
		String sql = String.format("SELECT E.EMP_ID, E.EMP_NAME, E.SSN, E.IBSADATE"
				+ ", C.CITY_NAME,E.TEL,B.BUSEO_NAME ,J.JIKWI_NAME, E.BASICPAY, E.SUDANG"
				+ ", (E.BASICPAY+E.SUDANG) AS PAY"
				+ " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID JOIN TBL_JIKWI J"
				+ " ON E.JIKWI_ID = J.JIKWI_ID JOIN TBL_CITY C"
				+ " ON E.CITY_ID = C.CITY_ID"
				+ " WHERE EMP_ID = %d"
				, id);
		
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getString("E.EMP_ID"));
			dto.setName((rs.getString("E.EMP_NAME")));
			dto.setSsn((rs.getString("E.SSN")));
			dto.setIbsadate((rs.getString("E.IBSADATE")));
			dto.setCity((rs.getString("C.CITY_NAME")));
			dto.setTel((rs.getString("E.TEL")));
			dto.setBuseo((rs.getString("B.BUSEO_NAME")));
			dto.setJikwi((rs.getString("J.JIKWI_NAME")));
			dto.setBasicpay(rs.getString("E.BASICPAY"));
			dto.setSudang(rs.getString("E.SUDANG"));
			dto.setPay(rs.getString("E.PAY"));
			
			
			array.add(dto);
		}
		
		//(5).리소스 반납
		rs.close();
		stmt.close();
		
		//(6).값 반환	
		return array;
	}//end selectChoice()
	
	
	//3. 직원 검색 출력(사번,직위,부서)   직위랑 부서는 str으로 입력받고 숫자로 해당번호로 변환하는 과정 process에서
		public ArrayList<MemberDTO> selectChoice(int jogan, String name) throws SQLException
		{
			//(1).주요 변수 선언
			ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
			//(1) - (1).
			String strNum = ""; //→ 얘를 쿼리문에 넣을 거임.
			
			switch (jogan)
			{
			case 1: strNum = "EMP_ID";
				break;
			case 2: strNum = "JIKWI_ID";
				break;
			case 3: strNum = "BUSEO_ID";
				break;
			}
			
			//(2).작업 객체 생성
			Statement stmt = conn.createStatement();
			
			//(3).쿼리문 준비
			String sql = String.format("SELECT E.EMP_ID, E.EMP_NAME, E.SSN, E.IBSADATE"
					+ ", C.CITY_NAME,E.TEL,B.BUSEO_NAME ,J.JIKWI_NAME, E.BASICPAY"
					+ ", E.SUDANG, (E.BASICPAY+E.SUDANG) AS PAY"
					+ " FROM TBL_EMP E JOIN TBL_BUSEO B"
					+ " ON E.BUSEO_ID = B.BUSEO_ID JOIN TBL_JIKWI J"
					+ " ON E.JIKWI_ID = J.JIKWI_ID JOIN TBL_CITY C"
					+ " ON E.CITY_ID = C.CITY_ID"
					+ " WHERE %s = '%s'"
					,strNum,name);
			
			//(4).쿼리문 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("E.EMP_ID"));
				dto.setName((rs.getString("E.EMP_NAME")));
				dto.setSsn((rs.getString("E.SSN")));
				dto.setIbsadate((rs.getString("E.IBSADATE")));
				dto.setCity((rs.getString("C.CITY_NAME")));
				dto.setTel((rs.getString("E.TEL")));
				dto.setBuseo((rs.getString("B.BUSEO_NAME")));
				dto.setJikwi((rs.getString("J.JIKWI_NAME")));
				dto.setBasicpay(rs.getString("E.BASICPAY"));
				dto.setSudang(rs.getString("E.SUDANG"));
				dto.setPay(rs.getString("E.PAY"));
				
				
				array.add(dto);
			}
			
			//(5).리소스 반납
			rs.close();
			stmt.close();
			
			//(6).값 반환	
			return array;
		}//end selectChoice()
		
		
		
		
		
	//4. 직원 정보 수정
	public int modify(MemberDTO dto) throws SQLException
	{
		//(1).주요 변수 선언
		int result = 0;
		int bNum = findB(dto.getBuseo()); //-- 부서번호
		int jNum = findJ(dto.getJikwi()); //-- 직위번호
		int cNum = findC(dto.getCity()); //-- 지역번호
		
		
		
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3).쿼리문 준비
		String sql = String.format("UPDATE TBL_EMP"
				+ " SET EMP_NAME = '%s', SSN = '%s', IBSADATE = '%s',CITY_ID = %d,"
				+ " TEL = '%s',BUSEO_ID = %d, JIKWI_ID = %d,"
				+ "  BASICPAY = %d,SUDANG = %d"
				+ " WHERE EMP_ID = %s"
				, dto.getName(),dto.getSsn(),dto.getIbsadate(),cNum,dto.getTel(),bNum,jNum,dto.getBasicpay(),dto.getSudang()
				,dto.getId());
		
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		//(5).리소스 반납
		stmt.close();
		
		//(6).값 반환	
		return result;
	}//end modify()
	
	
	
	
	//5. 직원 정보 삭제
	public int delete(int sid) throws SQLException
	{
		//(1).주요 변수 선언
		int result = 0;
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3).쿼리문 준비
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", sid);
		
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		//(5).리소스 반납
		stmt.close();
		
		//(6).값 반환	
		return result;
	}//end delete()
	
	
	
	//부서이름 → 부서번호 
	public int findB(String name) throws SQLException
	{
		int num = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'", name);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			num = rs.getInt("BUSEO_ID");
		}
		return num;
	}
	//직위이름 → 직위번호 
	public int findJ(String name) throws SQLException
	{
		int num = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", name);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			num = rs.getInt("JIKWI_ID");
		}
		return num;
	}
	//지역이름 → 지역번호
	public int findC(String name) throws SQLException
	{
		int num = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME = '%s'", name);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			num = rs.getInt("CITY_ID");
		}
		return num;
	}
	
	
	
	
	//직원 수 세는 함수
	public int count() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		return result;
	}
	
}//end class memberDAO















