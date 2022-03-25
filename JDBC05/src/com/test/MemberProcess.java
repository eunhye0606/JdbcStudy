package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess
{
	//주요 속성 구성
	private MemberDAO dao;
	
	//생성자 정의
	MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	//직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			
			//데이터베이스 연결
			dao.connection();
			
			//지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
				cityStr.append(city + "/"); 
			
			//부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
				buseoStr.append(buseo + "/");
			
			//직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}
			
			//사용자에게 보여지는 화면 처리
			System.out.println();
			System.out.println(" * 직원 정보 입력 ------------------------------------------------------------");
			System.out.print("이름 : ");
			String empName = sc.next();
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsadate = sc.next();
			System.out.printf("지역 (%s): ",cityStr.toString());
			String cityName = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			System.out.printf("부서(%s) : ",buseoStr.toString());
			String buseoName = sc.next();
			System.out.printf("직위(%s) : ",jikwiStr.toString());
			String jikwiName = sc.next();
			System.out.printf("기본급 (최소 %d 이상) : ",dao.searchBasicPay(jikwiName));
			int basicpay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			System.out.println();
			
			
			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName);
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsadate);
			dto.setCityName(cityName);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicPay(basicpay);
			dto.setSudang(sudang);
			
			int result = dao.add(dto);
			if (result > 0)
				System.out.println("직원 정보 입력 완료~!!!");
			System.out.println("------------------------------------------------------------- 직원 정보 입력");
			System.out.println();
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}//end memberInsert()
	
	//직원 전체 출력 메소드 정의
	public void memberLists()
	{
	
		Scanner sc = new Scanner(System.in);
		
		//서브 메뉴 출력(안내)
		System.out.println();
		System.out.println("1. 사번 정렬");					//EMP_ID
		System.out.println("2. 이름 정렬");					//EMP_NAME
		System.out.println("3. 부서 정렬");					//BUSEO_NAME
		System.out.println("4. 직위 정렬");					//JIKWI_NAME
		System.out.println("5. 급여 내림차순 정렬");		//PAY DESC
		System.out.print(">> 선택(1~5, 『-1』 종료) : ");
		String menuStr = sc.next();
		
		
		try
		{
			
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
				return;
			String key ="";
			
			switch (menu)
			{
				case 1: key = "EMP_ID";
					break;
				case 2: key = "EMP_NAME";
					break;
				case 3: key = "BUSEO_NAME";
					break;
				case 4: key = "JIKWI_NAME";
					break;
				case 5: key = "PAY DESC";
					break;
			}
			
			//데이터베이스 연결
			dao.connection();
			
			//직원 리스트 출력
			System.out.println();
			System.out.printf("전체 인원 : %d명\n",dao.memberCount());
			System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
					,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
			ArrayList<MemberDTO> memList = dao.lists(key);
			
			for (MemberDTO dto : memList)
			{
				System.out.printf("%5d %5s %10s %13s %5s %15s %5s %4s %13d %14d %12d\n"
									,dto.getEmpId(),dto.getEmpName(),dto.getSsn()
									,dto.getIbsaDate(),dto.getCityName(),dto.getTel()
									,dto.getBuseoName(),dto.getJikwiName()
									,dto.getBasicPay(),dto.getSudang(),dto.getPay());
			}
			System.out.println();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}//end memberLists()
	
	//직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		
		//서브 메뉴 출력
		System.out.println();
		System.out.println("1. 사번 검색");					//key : EMP_ID
		System.out.println("2. 이름 검색");					//key : EMP_NAME
		System.out.println("3. 부서 검색");					//key : BUSEO_NAME
		System.out.println("4. 직위 검색");					//key : JIKWI_NAME
		System.out.print(">> 선택(1~4, 『-1』 종료) : ");
		String menuStr = sc.next();
		
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			
			if (menu == -1)
				return;
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
			case 1: key = "EMP_ID";
					System.out.print("검색할 사원번호 입력 : ");
					value = sc.next();
				break;
			case 2: key = "EMP_NAME";
					System.out.print("검색할 이름 입력 : ");
					value = sc.next();
				break;
			case 3: key = "BUSEO_NAME";
					System.out.print("검색할 부서명 입력 : ");
					value = sc.next();
				break;
			case 4: key = "JIKWI_NAME";
					System.out.print("검색할 직위명 입력 : ");
					value = sc.next();
				break;
			}
			//데이터베이스 연결
			dao.connection();
			
			//검색 결과 출력
			System.out.println();
			System.out.printf("검색 인원 : %d명\n",dao.memberCount(key, value));
			System.out.println();
			System.out.printf("전체 인원 : %d명\n",dao.memberCount());
			System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
					,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
			ArrayList<MemberDTO> memList = dao.searchLists(key, value);
			
			for (MemberDTO dto : memList)
			{
				System.out.printf("%5d %5s %10s %13s %5s %15s %5s %4s %13d %14d %12d\n"
									,dto.getEmpId(),dto.getEmpName(),dto.getSsn()
									,dto.getIbsaDate(),dto.getCityName(),dto.getTel()
									,dto.getBuseoName(),dto.getJikwiName()
									,dto.getBasicPay(),dto.getSudang(),dto.getPay());
			}
			System.out.println();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}	
		
	}//end memberSearch()
	
	
	//직원 정보 수정 메소드 정의
	public void memberUpdate()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 수정할 대상 입력받기
			System.out.print("수정할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			//데이터베이스 연결
			dao.connection();
			
			ArrayList<MemberDTO> memList = dao.searchLists("EMP_ID", value);
			
			if (memList.size() > 0)
			{
				//검색 결과 출력
				
				
				//지역 리스트 구성
				ArrayList<String> citys = dao.searchCity();
				StringBuilder cityStr = new StringBuilder();
				for (String city : citys)
					cityStr.append(city + "/"); 
				
				//부서 리스트 구성
				ArrayList<String> buseos = dao.searchBuseo();
				StringBuilder buseoStr = new StringBuilder();
				for (String buseo : buseos)
					buseoStr.append(buseo + "/");
				
				//직위 리스트 구성
				ArrayList<String> jikwis = dao.searchJikwi();
				StringBuilder jikwiStr = new StringBuilder();
				for (String jikwi : jikwis)
				{
					jikwiStr.append(jikwi + "/");
				}
				
				//★★★풀이.★★★
				//사원번호로 조회하는거라 한명밖에 안뜸.
				//그래서 get(0)
				MemberDTO mMember = memList.get(0);
				
				int mEmpId = mMember.getEmpId();
				String mEmpName = mMember.getEmpName();
				String mSsn = mMember.getSsn();
				String mIbsaDate = mMember.getIbsaDate();
				String mCityName = mMember.getCityName();
				String mTel = mMember.getTel();
				String mBuseoName = mMember.getBuseoName();
				String mJikwiName = mMember.getJikwiName();
				int mBasicPay = mMember.getBasicPay();
				int mSudang = mMember.getSudang();
				
				//사용자에게 보여지는 화면 처리
				System.out.println();
				System.out.println("직원 정보 수정 ------------------------------------------------------------");
				System.out.printf("기존 이름 : %s",mEmpName);
				System.out.println();
				System.out.print("수정 이름 : ");
				String empName = sc.next();
				if (empName.equals("-"))
				{
					empName = mEmpName;
				}
				System.out.printf("기존 주민등록번호(yymmdd-nnnnnnn) : %s",mSsn);
				System.out.println();
				System.out.print("수정 주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				if (ssn.equals("-"))
				{
					ssn = mSsn;
				}
				System.out.printf("기존 입사일(yyyy-mm-dd) : %s",mIbsaDate);
				System.out.println();
				System.out.print("수정 입사일(yyyy-mm-dd) : ");
				String ibsaDate = sc.next();
				if (ibsaDate.equals("-"))
				{
					ibsaDate = mIbsaDate;
				}
				System.out.printf("기존 지역 : %s",mCityName);
				System.out.println();
				System.out.printf("수정 지역(%s): ",cityStr.toString());
				String cityName = sc.next();
				if (cityName.equals("-"))
				{
					cityName = mCityName;
				}
				System.out.printf("기존 전화번호 : %s",mTel);
				System.out.println();
				System.out.print("수정 전화번호 : ");
				String tel = sc.next();
				if (tel.equals("-"))
				{
					tel = mTel;
				}
				System.out.printf("기존 부서 : %s",mBuseoName);
				System.out.println();
				System.out.printf("수정 부서(%s) :  ",buseoStr.toString());
				String buseoName = sc.next();
				if (buseoName.equals("-"))
				{
					buseoName = mBuseoName;
				}
				System.out.printf("기존 직위 : %s",mJikwiName);
				System.out.println();
				System.out.printf("수정 직위(%s) : ",jikwiStr.toString());
				String jikwiName = sc.next();
				if (jikwiName.equals("-"))
				{
					jikwiName = mJikwiName;
				}
				System.out.printf("기존 기본급 : %d",mBasicPay);
				System.out.println();
				System.out.printf("수정 기본급 (최소 %d 이상) : ",dao.searchBasicPay(jikwiName));
				String basicPayStr = sc.next();
				int basicPay = 0;
				if (basicPayStr.equals("-"))
				{
					basicPay = mBasicPay;
				}
				else
				{
					basicPay = Integer.parseInt(basicPayStr);
				}
				System.out.printf("기존 수당 : %d",mSudang);
				System.out.println();
				System.out.print("수정 수당 : ");
				String sudangStr = sc.next();
				int sudang = 0;
				if (sudangStr.equals("-"))
				{
					sudang = mSudang;
				}
				else
				{
					sudang = Integer.parseInt(sudangStr);
				}
				System.out.println();
				
				// 새로 입력받은(수정한) 내용을 통해 DTO 구성
				// 수정한다해서 기존의 dto를 바꾸는 것이 아니라
				// 새롭게 만들고 세팅하는거라
				// emp_id도 넘겨줘서 세팅 새로하는거임.
				// 이 dto를 modify에게 넘겨줄거라!!
				MemberDTO member = new MemberDTO();
				member.setEmpId(mEmpId);
				
				member.setEmpName(empName);
				member.setSsn(ssn);
				member.setIbsaDate(ibsaDate);
				member.setCityName(cityName);
				member.setTel(tel);
				member.setBuseoName(buseoName);
				member.setJikwiName(jikwiName);
				member.setBasicPay(basicPay);
				member.setSudang(sudang);
			
				int result = dao.modify(member);
				
				if (result > 0)
				{
					System.out.print("직원 데이터 수정 완료 ~!!!");
				}
				System.out.print("------------------------------------------------------------- 직원 정보 수정");
				System.out.println();
				
				
				
				
				
				/*for (MemberDTO dto : memList)
				{
					//사용자에게 보여지는 화면 처리
					System.out.println();
					System.out.print("직원 정보 수정 ------------------------------------------------------------");
					System.out.println("※ 기존 사항 유지 『-』 입력");
					System.out.printf("기존 이름 : %s",dto.getEmpName());
					System.out.print("수정 이름 : ");
					String name = sc.next();
					if (name.equals("-"))
					{
						name = dto.getEmpName();
					}
					System.out.printf("기존 주민등록번호(yymmdd-nnnnnnn) : %s",dto.getSsn());
					System.out.print("수정 주민등록번호(yymmdd-nnnnnnn) : ");
					String ssn = sc.next();
					if (ssn.equals("-"))
					{
						ssn = dto.getSsn();
					}
					System.out.printf("기존 입사일(yyyy-mm-dd) : %s",dto.getIbsaDate());
					System.out.print("수정 입사일(yyyy-mm-dd) : ");
					String ibsadate = sc.next();
					if (ibsadate.equals("-"))
					{
						ibsadate = dto.getIbsaDate();
					}
					System.out.printf("기존 지역 : %s",dto.getCityName());
					System.out.printf("수정 지역(%s): ",cityStr.toString());
					String cityName = sc.next();
					if (cityName.equals("-"))
					{
						cityName = dto.getCityName();
					}
					System.out.printf("기존 전화번호 : %s",dto.getTel());
					System.out.print("수정 전화번호 : ");
					String tel = sc.next();
					if (tel.equals("-"))
					{
						tel = dto.getTel();
					}
					System.out.printf("기존 부서 : %s",dto.getBuseoName());
					System.out.printf("수정 부서(%s) :  ",buseoStr.toString());
					String buseoName = sc.next();
					if (tel.equals("-"))
					{
						tel = dto.getTel();
					}
					System.out.printf("기존 직위 : %s",dto.getJikwiName());
					System.out.printf("수정 직위(%s) : ",jikwiStr.toString());
					String jikwiName = sc.next();
					if (jikwiName.equals("-"))
					{
						jikwiName = dto.getJikwiName();
					}
					System.out.printf("기존 기본급 : %d",dto.getBasicPay());
					System.out.printf("수정 기본급 (최소 %d 이상) : ",dao.searchBasicPay(jikwiName));
					String basicpay = sc.next();
					if (basicpay.equals("-"))
					{
						basicpay = Integer.toString(dto.getBasicPay());
					}
					System.out.printf("기존 수당 : %d",dto.getSudang());
					System.out.print("수정 수당 : ");
					String sudang = sc.next();
					if (sudang.equals("-"))
					{
						sudang = Integer.toString(dto.getSudang());
					}
					System.out.println();
					
					dto.setEmpName(name);
					dto.setSsn(ssn);
					dto.setIbsaDate(ibsadate);
					dto.setCityName(cityName);
					dto.setTel(tel);
					dto.setBuseoName(buseoName);
					dto.setJikwiName(jikwiName);
					dto.setBasicPay(Integer.parseInt(basicpay));
					dto.setSudang(Integer.parseInt(sudang));
				
					int result = dao.modify(dto);
					
					if (result > 0)
					{
						System.out.print("직원 정보 입력 완료.");
					}
					System.out.print("------------------------------------------------------------- 직원 정보 수정");
					System.out.println();
				}*/
				
			}
			else
			{
				System.out.println("수정 대상을 검색하지 못했습니다~!!!");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}//end memberUpdate()
	
	//직원 정보 삭제 메소드 정의
	public void memberDelete()
	{
		
		Scanner sc = new Scanner(System.in);
		
		try
		{
			//보통 실무에서 입력 받을 때는 『문자열』로 받는 경우가 많음.
			System.out.print("삭제할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			// 사용자가 입력한 직원의 사원번호에 대한 유효성 검사 가능~!!!
			
			// 직원 정보 확인 후 삭제여부 결정
			
			//데이터베이스 연결
			dao.connection();
			
			dao.searchLists("EMP_ID", value);
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			
			if (members.size() > 0)
			{
				System.out.println();
				
				//검색 결과 출력
				System.out.println();
				System.out.printf("검색 인원 : %d명\n",dao.memberCount("EMP_ID", value));
				System.out.println();
				System.out.printf("%2s %5s %10s %10s %5s %10s %6s %5s %10s %10s %10s\n"
						,"사번","이름","주민등록번호","입사일","지역","전화번호","부서","직위","기본급","수당","급여");
				
				for (MemberDTO dto : members)
				{
					System.out.printf("%5d %5s %10s %13s %5s %15s %5s %4s %13d %14d %12d\n"
										,dto.getEmpId(),dto.getEmpName(),dto.getSsn()
										,dto.getIbsaDate(),dto.getCityName(),dto.getTel()
										,dto.getBuseoName(),dto.getJikwiName()
										,dto.getBasicPay(),dto.getSudang(),dto.getPay());
				}
				System.out.print("\n정말 삭제하시겠습니까???(Y / N) : ");
				String response = sc.next();
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(Integer.parseInt(value));
					if (result > 0)
					{
						System.out.println("직원 정보가 정상적으로 삭제되었습니다 ~!!!");
					}
				}
				System.out.println();
				
			}
			else
			{
				System.out.println("삭제 대상을 찾지 못했습니다.~!!!");
			}
		
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
	}//end memberDelete()
	
	
}//end calss MemberProcess
