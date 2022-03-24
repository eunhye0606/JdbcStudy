/*=====================================
	ScoreDTO.java
	- 데이터 보관 및 전송 전용 객체
=======================================*/

package com.test;

public class ScoreDTO
{
	//주요 속성 구성
	private String sid, name;		//-- 번호, 이름
	//※ 『sid』는 따로 연산이 필요한게 아니므로 String 타입으로 선언.
	//    추후 오버로딩.
	
	private int kor,eng,mat;		//-- 국어점수, 영어점수, 수학점
	private int tot,rank;			//-- 총점, 석차
	private double avg;				//-- 평균
	
	
	//getter / setter
	public String getSid()
	{
		return sid;
	}
	public void setSid(String sid)
	{
		this.sid = sid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getKor()
	{
		return kor;
	}
	public void setKor(int kor)
	{
		this.kor = kor;
	}
	public int getEng()
	{
		return eng;
	}
	public void setEng(int eng)
	{
		this.eng = eng;
	}
	public int getMat()
	{
		return mat;
	}
	public void setMat(int mat)
	{
		this.mat = mat;
	}
	public int getTot()
	{
		return tot;
	}
	public void setTot(int tot)
	{
		this.tot = tot;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public double getAvg()
	{
		return avg;
	}
	public void setAvg(double avg)
	{
		this.avg = avg;
	}
	
	
}
