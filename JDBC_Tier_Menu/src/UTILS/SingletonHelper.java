package UTILS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class SingletonHelper {
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn = null;
	private SingletonHelper() {}
	public static Connection getConnection(String dsn) {//oracle, mysql
		if(conn == null) {
			try {
				if(dsn.equals("oracle")) {
					Class.forName("oracle.jdbc.OracleDriver");
					conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bituser","1004");
				}else if (dsn.equals("mysql")) {
					
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return conn;
	}
	public static Connection getConnection(String dsn, String id, String pwd) {//oracle, mysql
		if(conn==null) {	
			try {
				if(dsn.equals("oracle")) {
					Class.forName("oracle.jdbc.OracleDriver");
					conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",id,pwd);
				}else if (dsn.equals("mysql")) {
					
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return conn;
	}
	public static void menu() {
		System.out.println("**********************");
		System.out.println( "1. �μ������ȸ");
		System.out.println( "2. �μ����");
		System.out.println( "3. �μ�����");
		System.out.println( "4. �μ�����");
		System.out.println( "5. �μ��˻�");
		System.out.println( "6. ����");
		}
	public static int inputNum() {
		
		int selectNum = 0;
		System.out.println("**********************");
		System.out.print("�۾���ȣ	���� :");
		selectNum = Integer.parseInt(sc.nextLine());
		
		return selectNum;
	}
}