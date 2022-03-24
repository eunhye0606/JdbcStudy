/*========================
	MemberDTO.java
=========================*/
package com.test;

public class MemberDTO
{
	private String empid
					,name, ssn, ibsadate, tel, basicpay, sudang, pay
					,city_name, buseo_name, jikwi_name;
	
	private int city_id, buseo_id, jikwi_id;

	
	
	
	
	// getter / setter
	public String getEmpid()
	{
		return empid;
	}

	public void setEmpid(String empid)
	{
		this.empid = empid;
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

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
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

	public String getCity_name()
	{
		return city_name;
	}

	public void setCity_name(String city_name)
	{
		this.city_name = city_name;
	}

	public String getBuseo_name()
	{
		return buseo_name;
	}

	public void setBuseo_name(String buseo_name)
	{
		this.buseo_name = buseo_name;
	}

	public String getJikwi_name()
	{
		return jikwi_name;
	}

	public void setJikwi_name(String jikwi_name)
	{
		this.jikwi_name = jikwi_name;
	}

	public int getCity_id()
	{
		return city_id;
	}

	public void setCity_id(int city_id)
	{
		this.city_id = city_id;
	}

	public int getBuseo_id()
	{
		return buseo_id;
	}

	public void setBuseo_id(int buseo_id)
	{
		this.buseo_id = buseo_id;
	}

	public int getJikwi_id()
	{
		return jikwi_id;
	}

	public void setJikwi_id(int jikwi_id)
	{
		this.jikwi_id = jikwi_id;
	}
	
	
	
	
	
	
}//end class MemberDTO