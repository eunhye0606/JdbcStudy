/*===========================================
 	MemberDTO.java
 	
 	- private 변수 선언과 getter/setter
 ========================================*/

package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	// 여기서는 『sid』가 문자형이여야 할까 ? int 이여야 할까 ?
	// 고민했을거야
	private String sid, name, tel;
	// private 변수에 접근하기 위해서는 getter/setter

	
	//이클립스에서 자동으로 getter/setter 만들기
	//『마우스 오른쪽 버튼→ source → generate getter/setter』
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

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	
//내가한거
//---------------------------------------------------------
	/*// 변수 선언
	int sid;
	String name, tel;
	
	//getter/setter
	
	//1.sid
	public int getSid()
	{
		return sid;
	}
	
	public void setSid(int sid)
	{
		this.sid = sid;
	}
	
	//2.name
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	//3.tel
	public String getTel()
	{
		return tel;
	}
	
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	*/
	//---------------------------------------------------------
}
