/*=======================
 	MemberMain.java
 =======================*/
/*
○ TBL_MEMBER 테이블을 활용하여
   이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
   단, 데이터베이스 연동이 이루어져야 하고,
   MemberDAO, MemberDTO 클래스를 활용해야 한다.
   
실행 예)

이름 전화번호 입력(2) : 오이삭 010-2222-2222
>> 회원 정보 입력 완료 ~!!! 
이름 전화번호 입력(3) : 임소민 010-3333-3333 
>> 회원 정보 입력 완료 ~!!! 
이름 전화번호 입력(4) : .
---------------------------------------------
전체 회원 수 : 3명 
---------------------------------------------
번호	이름	전화번호
1		이호석	010-1111-1111
2		오이삭	010-2222-2222
3		임소민	010-3333-3333
---------------------------------------------
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			
			
			//이름 전화번호 입력(2) → 여기 안에 숫자 
			
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : " ,++count);
				//오이삭
				
				String name = sc.next();
				// 반복의 조건을 무너뜨리는 코드 구성
				if (name.equals("."))
				{
					break;
				}
				
				String tel = sc.next();
				//010-2222-2222
				
				// ※ 여기까지의 과정을 통해 이름과 전화번호를 사용자로부터 입력받은 이유는
				//    입력받은 데이터를 데이터베이스에 입력하기 위함.
				//    데이터 입력을 위해서는 dao 의 add() 메소드 호출
				//    add() 메소드 호출하기 위해서는 MemberDTO 값을 넘겨주는 과정이 필요하다.
				//    MemberDTO 값을 넘겨주기 위해서는 객체의 속성값 구성 필요
				
				// (1).MemberDTO 객체 생성
				MemberDTO dto = new MemberDTO();
				
				// (2).속성값 구성
				dto.setName(name);
				dto.setTel(tel);
				
				//데이터베이스에 데이터 입력하는 메소드 호출 → add()
				int result = dao.add(dto);
				if (result > 0)
				{
					System.out.println(">> 회원 정보 입력 완료 ~!!!");
				}
			} while (true);
			
			System.out.println();
			System.out.println("------------------------------");
			System.out.printf("전체 회원 수 : %d명\n",dao.count());
			System.out.println("------------------------------");
			System.out.println("번호	이름		전화번호");
			System.out.println("------------------------------");
			
			//리스트 가져와 출력하기
			//dao.lists();
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %7s %12s\n",obj.getSid(),obj.getName(),obj.getTel());
			}
			System.out.println("------------------------------");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘~!!!");
				System.out.println("프로그램 종료됨~!!!");
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}
//샐행 결과
/*
이름 전화번호 입력(10) : 신하리 010-4343-4343
>> 회원 정보 입력 완료 ~!!!
이름 전화번호 입력(11) : 강태무 010-5555-5555
>> 회원 정보 입력 완료 ~!!!
이름 전화번호 입력(12) : .

------------------------------
전체 회원 수 : 11명
------------------------------
번호	이름		전화번호
------------------------------
  1     이호석 010-1111-1111
  2     남주혁 010-1234-1234
  3     백이진 010-5555-2222
  4     김문어 010-4444-4444
  5     오이삭 010-2222-2222
  6     김태리 010-6666-6666
  7     시조새 010-4444-2222
  8      ..            .
  9       .            .
 10     신하리 010-4343-4343
 11     강태무 010-5555-5555
------------------------------
데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!!
*/
//아직까지 도메인 x
	
	
	
	
	
	
	
	/*
	 * public static void main(String[] args) { try {
	 * 
	 * MemberDAO mdao = new MemberDAO(); mdao.insert();
	 * System.out.println("-----------------------------------"); int num =
	 * mdao.count(); System.out.println("전체 회원 수 : " + num + "명");
	 * System.out.println("-----------------------------------");
	 * System.out.println("번호    이름    전화번호");
	 * System.out.println("-----------------------------------"); mdao.select();
	 * System.out.println("-----------------------------------"); } catch (Exception
	 * e) { System.out.println(e.toString()); } }
	 */

}//end class MemberMain
