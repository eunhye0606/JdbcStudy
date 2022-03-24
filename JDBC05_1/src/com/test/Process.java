/*====================
 	Process.java
======================*/
package com.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Process
{
	MemberDAO dao;
	
	Process()
	{
		dao = new MemberDAO();
	}
	
	//메뉴 출력하기
	public int menuPrint()
	{
		int num = 0;
		Scanner sc = new Scanner(System.in);
		
		
			System.out.println("========[ 직원 관리 ]========");
			System.out.println("1. 직원 정보 입력");
			System.out.println("2. 직원 전체 출력");
			System.out.println("	 - 사번 정렬");
			System.out.println("	 - 이름 정렬");
			System.out.println("	 - 부서 정렬");
			System.out.println("	 - 직위 정렬");
			System.out.println("	 - 급여 내림차순 정렬");
			System.out.println("3. 직원 검색 출력");
			System.out.println("	 - 사번 검색");
			System.out.println("	 - 이름 검색");
			System.out.println("	 - 부서 검색");
			System.out.println("	 - 직위 검색");
			System.out.println("4. 직원 정보 수정");
			System.out.println("5. 직원 정보 삭제");
			System.out.println("=============================");
			
			System.out.print(">> 메뉴 선택(1~5, 『-1』 종료) : ");
			num = Integer.parseInt(sc.next());
		
		
		return num;
	}
	
	//1. 직원 정보 입력
	public void empInsert() throws SQLException
	{
		//db 연결하기
		dao.connection();
		
		
		Scanner sc = new Scanner(System.in);
		
		
		String name, ssn
			   , ibsadate, city_name
			   , tel, buseo_name
			   , jikwi_name, basicpay
			   , sudang;
		String [] cityArray = new String [dao.printCity().size()] ;
		String [] buseoArray = new String [dao.printBuseo().size()];
		String [] jikwiArray = new String [dao.printJikwi().size()];
		
		String bPay;
		
		
			    
		
		System.out.println("직원 정보 입력 ------------------------------------------------------------");
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
		ssn = sc.next();
		System.out.print("입사일(yyyy-mm-dd) : ");
		ibsadate = sc.next();
		System.out.print("지역(");
		for (String str : dao.printCity())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		//-- 지역array에 포함되지 않으면 재입력 요청
		city_name = sc.next();
		System.out.print("전화번호 : ");
		tel = sc.next();
		System.out.print("부서(");
		for (String str : dao.printBuseo())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		//-- 부서array에 포함되지 않으면 재입력 요청
		buseo_name = sc.next();
		System.out.print("직위(");
		for (String str : dao.printJikwi())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		//-- 직위array에 포함되지 않으면 재입력 요청
		jikwi_name = sc.next();
		bPay = dao.printJikwiBasicpay(jikwi_name); //-- DB에 있는 기본급 (최소 ~~ 이상)
		System.out.printf("기본급 (최소 %s 이상) : ",bPay);
		//--최소 얼마 미만이면 재입력 요청
		basicpay = sc.next();
		System.out.print("수당 : ");
		sudang = sc.next();
		
		
		
		MemberDTO dto = new MemberDTO();
		dto.setName(name);
		dto.setSsn(ssn);
		dto.setIbsadate(ibsadate);
		dto.setCity_name(city_name);
		dto.setTel(tel);
		dto.setBuseo_name(buseo_name);
		dto.setJikwi_name(jikwi_name);
		dto.setBasicpay(basicpay);
		dto.setSudang(sudang);
		
		int temp = dao.insert(dto);
		if (temp > 0)
		{
			System.out.println("직원 정보 입력 완료.");
		}
		System.out.println("------------------------------------------------------------- 직원 정보 입력");
		
	}//end empInsert()

	//2. 직원 전체 출력
	public void empSelect() throws SQLException
	{
		//db연결하기
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		int num = 0;
		int count = 0;
		
		do
		{
			System.out.println();
			System.out.println("1. 사번 정렬");
			System.out.println("2. 이름 정렬");
			System.out.println("3. 부서 정렬");
			System.out.println("4. 직위 정렬");
			System.out.println("5. 급여 내림차순 정렬");
			System.out.print(">> 선택(1~5, 『-1』 종료) : ");
			num = Integer.parseInt(sc.next());
			
			if (num == -1)
			{
				return;
			}
		} while (num > 5 || num < 0);
		count = dao.count();
		//출력하기
		System.out.println();
		System.out.printf("전체 인원 수 : %d 명",count);
		System.out.println();
		System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
							,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
		for (MemberDTO dto : dao.selectAll(num))
		{
			System.out.printf("%5s %5s %10s %13s %5s %15s %5s %4s %13s %14s %12s\n"
							 ,dto.getEmpid(),dto.getName()
							 ,dto.getSsn(),dto.getIbsadate().substring(0,10)
							 ,dto.getCity_name(),dto.getTel()
							 ,dto.getBuseo_name(),dto.getJikwi_name()
							 ,dto.getBasicpay(),dto.getSudang()
							 ,dto.getPay());
		}
		System.out.println();
	}//end empSelect()
	
	
	//3. 직원 검색 출력
	public void empSerch() throws SQLException
	{
		//db연결하기
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		int num = 0;
		String str = "";
		
		do
		{
			System.out.println();
			System.out.println("1. 사번 검색");
			System.out.println("2. 이름 검색");
			System.out.println("3. 부서 검색");
			System.out.println("4. 직위 검색");
			System.out.print(">> 선택(1~4, 『-1』 종료) : ");
			num = sc.nextInt();
			if (num == -1)
			{
				return;
			}
		} while (num > 5 || num < 0);
		
		if (num == 1)
		{
			System.out.print("검색할 사원번호 입력 : ");
			int temp = sc.nextInt();
			
			System.out.println();
			
			if (dao.searchEmp(temp).size() ==0)
			{
				System.out.println("검색 결과가 없습니다.");
				System.out.println();
				return;
			}
			else
			{
				System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
						,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
	
				for (MemberDTO dto : dao.searchEmp(temp))
				{
					System.out.printf("%5s %5s %10s %13s %5s %15s %5s %4s %13s %14s %12s\n"
									 ,dto.getEmpid(),dto.getName()
									 ,dto.getSsn(),dto.getIbsadate().substring(0,10)
									 ,dto.getCity_name(),dto.getTel()
									 ,dto.getBuseo_name(),dto.getJikwi_name()
									 ,dto.getBasicpay(),dto.getSudang()
									 ,dto.getPay());
				}
				System.out.println();
			}
		}
		else
		{
			if (num == 2)
			{
				System.out.print("검색할 이름 입력 : ");
				str = sc.next();
			}
			else if(num == 3)
			{
				System.out.println("검색할 부서 입력 : ");
				str = sc.next();
			}
			else if(num == 4)
			{
				System.out.println("검색할 직위 입력 : ");
				str = sc.next();
			}
			
			
			if (dao.searchEmp(num, str).size() ==0)
			{
				System.out.println("검색 결과가 없습니다.");
				System.out.println();
				return;
			}			
			else
			{
				System.out.println();
				System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
									,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
				
				
				for (MemberDTO dto : dao.searchEmp(num, str))
				{
					System.out.printf("%5s %5s %10s %13s %5s %15s %5s %4s %13s %14s %12s\n"
									 ,dto.getEmpid(),dto.getName()
									 ,dto.getSsn(),dto.getIbsadate().substring(0,10)
									 ,dto.getCity_name(),dto.getTel()
									 ,dto.getBuseo_name(),dto.getJikwi_name()
									 ,dto.getBasicpay(),dto.getSudang()
									 ,dto.getPay());
				}
				System.out.println();
			}
			
		}
	
	}//end empSerch()
	
	
	
	
	//4. 직원 정보 수정
	public void empUpdate() throws SQLException
	{
		//db연결하기
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		
		String name, ssn, ibsadate, tel,basicpay,sudang,empid; 
		int city_id,buseo_id,jikwi_id;
		
		String bPay = "";
		
		System.out.print("검색할 사번 입력 : ");
		empid = sc.next();
		
		System.out.print("바꿀 이름 입력 : ");
		name = sc.next();
		System.out.print("바꿀 주민등록번호 입력 (yymmdd-nnnnnnn) :");
		ssn = sc.next();
		System.out.print("바꿀 입사일 입력 (yyyy-mm-dd) : ");
		ibsadate = sc.next();
		
		System.out.print("바꿀 지역 입력 (");
		for (String str : dao.printCity())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		city_id = dao.cityNum(sc.next());
		
		
		
		System.out.print("바꿀 전화번호 : ");
		tel = sc.next();
		
		
		System.out.print("바꿀 부서 입력 (");
		for (String str : dao.printBuseo())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		buseo_id = dao.buseoNum(sc.next());
		
		
		
		
		System.out.print("바꿀 직위 입력 (");
		for (String str : dao.printJikwi())
		{
			System.out.print(str + " ");
		}
		System.out.print(") : ");
		String temp = sc.next();
		bPay = dao.printJikwiBasicpay(temp); //-- DB에 있는 기본급 (최소 ~~ 이상)
		jikwi_id = dao.jikwiNum(temp);
		
		System.out.printf("바꿀 기본급 입력(최소 %s 이상) : ",bPay);
		basicpay = sc.next();
		
		System.out.print("바꿀 수당 입력 : ");
		sudang = sc.next();
		
		
		MemberDTO dto = new MemberDTO();
		dto.setName(name);
		dto.setSsn(ssn);
		dto.setIbsadate(ibsadate);
		dto.setCity_id(city_id);
		dto.setTel(tel);
		dto.setBuseo_id(buseo_id);
		dto.setJikwi_id(jikwi_id);
		dto.setBasicpay(basicpay);
		dto.setSudang(sudang);
		dto.setEmpid(empid);
		
		int result = dao.updateEmp(dto);
		
		if (result > 0)
		{
			System.out.println("입력 정보가 성공적으로 수정되었습니다.");
		}
			
		
	}//end empUpdate()
	
	
	
	
	//5. 직원 정보 삭제
	public void empDelete() throws SQLException
	{
		//db연결
		dao.connection();	
		
		//주요 변수 선언
		int empid = 0;
		int result = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 사원번호 입력 : ");
		empid = sc.nextInt();
		
		result = dao.searchEmp(empid).size();
		
		if (result > 0)
		{
			System.out.println();
			System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
								,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
			
			
			for (MemberDTO dto : dao.searchEmp(empid))
			{
				System.out.printf("%5s %5s %10s %13s %5s %15s %5s %4s %13s %14s %12s\n"
								 ,dto.getEmpid(),dto.getName()
								 ,dto.getSsn(),dto.getIbsadate().substring(0,10)
								 ,dto.getCity_name(),dto.getTel()
								 ,dto.getBuseo_name(),dto.getJikwi_name()
								 ,dto.getBasicpay(),dto.getSudang()
								 ,dto.getPay());
			}
			
			String respon;
			
			do
			{
				System.out.print("정말 삭제하겠습니까? (Y / N) : ");
				respon = sc.next();
				
				if (respon.equals("y")||respon.equals("Y"))
				{
					break;
				}
			} while (true);
			
			int num = dao.deleteEmp(empid);
			
			if (num > 0)
			{
				System.out.println("데이터가 정상적으로 삭제되었습니다.");
			}
		
		}
		
		
		
	}//end empDelete()
}//end class Process
