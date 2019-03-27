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
trans_A, trans_B �ϳ��� ������ ���� (transaction ó��)
JDBC >> autocommit >> false >> �����ڴ� �ݵ�� >> commit , rollback




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
			conn.setAutoCommit(false); // APP commit, rollback ����
			//begin tran
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.executeQuery();
			//end tran
			conn.commit(); // �Ѵ� ���������� ����ǰ� ���ܰ� �߻����� ������ �Ѵ� �ǹݿ�!
		} catch (Exception e) {
			//����ó�� �κп��� ������ ����
			System.out.println("�����߻� : "+e.getMessage());
			conn.rollback(); //�ΰ��� �۾��� ��� ���
		} finally {
			
			SingletonHelper.close(pstmt);
			SingletonHelper.close(pstmt2);
			
		}
		
		
		
	}

}
