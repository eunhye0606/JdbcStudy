/*=====================
	ScoreMain.java
=====================*/

/*
○ 성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO,ScoreDTO 클래스 활용

	여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
	총점, 평균을 연산하여 내용을 출력하는 프로그램을 구현한다.
	출력 시, 번호(이름,총점 등) 오름차순으로 정렬하여 출력한다.
	
실행 예)
1번 학생 성적 입력(이름 국어 영어 수학) : 신시은 80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 이호석 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 이연주 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

-------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
-------------------------------------------------------
 1		신시은	80		75		60		xxx     xxx.x
 2
 ....
-------------------------------------------------------
*/

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		//①입력받기
		Scanner sc = new Scanner(System.in);
		
		ScoreDAO dao = new ScoreDAO();
		do
		{
			System.out.print("학생 성적 입력(이름 국어 영어 수학 [종료 : 『.』입력) : ");
			String name = sc.next();
			
			if (name.equals("."))
			{
				break;
			}
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			//dto에 값 세팅하기
			ScoreDTO dto = new ScoreDTO();
			
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			
			//입력하기
			int result = dao.add(dto);
			if (result > 0)
			{
				System.out.printf(">>%d 개의 데이터가 새롭게 추가되었습니다!",result);
			}	
			
		} while (true);
		
		System.out.println("-------------------------------------------------------");
		System.out.println("번호	이름	국어	영어	수학	총점	평균");
		System.out.println("-------------------------------------------------------");
		//②조회하기
		for (ScoreDTO dto : dao.select())
		{
			
		}
		
		
		
		
		
		
		
		
		
	}

}
