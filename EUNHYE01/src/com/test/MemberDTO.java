/*========================
	MemberDTO.java
=========================*/
//사번  이름  주민번호  입사일  지역  
//전화번호  부서  직위  기본급  수당  급여
package com.test;

public class MemberDTO
{
	//주요 변수 선언
	// id : 1001, 1002
	//city : 경기, 서울...
	//buseo : 기획부...
	//jikwi : 사장, 부장...
	private String id, name, ssn, ibsadate, city, tel, buseo,jikwi,basicpay,sudang, pay;

	
	//getter / setter
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public String getIbsadate()
	{
		return ibsadate;
	}

	public void setIbsadate(String ibsadate)
	{
		this.ibsadate = ibsadate;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getBuseo()
	{
		return buseo;
	}

	public void setBuseo(String buseo)
	{
		this.buseo = buseo;
	}

	public String getJikwi()
	{
		return jikwi;
	}

	public void setJikwi(String jikwi)
	{
		this.jikwi = jikwi;
	}

	public String getBasicpay()
	{
		return basicpay;
	}

	public void setBasicpay(String basicpay)
	{
		this.basicpay = basicpay;
	}

	public String getSudang()
	{
		return sudang;
	}

	public void setSudang(String sudang)
	{
		this.sudang = sudang;
	}

	public String getPay()
	{
		return pay;
	}

	public void setPay(String pay)
	{
		this.pay = pay;
	}
	
	
	

}
