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
				System.out.println("조회된 결과가 없습니다.");
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
 * 메뉴구성 1. 부서목록조회 (전체조회) 2. 부서등록 3. 부서변경 4. 부서삭제 5. 부서검색 (where dname like
 * '%A%') 6. 종료
 * 
 * private static String menu(){ 1. 부서목록조회 2. 부서등록 3. 부서변경 4. 부서삭제 5. 부서검색 6. 종료
 * 
 * }
 **********************
 * 
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:1
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:2 부서코드:10 부서이름:IT INSERT ROW : 1 INSERT DATA : 10 : IT
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:1 [10][IT]
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:2 부서코드:20 부서이름:MANAGER INSERT ROW : 1 INSERT DATA : 20 : MANAGER
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:1 [10][IT] [20][MANAGER]
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:3 변경할 부서명 :MANAGER Department [deptno=20, dname=MANAGER] [부서 변경 정보 입력]
 * 부서코드:20 부서이름:SALES 변경된 ROW : 1
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:1 [10][IT] [20][SALES]
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:3 변경할 부서명 :IT Department [deptno=10, dname=IT] [부서 변경 정보 입력] 부서코드:10
 * 부서이름:ITMANAGER 변경된 ROW : 1
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:5 검색할 부서명:I Department [deptno=10, dname=ITMANAGER]
 **********************
 * 1.부서목록 2.부서등록 3.부서변경 4.부서삭제 5.부서검색 6.프로그램종료
 **********************
 * 작업번호선택:6 프로그램 종료
 */