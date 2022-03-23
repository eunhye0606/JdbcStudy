package com.test;

public class ScoreDTO
{
	//주요 변수 선언
	//insert 로 넘겨주는 변수들 : sid,name,kor,eng,mat,sum,avg
	
	private int sid, kor,eng,mat,sum;
	private double avg;
	
	
	private String name;

	
	//getter/setter 구성
	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	//----------------------------총점,평균	
	public int getSum()
	{
		return sum;
	}

	public void setSum(int kor, int eng, int mat)
	{
		this.sum = kor + eng + mat;
	}

	public double getAvg()
	{
		return avg;
	}

	public void setAvg(int kor, int eng, int mat)
	{
		this.avg = (kor + eng + mat) / 3.0;

	}
	
	
	

}
