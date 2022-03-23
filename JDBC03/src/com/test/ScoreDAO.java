package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	//주요 변수 선언
	Connection conn;
	
	//생성자(데이터베이스연결하기)
	ScoreDAO()
	{
		conn = DBConn.getConnection();
	}
	
	//① 입력하기
	public int add(ScoreDTO dto)
	{
		//(1). 값 반환할 변수 선언
		int num = 0;
		
		//(2). 작업 객체 생성하기
		try
		{
			Statement stmt = conn.createStatement();
			//(3). 쿼리문 준비
			String sql = String.format("INSERT INTO TBL_SCORE(SID,NAME,KOR,ENG,MAT) VALUES (SCORESEQ.NEXTVAL,'%s',%d,%d,%d)"
					, dto.getName(),dto.getKor(),dto.getEng(),dto.getMat());
			//(4). 쿼리문 실행
			num = stmt.executeUpdate(sql);
			
			//(5). 리소스 반납
			stmt.close();
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		//(6). 값 리턴하기
		return num;
	}//end add()
	
	//② 조회하기
	public ArrayList<ScoreDTO> select()
	{
		//(1).주요 변수 선언
		ArrayList<ScoreDTO> array = new ArrayList<ScoreDTO>();
		
		//(2).작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//(3). 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT FROM TBL_SCORE";
		
		ResultSet rs;
		try
		{
			//(4). 쿼리문 실행
			rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				//빈 ScoreDTO 준비
				ScoreDTO dto = new ScoreDTO();				
				dto.setSid(rs.getInt("SID"));
			
				//-- 번호
				dto.setName(rs.getString("NAME")); //--이름
				dto.setKor(rs.getInt("KOR")); //--국어점수
				dto.setEng(rs.getInt("ENG")); //--영어점수
				dto.setMat(rs.getInt("MAT")); //--수학점수
				dto.setSum(rs.getInt("KOR"), rs.getInt("ENG"), rs.getInt("MAT"));	//--총점(*이 두개는 오라클에서 연산해서주는게 좋을까?)
				dto.setAvg(rs.getInt("KOR"), rs.getInt("ENG"), rs.getInt("MAT"));	//--평균(*sum을 넘겨주고싶은데 어떻게 하지??)
				
				array.add(dto);
				
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			e1.printStackTrace();
		}

		//(5). 리소스 반납
		rs.close();
		stmt.close();
		
		//(6).값 반환
		return array;
	}

}
