import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.bit.utils.SingletonHelper;

/* 
 bituser
 create table trans_A(
  num number,
  name varchar2(20)
);
create table trans_B(
  num number constraint pk_tran_B_num primary key,
  name varchar2(20)
);


JDBC >> default(dml) >> autocommit
trans_A, trans_B 하나의 논리적인 단위 (transaction 처리)
JDBC >> autocommit >> false >> 개발자는 반드시 >> commit , rollback




 */
public class Ex08_Oracel_Transaction {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method s

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		conn = SingletonHelper.getConnection("oracle");
		
		String sql = "insert into trans_A(num,name) values(100,'A')";
		String sql2 = "insert into trans_B(num,name) values(100,'B')";
		
		try {
			conn.setAutoCommit(false); // APP commit, rollback 강제
			//begin tran
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.executeQuery();
			//end tran
			conn.commit(); // 둘다 정상적으로 실행되고 예외가 발생하지 않으면 둘다 실반영!
		} catch (Exception e) {
			//에외처리 부분에서 오류가 난다
			System.out.println("문제발생 : "+e.getMessage());
			conn.rollback(); //두개의 작업을 모두 취소
		} finally {
			
			SingletonHelper.close(pstmt);
			SingletonHelper.close(pstmt2);
			
		}
		
		
		
	}

}
