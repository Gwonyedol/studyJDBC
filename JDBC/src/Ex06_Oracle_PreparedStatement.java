import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.or.bit.utils.SingletonHelper;

/*
 Statement stmt = conn.createStatement(); ���� �ּҰ��� ����
 stmt.excuteQuery("select*from emp where empno = 7788") ����
 
 ����: �������� �ٲ㼭 ������ �����ϴ�
 
 
 PreparedStatement (�غ�� Statement)
   (1) ���� 
     �̸� SQL���� ���õ� Statement �� DB�� ���۵Ǿ�����   �����ϵǾ�����, SQL���� parameter�� ���߿� �߰� �����ؼ� ���� 
    �� �Ǿ����� �غ�� Statement 
  (2) ���� 
     <1> Statement �� ���ؼ� �ݺ����� SQL���� ����� ��쿡  �� ������. 
     ( Ư��, �˻��� ) : query ���� DB������ ������ ������ �س���.. �����Ҷ����� ?(parameter)�� ������.
  <2> DB�÷�Ÿ�԰� ������� parameter�ϳ��� ǥ���ϸ� �ǹǷ�   �����ڰ� ����� �ʰ� ����. ( Ư��, INSERT�� )
       (����: parameter�� ������ SQL���� DB���� �̸� �����ϵǾ����� ���)

  (3) ���� 
     SQL������ PreparedStatement ��ü�� ���� �����ؾ� �ϹǷ� ����Ұ�
     (but, Statement ��ü�� SQL���� �޶������� �� ���� �����ؼ�  ������ �����ϴ�. )

  (4) Ư¡ 
     <1> Statement stmt = con.createStatement(); //����       stmt.execute(sql);//����
  <2> PreparedStatement pstmt = con.prepareStatement(sql); //����      pstmt.execute(); //����

  (5) ���� 
      DB ��ü��(table, ..)�� ����(   ���̺�� or �÷��� or �������� ���� ��ü�� �Ӽ���)�� 
      ?(parameter��) ǥ���� �� ����.  ��, data �ڸ����� ?�� ǥ���� �� �ִ�.
 	  cf) �׷���, DDL �������� PreparedStatement�� ������� �ʴ´�.
 	  
 	  ����: ����, �̸� ������ ������ -> parameter�� ���� (��Ʈ��ũ Ʈ���� ����)
 	  ����: �������� ���� �Ұ�
 	  
 
 */
public class Ex06_Oracle_PreparedStatement {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename from emp where deptno=?";
			// where id? and name=?, and job=?
			// ? ������ŭ�� ����...
			
			//Statement stmt = con.createSTatement();
			
			
			//������� ����� (���� X)
			pstmt = conn.prepareStatement(sql); // �������� oracle ������ ������...
			
			//���Ŀ��� parameter ������ �����ؼ� ����
			pstmt.setInt(1, 30); //deptno=? >> deptno = 30
			
			//stmt.executeQuery(Sql)
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					System.out.println(rs.getInt(1) + "/" + rs.getString(2));
					
					
				} while(rs.next());
			} else {
				System.out.println("��ȸ�� �����Ͱ� �����");
			}
			
			
		} catch (Exception e) { 
			
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
//			SingletonHelper.close(conn); APP�� ����ִ� �� Static�� ������� ����
			}
		}
}
