import java.sql.Connection;
import java.sql.SQLException;

import kr.or.bit.utils.ConnectionHelper;

public class Ex04_ConnectionHelper {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		conn = ConnectionHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductVersion());
		System.out.println(conn.isClosed());
		ConnectionHelper.close(conn);
		System.out.println("���Ῡ�� : "+ conn.isClosed());
		
		//1 return : oracle.jdbc.driver.T4CConnection@4aa8f0b4
		//2 reutrn : oracle.jdbc.driver.T4CConnection@1c3a4799
		//result : getConnection() �Լ� ȣ��ø��� ���ο� ��ü ����
		//�ϳ��� APP ���� ��ü �ϳ��� ����ص� ���� ������..
		//�ѹ����� ����
		
		Connection myconn = null;
		myconn = ConnectionHelper.getConnection("oracle");
		System.out.println(myconn.toString());
		System.out.println(myconn.getMetaData().getDatabaseProductName());
		System.out.println(myconn.getMetaData().getDatabaseProductVersion());
		System.out.println(myconn.isClosed());
		ConnectionHelper.close(myconn);
		System.out.println("���Ῡ�� : "+ myconn.isClosed());

	}

}
