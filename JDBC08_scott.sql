SELECT USER
FROM DUAL;
--==>>SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	이호석	010-1111-1111
2	남주혁	010-1234-1234
3	백이진	010-5555-2222
4	김문어	010-4444-4444
5	오이삭	010-2222-2222
6	김태리	010-6666-6666
7	시조새	010-4444-2222
10	신하리	010-4343-4343
11	강태무	010-5555-5555
12	김상기	010-4444-4444
13	김충희	010-5555-5555
14	변백현	010-6666-6666
*/


TRUNCATE TABLE TBL_MEMBER;
--==>>Table TBL_MEMBER이(가) 잘렸습니다.
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>> 조회 결과 없음.



--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명   : PRC_MEMBERINSERT
--   프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
(VNAME IN TBL_MEMBER.NAME%TYPE
,VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- 주요 변수 선언
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;
    
    -- 데이터 입력(INSERT 쿼리문 수행)
    INSERT INTO TBL_MEMBER(SID,NAME,TEL)
    VALUES((VSID+1),VNAME,VTEL);
    
    --커밋
    COMMIT;
END;
--==>>Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ 생성된 프로시저 테스트(확인)
EXEC PRC_MEMBERINSERT('이호석','010-1111-1111');
--==>>PL/SQL 프로시저가 성공적으로 완료되었습니다.

--○ 테이블 조회
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--==>>1	이호석	010-1111-1111



--○ JDBC08 의 Test001 실행 후 확인
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
/*
1	이호석	010-1111-1111
2	남주혁	010-2222-2222
3	강태무	010-3333-3333
4	백이진	010-4444-4444
*/




--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명   : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
--       이미 정해진 커서라 정의 안해두댐.
--   빈공간에 열무 담아서 건내다 → IN
--   빈공간 줄게 채워서 줘 → OUT
--   INOUT도 있음.
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT OUT SYS_REFCURSOR
)
IS
    -- 주요 변수 선언
BEGIN
    OPEN VRESULT FOR
        SELECT SID,NAME,TEL
        FROM TBL_MEMBER
        ORDER BY SID;
    -- CLOSE VRESULT; 
    -- 닫으면 JAVA에서 못열어
    
    -- COMMIT;
    -- SELECT 만 실행이기 때문에 COMMIT 도 필요없음.
END;
--==>>Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.


--○ 프로시저 생성 후 테스트(확인)
EXEC PRC_MEMBERSELECT;
















