import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

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
		Connection conn = null;
		Statement stmt = null;

		try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bituser", "1004");
            stmt = conn.createStatement();

            // INSERT
//            int empno = 0;
//            String ename = "";
            int deptno = 0;
//            int sal = 0;

            Scanner sc = new Scanner(System.in);
//            System.out.println("����Է� : ");
//            empno = Integer.parseInt(sc.nextLine());
//
//            System.out.println("�̸��Է� : ");
//            ename = sc.nextLine();

            System.out.println("�μ���ȣ�Է� : ");
            deptno = Integer.parseInt(sc.nextLine());
            
            //UPDATE
            //UPDATE dmlemp set sal = 0 where deptno = �μ���ȣ
//            System.out.println("�����Է� : ");
//            sal = Integer.parseInt(sc.nextLine());

            // ��������� �ĸ´��ڵ�
            String sql = "update dmlemp set sal = 0 where deptno = " + deptno;
//            sql += "values(" + empno + ",'" + ename + "'," + deptno + "'," + sal + ")" ;

            // ����) parameter
            // values(?,?,?);
            //executeUpdate() > insert, update, delete
            
            //DELETE
            //delete from dmlemp where deptno = �μ���ȣ
//          System.out.println("�����Է� : ");
//          deptno = Integer.parseInt(sc.nextLine());
            
            int resultrowcount = stmt.executeUpdate(sql);
            if(resultrowcount > 0) {
                System.out.println("�ݿ��� ���� �� : " + resultrowcount);
            } else {
                //���ܰ� �ƴϰ� ... �ݿ��� ���� ����(���ܴ� ��񿬰��� �ȵǰų� ��������)
                System.out.println("Insert Fail : " + resultrowcount);
            }
            
            
            

        } catch (Exception e) {
            System.out.println("SQL ���ܹ߻� : " + e.getStackTrace());
        } finally {
            if (stmt != null) try {stmt.close();} catch (Exception e) {}
           if (conn != null) try {conn.close();} catch (Exception e) {}
        }
    }
}