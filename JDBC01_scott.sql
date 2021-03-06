SELECT USER
FROM DUAL;
--==>>SCOTT

DROP TABLE TBL_MEMBER PURGE;
--==>>Table TBL_MEMBER이(가) 삭제되었습니다.

--○ 실습 테이블 생성
CREATE TABLE TBL_MEMBER
(SID    NUMBER
,NAME   VARCHAR2(30)
,TEL    VARCHAR2(60)
,CONSTRAINT MEMBER_SID_PK   PRIMARY KEY(SID)
);
--==>>Table TBL_MEMBER이(가) 생성되었습니다.

--○ 샘플 데이터 입력
INSERT INTO TBL_MEMBER(SID,NAME,TEL) VALUES(1, '홍길동','010-1111-1111');
--==>>1 행 이(가) 삽입되었습니다.


--○ 확인
SELECT *
FROM TBL_MEMBER;
--==>>1	홍길동	010-1111-1111

--○ 커밋
COMMIT;
--==>>커밋 완료.

--○ 자바에서 Test003.java 실행 후 다시 확인

SELECT *
FROM TBL_MEMBER;
--==>>
/*
1	홍길동	010-1111-1111
2	홍은혜	010-4139-4969
*/

UPDATE TBL_MEMBER
SET NAME = '최길동'
WHERE SID = 1;
--==>>1 행 이(가) 업데이트되었습니다.

-- 커밋을 해야 트랜잭선 완료.
COMMIT;
--==>> 커밋 완료.

UPDATE TBL_MEMBER
SET NAME = '최길동'
WHERE SID = 2;

UPDATE TBL_MEMBER
SET TEL = '010-2222-2222'
WHERE SID = 2;

-- 커밋을 해야 트랜잭선 완료.
COMMIT;
--==>> 커밋 완료.


--○ 자바에서 Test004.java 실행 후 다시 확인
SELECT *
FROM TBL_MEMBER;
/*

1	최길동	010-1111-1111
2	최길동	010-2222-2222
4	오이삭	010-4444-4444
5	김태형	010-5555-5555
3	김정용	010-3333-3333

*/

UPDATE TBL_MEMBER
SET NAME = '최문어'
WHERE SID = 2;

COMMIT;

--○ 조회 쿼리문 준비

SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
-- 이클립스로 넘기자.
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

