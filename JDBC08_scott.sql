SELECT USER
FROM DUAL;
--==>>SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	��ȣ��	010-1111-1111
2	������	010-1234-1234
3	������	010-5555-2222
4	�蹮��	010-4444-4444
5	���̻�	010-2222-2222
6	���¸�	010-6666-6666
7	������	010-4444-2222
10	���ϸ�	010-4343-4343
11	���¹�	010-5555-5555
12	����	010-4444-4444
13	������	010-5555-5555
14	������	010-6666-6666
*/


TRUNCATE TABLE TBL_MEMBER;
--==>>Table TBL_MEMBER��(��) �߷Ƚ��ϴ�.
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>> ��ȸ ��� ����.



--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� ��   : PRC_MEMBERINSERT
--   ���ν��� ��� : TBL_MEMBER ���̺� �����͸� �Է��ϴ� ���ν���
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
(VNAME IN TBL_MEMBER.NAME%TYPE
,VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- �ֿ� ���� ����
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    -- ���� SID �� �ִ밪 ������
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;
    
    -- ������ �Է�(INSERT ������ ����)
    INSERT INTO TBL_MEMBER(SID,NAME,TEL)
    VALUES((VSID+1),VNAME,VTEL);
    
    --Ŀ��
    COMMIT;
END;
--==>>Procedure PRC_MEMBERINSERT��(��) �����ϵǾ����ϴ�.

--�� ������ ���ν��� �׽�Ʈ(Ȯ��)
EXEC PRC_MEMBERINSERT('��ȣ��','010-1111-1111');
--==>>PL/SQL ���ν����� ���������� �Ϸ�Ǿ����ϴ�.

--�� ���̺� ��ȸ
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--==>>1	��ȣ��	010-1111-1111



--�� JDBC08 �� Test001 ���� �� Ȯ��
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
/*
1	��ȣ��	010-1111-1111
2	������	010-2222-2222
3	���¹�	010-3333-3333
4	������	010-4444-4444
*/




--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� ��   : PRC_MEMBERSELECT
--   ���ν��� ��� : TBL_MEMBER ���̺��� �����͸� �о���� ���ν���
--   �� ��SYS_REFCURSOR�� �ڷ����� �̿��Ͽ� Ŀ�� �ٷ��
--       �̹� ������ Ŀ���� ���� ���صδ�.
--   ������� ���� ��Ƽ� �ǳ��� �� IN
--   ����� �ٰ� ä���� �� �� OUT
--   INOUT�� ����.
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT OUT SYS_REFCURSOR
)
IS
    -- �ֿ� ���� ����
BEGIN
    OPEN VRESULT FOR
        SELECT SID,NAME,TEL
        FROM TBL_MEMBER
        ORDER BY SID;
    -- CLOSE VRESULT; 
    -- ������ JAVA���� ������
    
    -- COMMIT;
    -- SELECT �� �����̱� ������ COMMIT �� �ʿ����.
END;
--==>>Procedure PRC_MEMBERSELECT��(��) �����ϵǾ����ϴ�.


--�� ���ν��� ���� �� �׽�Ʈ(Ȯ��)
EXEC PRC_MEMBERSELECT;
















