import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

/*
 * 1.����̹� ����(jar �߰�)
 * 2.����̹� �ε�(JVM : Class.forName())
 * 3.���ᰴü���� -> DriverManager Ŭ����
 * 4.��ɰ�ü���� -> CRUD �۾� �غ�(Statement)
 * 5.��� ���� -> DQL(select 1��, select multi row) => return ResultSet ��ü
 * 		      DML(insert,update,delete) =>��� ������ ������ ����
 * 6.��� ó�� (2����) : DQL(ȭ�����), DML(����� ���� ó�� (����?����?))
 * 7.�ڿ� ����
 * 
 * 
 * JDBC API (�������̽���) : ǥ��ȭ�� �ڵ� ��� (���������)
 * Connection
 * Statement
 * PrepareStatement
 * ResultSet
 */
public class Ex02_MariaDB_Connection {

	public static void main(String[] args) {
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			//2.����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MariaDB");
			
			//3.���ᰴü ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=true&serverTimezone=UTC","root","0916");
			System.out.println(conn.isClosed());
			
			//4.��ɰ�ü ����
			stmt = conn.createStatement();
			
			//4.1���๮��
			String job = "";
			Scanner sc = new Scanner(System.in);
			System.out.println("�����Է�");
			job = sc.nextLine();
			//Where job = 'ClERK'
			String sql = "select empno, ename, job from emp where job = '"+ job + "'";
			
			//5.��ɽ���
			//DQL (select) > stmt.executeQuery(sql) > return ResultSet ��ü�� �ּ�
			//DML(inset,delete,update) > �������(x) > �ݿ����(�ݿ������Ǽ�)
			//stmt.executeUpdate() : >> return �ݿ��� ���� ��
			//delete from emp; >> 14����
			
			rs = stmt.executeQuery(sql);
			
			//6.���ó��
			//DQL : 1. ����� ���� ��� (where empno = 9999)
			//      2. ����� 1���� ��� (PK,Unique �ɷ��ִ� �÷�: where empno =7788)
			//      3. ����� �������� row >> select* from emp where deptno = 20
			
			
			
			//1.�����ϴ�(�ܼ�)
			//2.��� ������ ���°�� ����ó���� �ȵ�
//			while(rs.next()) {
//				System.out.println(rs.getInt("empno")+","+
//								   rs.getString("ename")+","+
//								   rs.getString("job"));
//			}
//				
//			//1.����� �ִ� ��� ���� ��� ó�� ����
//			//2.Multi row ��� �Ұ���
//			if(rs.next()) {
//				System.out.println(rs.getInt("empno")+","+
//						   rs.getString("ename")+","+
//						   rs.getString("job"));
//			} else {
//				System.out.println("��ȸ�� �����Ͱ� ����");
//			}
//			
			//�ΰ��� ������ ��ģ ������ ����� ������
			//1. ��� ������ ���� ��� ó�� ����
			//2. Single row Read
			//3. Multi row Read
			//�� 3������ �����ϴ� �ڵ� ����
			

			
			if(rs.next()) {
				do {
					System.out.println(rs.getInt("empno")+","+
							   rs.getString("ename")+","+
							   rs.getString("job"));
				} while (rs.next());

		} else {
			System.out.println("��ȸ�� �����Ͱ� ����");
		}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

		
		
		
	}

}
