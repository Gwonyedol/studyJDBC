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
		System.out.println( "1. 부서목록조회");
		System.out.println( "2. 부서등록");
		System.out.println( "3. 부서변경");
		System.out.println( "4. 부서삭제");
		System.out.println( "5. 부서검색");
		System.out.println( "6. 종료");
		}
	public static int inputNum() {
		
		int selectNum = 0;
		System.out.println("**********************");
		System.out.print("작업번호	선택 :");
		selectNum = Integer.parseInt(sc.nextLine());
		
		return selectNum;
	}
}