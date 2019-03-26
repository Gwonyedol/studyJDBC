/*
 DML(insert, update, delete)
 JDBC API ���ؼ� �۾�
 1. ��� ������ ����
 2. �ݿ����( return ���� �� )
 
 JAVA �����ڵ�
 update emp set sal = 0 >> 14�� update >> return 14
 update emp set sal = 100 where empno = 4444 >> return 0
 
 JAVA �ڵ� ���� : ������ ����...
 KEY POINT
 1.Oracle DML �۾�(developer , cmd, Tool) �ϸ� commit or rollback ����
 2.JDBC API ����ϴ� Java �ڵ�� >> DML >> default : autocommit
 3.JAVA CODE : delete from emp >> ���� >> �ڵ� commit >> �ǹݿ�
 4.�ʿ信 ���󼭴� commit(), rollback() JAVA�ڵ忡�� ���� ����
 
 ���� 
 	A ���� ����(update...
 	..
 	B ���� �Ա�(update...
 	
 ��
>> �ϳ��� ���� ������ ������Ѵ� (Transaction)
>>autocommit �ɼ� >> false ��ȯ
>>Java code DML �۾��� �ݵ�� >> commit(), rollback() ���� ȣ��


--sqldeveloper ����...--
create table dmlemp
as
  select*from emp;
select*from dmlemp;

alter table dmlemp
add constraint pk_dmlemp_empno primary key(empno);

select*from SYS.USER_CONSTRAINTS where TABLE_NAME = 'DMLEMP';
 
 
 */
public class Ex03_Oracle_DML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
