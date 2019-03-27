//DAO
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DTO.Department;
import UTILS.SingletonHelper;

public class DepartmentDao {
	
	public List<Department> departmentList() {
		List<Department> deptList = new ArrayList<Department>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname from department";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					Department department = new Department();
					department.setDeptno(rs.getInt("deptno"));
					department.setDname(rs.getString("dname"));
					deptList.add(department);
				}
			}else {
				System.out.println("��ȸ�� ����� �����ϴ�.");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return deptList;
	}
	
	public int departmentInsert(Department department) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount=0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into department(deptno, dname) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, department.getDeptno());
			pstmt.setString(2, department.getDname());
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	public int departmentUpdate(Department department, String dname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount=0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update department set deptno=? , dname = ? where dname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, department.getDeptno());
			pstmt.setString(2, department.getDname());
			pstmt.setString(3, dname);
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	public int departmentDeleteByDeptno(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount=0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete department where deptno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	public Department departmetnListByDname(String dname) {
		Department department = new Department();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname from department where dname like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+dname+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				department.setDeptno(rs.getInt("deptno"));
				department.setDname(rs.getString("dname"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return department;
	}
	
	
	
}

/*
 * �޴����� 1. �μ������ȸ (��ü��ȸ) 2. �μ���� 3. �μ����� 4. �μ����� 5. �μ��˻� (where dname like
 * '%A%') 6. ����
 * 
 * private static String menu(){ 1. �μ������ȸ 2. �μ���� 3. �μ����� 4. �μ����� 5. �μ��˻� 6. ����
 * 
 * }
 **********************
 * 
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:1
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:2 �μ��ڵ�:10 �μ��̸�:IT INSERT ROW : 1 INSERT DATA : 10 : IT
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:1 [10][IT]
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:2 �μ��ڵ�:20 �μ��̸�:MANAGER INSERT ROW : 1 INSERT DATA : 20 : MANAGER
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:1 [10][IT] [20][MANAGER]
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:3 ������ �μ��� :MANAGER Department [deptno=20, dname=MANAGER] [�μ� ���� ���� �Է�]
 * �μ��ڵ�:20 �μ��̸�:SALES ����� ROW : 1
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:1 [10][IT] [20][SALES]
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:3 ������ �μ��� :IT Department [deptno=10, dname=IT] [�μ� ���� ���� �Է�] �μ��ڵ�:10
 * �μ��̸�:ITMANAGER ����� ROW : 1
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:5 �˻��� �μ���:I Department [deptno=10, dname=ITMANAGER]
 **********************
 * 1.�μ���� 2.�μ���� 3.�μ����� 4.�μ����� 5.�μ��˻� 6.���α׷�����
 **********************
 * �۾���ȣ����:6 ���α׷� ����
 */