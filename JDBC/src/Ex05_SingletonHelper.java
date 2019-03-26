import java.sql.Connection;
import java.sql.SQLException;

import kr.or.bit.utils.ConnectionHelper;
import kr.or.bit.utils.SingletonHelper;

public class Ex05_SingletonHelper {

	public static void main(String[] args) throws SQLException {
		//Singleton ���
		//��ü�� ���� �ڿ� (conn ��ü)
		//�����ڿ����� ��ȭ�� �ٸ� ��� �� ��ȭ ...
		
		//conn.close(); ���� ���� 
		//conn ��ü�� �ּҰ��� null������ ���� ���� �ƴϴ�
		//APP ����ɋ����� singleton >> conn �� �������� ���� �ʴ´�
		
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductVersion());
		System.out.println(conn.isClosed());
		//ConnectionHelper.close(conn);
		//System.out.println("���Ῡ�� : " + conn.isClosed());
		
		//�ٽ� ����
		Connection conn2 = null;
		conn2 = SingletonHelper.getConnection("oracle");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductVersion());
		System.out.println(conn2.isClosed());
		//ConnectionHelper.close(conn2);
		//System.out.println("���Ῡ�� : " + conn2.isClosed());
	
		/*
		 ���� �̱��� ...
		 oracle.jdbc.driver.T4CConnection@4aa8f0b4
		 oracle.jdbc.driver.T4CConnection@4aa8f0b4
		 */
		SingletonHelper.dbClose();
		Connection myconn = null;
		myconn = SingletonHelper.getConnection("mysql");
		System.out.println(myconn.toString());
		System.out.println(myconn.getMetaData().getDatabaseProductName());
		System.out.println(myconn.getMetaData().getDatabaseProductVersion());
		System.out.println(myconn.isClosed());

	}

}



