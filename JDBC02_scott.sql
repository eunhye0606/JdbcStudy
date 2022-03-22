SELECT USER
FROM DUAL;
--==>>SCOTT

TRUNCATE TABLE TBL_MEMBER;
--==>>Table TBL_MEMBER이(가) 잘렸습니다.
-- 구조는 살리고 데이터 삭제한거임

CREATE SEQUENCE MEMBERSEQ
NOCACHE;
--==>>Sequence MEMBERSEQ이(가) 생성되었습니다.

--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '이호석','010-1111-1111');
--==>>1 행 이(가) 삽입되었습니다.
--> 한 줄 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '이호석','010-1111-1111')
;


--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBER;
--==>>1
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_MEMBER
;

--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--==>>1	이호석	010-1111-1111
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

--○ 커밋 (데이터 INSERT 했으니까)
COMMIT;
--==>> 커밋 완료.

DESC TBL_MEMBER;


--확인
SELECT *
FROM TBL_MEMBER;

INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '남주혁','010-1234-1234');

COMMIT;

SELECT *
FROM TBL_MEMBER;

