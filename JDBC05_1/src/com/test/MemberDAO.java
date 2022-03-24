/*==============================
	MemberDAO.java
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
	
	
	//데이터베이스 연결하기
	public void connection()
	{
		conn = DBConn.getConnection();
	}//end connection()
	
	
	
	//1번 직원 정보 입력 - 입력
	public int insert(MemberDTO dto) throws SQLException
	{
		//(1).주요 변수 선언
		int result;
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("INSERT INTO TBL_EMP(EMP_ID,EMP_NAME,SSN,IBSADATE,CITY_ID,TEL,BUSEO_ID,JIKWI_ID,BASICPAY,SUDANG)"
				+ " VALUES(EMPSEQ.NEXTVAL,'%s','960812-2345678','2000-04-05',1,'010-1234-1234',1,1,80000000,600000)"
				, dto.getName(),dto.getSsn(),dto.getIbsadate()
				,dto.getCity_id(),dto.getTel(),dto.getBuseo_id()
				,dto.getJikwi_id(),dto.getBasicpay(),dto.getSudang());
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		//(5).리소스반납
		stmt.close();
		//(6).값 반환
		return result;
	}//end insert()
	
	//1번 직원 정보 입력 - 지역출력
	public ArrayList<String> printCity() throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<String> array = new ArrayList<String>();
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = "SELECT CITY_NAME FROM TBL_CITY";
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			array.add(rs.getString("CITY_NAME"));
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end printCity()
	
	//1번 직원 정보 입력 - 부서출력
	public ArrayList<String> printBuseo() throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<String> array = new ArrayList<String>();
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO";
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			array.add(rs.getString("BUSEO_NAME"));
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end printBuseo()
	
	//1번 직원 정보 입력 - 직위출력
	public ArrayList<String> printJikwi() throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<String> array = new ArrayList<String>();
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI";
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			array.add(rs.getString("JIKWI_NAME"));
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end printJikwi()
	
	//1번 직원 정보 입력 - 직위별 기본급 출력
	public String printJikwiBasicpay(String jikwiName) throws SQLException
	{
		//(1).주요 변수 선언
		//ArrayList<String> array = new ArrayList<String>();
		String result = "";
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("SELECT TO_CHAR(MIN_BASICPAY,'999,999,999') AS MIN FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'",jikwiName);
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			result = rs.getString("MIN");
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return result;
	}//end printJikwiBasicpay()
	
	
	
	//2번 직원 전체 출력 - 사번,이름,부서,직위,급여 내림차순
	public ArrayList<MemberDTO> selectAll(int num) throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
		String temp = "";
		
		//(1) - (1) 번호에 따른 정렬 기준.
		switch (num)
		{
			case 1: temp = "EMP_ID";
				break;
			case 2: temp = "EMP_NAME";
				break;
			case 3: temp = "BUSEO_NAME";
				break;
			case 4: temp = "JIKWI_NAME";
				break;
			case 5: temp = "PAY DESC";
				break;
		}
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		
		String sql = String.format("SELECT EMP_ID,EMP_NAME,SSN, IBSADATE,CITY_NAME,TEL,BUSEO_NAME"
				+ ",JIKWI_NAME,BASICPAY,SUDANG, PAY"
				+ " FROM EMPVIEW"
				+ " ORDER BY %s"
				, temp);
		
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_name(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_name(rs.getString("BUSEO_NAME"));
			dto.setJikwi_name(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getString("BASICPAY"));
			dto.setSudang(rs.getString("SUDANG"));
			dto.setPay(rs.getString("PAY"));
			
			
			
			array.add(dto);
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end selectAll(int num)
	
	
	
	
	//3번 직원 검색 출력 - 사번 검색
	public ArrayList<MemberDTO> searchEmp(int id) throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("SELECT EMP_ID,EMP_NAME,SSN, IBSADATE,CITY_NAME,TEL,BUSEO_NAME"
				+ ",JIKWI_NAME,BASICPAY,SUDANG, PAY"
				+ " FROM EMPVIEW"
				+ " WHERE EMP_ID = %d"
				, id);
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_name(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_name(rs.getString("BUSEO_NAME"));
			dto.setJikwi_name(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getString("BASICPAY"));
			dto.setSudang(rs.getString("SUDANG"));
			dto.setPay(rs.getString("PAY"));
			
			
			
			array.add(dto);
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end searchEmp(int id)
	
	
	
	//3번 직원 검색 출력 - 이름,부서,직위 검색
	public ArrayList<MemberDTO> searchEmp(int num, String str) throws SQLException
	{
		//(1).주요 변수 선언
		ArrayList<MemberDTO> array = new ArrayList<MemberDTO>();
		String jogan = "";
		//(1) - (1). 이름 : 2 / 부서 : 3 / 직위 : 4
		switch (num)
		{
		case 2: jogan = "EMP_NAME";
			break;
		case 3: jogan = "BUSEO_NAME";
		break;
		case 4: jogan = "JIKWI_NAME";
		break;
		}
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("SELECT EMP_ID,EMP_NAME,SSN, IBSADATE,CITY_NAME,TEL,BUSEO_NAME"
				+ ",JIKWI_NAME,BASICPAY,SUDANG, PAY"
				+ " FROM EMPVIEW"
				+ " WHERE %s = '%s'"
				, jogan,str);
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_name(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_name(rs.getString("BUSEO_NAME"));
			dto.setJikwi_name(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getString("BASICPAY"));
			dto.setSudang(rs.getString("SUDANG"));
			dto.setPay(rs.getString("PAY"));
			
			
			
			array.add(dto);
		}
		
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return array;
	}//end searchEmp(int num, String str)
	
	
	//4번 직원 정보 수정
	public int updateEmp(MemberDTO dto) throws SQLException
	{
		//(1).주요 변수 선언
		int result = 0;
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("UPDATE TBL_EMP"
				+ " SET EMP_NAME = '%s',SSN = '%s'"
				+ " , IBSADATE='%s' ,CITY_ID = %d"
				+ ",TEL = '%s' ,BUSEO_ID = %d"
				+ " ,JIKWI_ID = %d ,BASICPAY = %s"
				+ " ,SUDANG = %s"
				+ " WHERE EMP_ID = %s"
				, dto.getName(),dto.getSsn()
				,dto.getIbsadate(),dto.getCity_id()
				,dto.getTel(),dto.getBuseo_id()
				,dto.getJikwi_id(),dto.getBasicpay()
				,dto.getSudang()
				,dto.getEmpid());
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		//(5).리소스반납
		stmt.close();
		//(6).값 반환
		return result;
	}//end updateEmp(MemberDTO dto)
	
	
	
	
	//5번 직원 정보 삭제
	public int deleteEmp(int id) throws SQLException
	{
		//(1).주요 변수 선언
		int result = 0;
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", id);
		//(4).쿼리문 실행
		result = stmt.executeUpdate(sql);
		//(5).리소스반납
		stmt.close();
		//(6).값 반환
		return result;
	}//end deleteEmp(int id)
	
	
	
	//*전체 인원 수 출력
	public int count() throws SQLException
	{
		//(1).주요 변수 선언
		int count = 0;
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		//(3).쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		//(4).쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			count = rs.getInt("COUNT");
		}
		//(5).리소스반납
		rs.close();
		stmt.close();
		//(6).값 반환
		return count;
	}//end count()
	
	
	//부서이름으로 부서번호 찾기
	public int buseoNum(String buseoName) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'", buseoName);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			result = rs.getInt("BUSEO_ID");
		}
		rs.close();
		stmt.close();
		
		return result;
	}//end buseoNum(String buseoName)
	
	//직위이름으로 직위번호 찾기
	public int jikwiNum(String jikwiName) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", jikwiName);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			result = rs.getInt("JIKWI_ID");
		}
		rs.close();
		stmt.close();
		
		return result;
	}//end jikwiNum(String jikwiName)
	
	//지역이름으로 지역번호 찾기
	public int cityNum(String cityName) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME = '%s'", cityName);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			result = rs.getInt("CITY_ID");
		}
		rs.close();
		stmt.close();
		
		return result;
	}//end cityNum(String cityName)
}//end class memberDAO















