import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * JDBC
 * 1.Java ���(App)�� ���ؼ� Oracle(����Ʈ����) �����ϰ� CRUD�۾�
 * 2.JAVA APP �ǻ����(� RDBMS ���):Oracle, My-sql, Ms-sql
 * 2.1 �����ͱ� ����:��ǻ��-�����ͱ� ����
 * Case 1 : ���� ���� (����̹��� ��ǻ�ͳ��� ����Ǿ�����)
 * Case 2 : �������� ���� >> ������ ȸ�� ����Ʈ >> ����̹� �ٿ�ε� >> ����
 * 2.2 My-sql(https://dev.mysql.com/downloads/file/?id=484819) zip �ٿ�ε� �� ���� ǰ
 * 2.3 Oracle(C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib)
 * 	   >>ojdbc6.jar
 * 3.����̹� ���� ���(�۾��ϴ� ������Ʈ ����...) (Java Project -> ����̹� ����)
 * 3.1 java >> �Ӽ� >> build path >> jar �߰�
 * 3.2 ����̹� ��� �غ� �Ϸ� >> �޸𸮿� Load (new)
 * 3.3 class.forName("Ŭ���� �̸�") >> new ������ ȿ��
 * 
 * 4.JAVA CODE ( JDBC API )
 * 4.1 import java.sql.* >> �Ʒ��� interface, class ����
 * 4.2 �����ڴ� interface �ؼ��� ���ؼ� �۾�
 * POINT ( why : interface ���·� ���� >> JDBC API : oracle, mysql.. >> )
 * oracle �絵 JDBC API �� ����.. (����̹�)
 * mysql JDVC API ����... (����̹�)
 * >>������ ���...
 * import java.sql.Connection
 * import java.sql.ResultSet
 * import java.sql.Statement
 * 
 * 5.�۾� ����
 * 5.1 DB ���� -> ��� -> ���� -> ó�� -> �ڿ� ����
 * 5.2 ��� : DDL(create, drop, alter) 
 * 			CRUD(insert,select,update,delete)
 * 5.3 ���� : DB �������� ����(����)
 * 5.4 ó�� : ȭ�� ���, �ٸ����� ���� �����ڰ� ���� ����
 * 5.5 �ڿ� ����(����)
 * 
 * TIP) ���� ���ڿ�(connectionString) ����
 * ��Ʈ��ũ�� ���ؼ� DB ����
 * (DB���� IP, PORT(1521,3306),SID(����DB:xe, orcl), ���Ӱ���, ���Ӻ��
 * 
 */

public class Ex01_Oracle_Connection {

	public static void main(String[] args) throws Exception {
		//DB����

		Class.forName("oracle.jdbc.OracleDriver");
        System.out.println("����Ŭ ����̹� �ε�");
        
        //getConnection Connection ������ ��ü�� �ּҰ��� ����
        Connection conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bituser", "1004");
        System.out.println(conn.isClosed()+ "���� (False)");
        
        Statement stmt = conn.createStatement(); //��� ��ü ��� ����
        
        //CRUD
        String sql = "select empno, ename, sal from emp";
        ResultSet rs = stmt.executeQuery(sql); //�������� ��ȯ �ޱ�
        
        
        //ó��(ȭ����ȸ)
        while(rs.next()) {
        	System.out.println(rs.getInt("empno") + "/" +
        					   rs.getString("ename") + "/" +
        					   rs.getInt("sal")); 
        }
        rs.close();
        conn.close();
        stmt.close();
        System.out.println("DB ���� : "+conn.isClosed());
        
        
    }
}

