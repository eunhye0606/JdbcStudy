/*======================
	MemberMain.java
=======================*/
/*
○ 직원 관리 프로그램을 구현한다.
   - 데이터베이스 연동 프로그램으로 작성한다.
   - MemberDTO, MemberDAO 를 활용한다.
   - 메뉴 구성 및 기능을 구현한다.→ Process
   
실행 예)

=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : 1

직원 정보 입력 ------------------------------------------------------------
이름 : 김정용
주민등록번호(yymmdd-nnnnnnn) : 960608-2234567
입사일(yyyy-mm-dd) : 2019-06-08
지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북) : 경기
전화번호 : 010-1111-1111
부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부) : 개발부 
직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : 대리
기본급 (최소 1800000 이상) : 5000000
수당 : 2000000

직원 정보 입력 완료.
------------------------------------------------------------- 직원 정보 입력

=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : 2

1. 사번 정렬
2. 이름 정렬
3. 부서 정렬
4. 직위 정렬
5. 급여 내림차순 정렬
>> 선택(1~5, 『-1』 종료) : -1

=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : 2

1. 사번 정렬
2. 이름 정렬
3. 부서 정렬
4. 직위 정렬
5. 급여 내림차순 정렬
>> 선택(1~5, 『-1』 종료) : 1

전체 인원 : xx명
사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여
1001
1002
1003									...
  :
1060


=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : 3

1. 사번 검색
2. 이름 검색
3. 부서 검색
4. 직위 검색
>> 선택(1~4, 『-1』 종료) : 4

검색할 사번 입력 : ...

...

=====[ 직원 관리 ]=====
1. 직원 정보 입력
2. 직원 전체 출력
	- 사번 정렬
	- 이름 정렬
	- 부서 정렬
	- 직위 정렬
	- 급여 내림차순 정렬
3. 직원 검색 출력
	- 사번 검색
	- 이름 검색
	- 부서 검색
	- 직위 검색
4. 직원 정보 수정
5. 직원 정보 삭제
=======================
>> 메뉴 선택(1~5, 『-1』 종료) : -1

프로그램이 종료되었습니다.
*/

package com.test;

import java.util.Scanner;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		MemberProcess prc = new MemberProcess();
		
		// 직원 정보 입력 메소드 호출
		//prc.memberInsert();
		
		
		/* * 직원 정보 입력 ------------------------------------------------------------
		 이름 : 박현수
		 주민등록번호(yymmdd-nnnnnnn) : 950513-2234567
		 입사일(yyyy-mm-dd) : 2020-09-13
		 지역 (강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/): 경북
		 전화번호 : 010-2525-2525
		 부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 개발부
		 직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : 과장
		 기본급 (최소 2260000 이상) : 5000000
		 수당 : 2000000
		
		 직원 정보 입력 완료~!!!
		 ------------------------------------------------------------- 직원 정보 입력*/
		
		
		//직원 전체 출력 메소드 호출
		//prc.memberLists();
		//잘됨.
		
		//직원 검색 출력 메소드 호출
		//prc.memberSearch();
		
		//직원 수정 메소드 호출
		//prc.memberUpdate();
		/*수정할 직원의 사원번호 입력 : 1020
		
		직원 정보 수정 ------------------------------------------------------------
		기존 이름 : 유영희
		수정 이름 : 김태리
		기존 주민등록번호(yymmdd-nnnnnnn) : 800304-2741258
		수정 주민등록번호(yymmdd-nnnnnnn) : 980606-2345678
		기존 입사일(yyyy-mm-dd) : 2003-10-10
		수정 입사일(yyyy-mm-dd) : 2004-01-20
		기존 지역 : 전남
		수정 지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/): 제주
		기존 전화번호 : 011-9595-8585
		수정 전화번호 : 010-4321-4321
		기존 부서 : 자재부
		수정 부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) :  -
		기존 직위 : 사원
		수정 직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : -
		기존 기본급 : 880000
		수정 기본급 (최소 1600000 이상) : -
		기존 수당 : 140000
		수정 수당 : 500000
		
		직원 데이터 수정 완료 ~!!!------------------------------------------------------------- 직원 정보 수정*/
		
		
		//직원 삭제 메소드 호출
		//prc.memberDelete();
		/*삭제할 직원의 사원번호 입력 : 
			1052
		
		
			검색 인원 : 1명
		
			사번    이름     주민등록번호        입사일    지역       전화번호     부서    직위        기본급         수당         급여
			 1052   권옥경 820406-2000456    2000-10-10    경기   010-3644-5577   기획부   사원       1020000         105000      1125000
		
			정말 삭제하시겠습니까???(Y / N) : y
			직원 정보가 정상적으로 삭제되었습니다 ~!!!*/
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
