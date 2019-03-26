package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/*
 ��ü ������Ʈ (ȸ�� : ��ü ��ȸ, ���� ��ȸ, ����, ���� ������ ����)
 ������ �������� ���������� �ʿ��� ���
 1. ����̹� �ε�
 2. ���ᰴü, ���, �ڿ�����...
 
 3. �������� �ڵ带 �����ؼ� ���´�!
 4. Oracle, mysql ��� �ϵ� �Ѵ� ������ �� �ִ� �ڵ尡 �ִٸ�..
 5. �������� ������ ������ Ŭ���� ����
 6. ConnectionHelper ���� >> ���(method) ���� ��� >> static >> overloading >> ������ Ȯ��
 7. ���� >> �ϳ��� ��ü ���� >> Singleton
 */
public class ConnectionHelper {
	//�Լ� parameter Ư�� ���ڿ�(oracle, mysql)
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");
		       conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bituser", "1004");
			}else if(dsn.equals("mysql")) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=true&serverTimezone=UTC","root","0916");
			}
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	
	public static Connection getConnection(String dsn, String id, String pwd) {
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");
		        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", id, pwd);
			}else if(dsn.equals("mysql")) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=true&serverTimezone=UTC","root","0916");
			}
		} catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
		
//        	if (rs != null) try {rs.close();} catch (Exception e) {}
//        	if (stmt != null) try {rs.close();} catch (Exception e) {}
//        	if (conn != null) try {rs.close();} catch (Exception e) {}
		}
		
		return null;
	}
	
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
