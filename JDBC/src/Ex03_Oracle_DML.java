import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/*
 DML(insert, update, delete)
 JDBC API 통해서 작업
 1. 결과 집합이 없다
 2. 반영결과( return 행의 수 )
 
 JAVA 구현코드
 update emp set sal = 0 >> 14건 update >> return 14
 update emp set sal = 100 where empno = 4444 >> return 0
 
 JAVA 코드 로직 : 성공과 실패...
 KEY POINT
 1.Oracle DML 작업(developer , cmd, Tool) 하면 commit or rollback 강제
 2.JDBC API 사용하는 Java 코드는 >> DML >> default : autocommit
 3.JAVA CODE : delete from emp >> 실행 >> 자동 commit >> 실반영
 4.필요에 따라서는 commit(), rollback() JAVA코드에서 제어 가능
 
 시작 
 	A 계좌 인출(update...
 	..
 	B 계좌 입금(update...
 	
 끝
>> 하나의 논리적 단위로 묶어야한다 (Transaction)
>>autocommit 옵션 >> false 전환
>>Java code DML 작업시 반드시 >> commit(), rollback() 강제 호출


--sqldeveloper 실행...--
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
//            System.out.println("사번입력 : ");
//            empno = Integer.parseInt(sc.nextLine());
//
//            System.out.println("이름입력 : ");
//            ename = sc.nextLine();

            System.out.println("부서번호입력 : ");
            deptno = Integer.parseInt(sc.nextLine());
            
            //UPDATE
            //UPDATE dmlemp set sal = 0 where deptno = 부서번호
//            System.out.println("월급입력 : ");
//            sal = Integer.parseInt(sc.nextLine());

            // 쓰면뒤지게 쳐맞는코드
            String sql = "update dmlemp set sal = 0 where deptno = " + deptno;
//            sql += "values(" + empno + ",'" + ename + "'," + deptno + "'," + sal + ")" ;

            // 실제) parameter
            // values(?,?,?);
            //executeUpdate() > insert, update, delete
            
            //DELETE
            //delete from dmlemp where deptno = 부서번호
//          System.out.println("월급입력 : ");
//          deptno = Integer.parseInt(sc.nextLine());
            
            int resultrowcount = stmt.executeUpdate(sql);
            if(resultrowcount > 0) {
                System.out.println("반영된 행의 수 : " + resultrowcount);
            } else {
                //예외가 아니고 ... 반영된 행이 없다(예외는 디비연결이 안되거나 했을때나)
                System.out.println("Insert Fail : " + resultrowcount);
            }
            
            
            

        } catch (Exception e) {
            System.out.println("SQL 예외발생 : " + e.getStackTrace());
        } finally {
            if (stmt != null) try {stmt.close();} catch (Exception e) {}
           if (conn != null) try {conn.close();} catch (Exception e) {}
        }
    }
}