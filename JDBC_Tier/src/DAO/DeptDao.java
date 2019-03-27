package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import Utils.SingletonHelper;

//DAO
//1. DB ����
//2. CRUD (�Լ�)
//3. 5���� method(select ALL,  select where , insert ,update, delete)
public class DeptDao {

	//DAO ���� 
	//1. DB ����
	//2. CRUD �۾�
	//3. 5���� method(select ALL, select where, insert, update, delete)
	
	
	// 1. ��ü��ȸ >> select ��� >> return multi row
	public List<Dept> getDeptAllList() {
		// �������� ������ ��� Ŭ����
		List<Dept> deptlist = new ArrayList<Dept>();
		// deptlist.add(new Dept())
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname, loc from dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Dept dept = new Dept(); // �ϳ��� row ��� ���� ��ü
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				deptlist.add(dept); // �迭�� ��ü ��� ��
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return deptlist;
	}

	// 2. ������ȸ >> select ���(where deptno=?) >> return single row
	public Dept getDeptListByDeptno(int deptno) {
		// select deptno , dname , loc from dept where deptno=?

		Dept dept = null; //

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname, loc from dept where deptno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dept = new Dept(); // �ϳ��� row ��� ���� ��ü
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return dept;
	}

	// 3. ������ ����
	// public int insertDept(int deptno , String dname , String loc) ..(x)
	public int insertDept(Dept dept) {
		//insert into dept(deptno, dname, loc) values(?,?,?)
		 Connection conn = null;
		 PreparedStatement pstmt=null;
		 int rowcount = 0;
		 
		 try {
			 conn = SingletonHelper.getConnection("oracle");
			 String sql="insert into dept(deptno,dname,loc) values(?,?,?)";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, dept.getDeptno());
			 pstmt.setString(2, dept.getDname());
			 pstmt.setString(3, dept.getLoc());
			 rowcount = pstmt.executeUpdate(); 
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }finally {
			 SingletonHelper.close(pstmt);
		 }
		 
		 return rowcount;
	}

	// 4. ������ ����
	public int updateDept(Dept dept) {
		//update dept set dname=? , loc=? where deptno=?
		Connection conn = null;
		 PreparedStatement pstmt=null;
		 int rowcount = 0;
		 
		 try {
			 conn = SingletonHelper.getConnection("oracle");
			 String sql="update dept set dname=? , loc=? where deptno=?";
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, dept.getDname());
			 pstmt.setString(2, dept.getLoc());
			 pstmt.setInt(3, dept.getDeptno());
			 
			 rowcount = pstmt.executeUpdate(); 
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }finally {
			 SingletonHelper.close(pstmt);
		 }
		 
		 return rowcount;
	}

	// 5. ������ ����
	public int deleteDept(int deptno) {
		//delete from dept where deptno=?
		Connection conn = null;
		 PreparedStatement pstmt=null;
		 int rowcount = 0;
		 
		 try {
			 conn = SingletonHelper.getConnection("oracle");
			 String sql="delete from dept where deptno=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, deptno);
			 rowcount = pstmt.executeUpdate();
		 }catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }finally {
			 SingletonHelper.close(pstmt);
		 }
		 
		 return rowcount;
	}
}
