//program main
import java.util.List;
import java.util.Scanner;

import DAO.DepartmentDao;
import DTO.Department;
import UTILS.SingletonHelper;

public class program {

	public static void main(String[] args) {
		DepartmentDao dao = new DepartmentDao();
		Scanner sc = new Scanner(System.in);
		int selectNum;
		String inputDname;
		int inputDeptno;
		while(true) {
			SingletonHelper.menu();
			selectNum = SingletonHelper.inputNum();
			
			switch(selectNum) {
				case 1 : List<Department> departmentList =dao.departmentList();
					System.out.println(departmentList);
					break;
				case 2 : 
					Department department = new Department();	
					System.out.print("부서코드 : "); 
					inputDeptno = Integer.parseInt(sc.nextLine());
					department.setDeptno(inputDeptno);
					System.out.print("부서이름 : ");
					inputDname = sc.nextLine();
					department.setDname(inputDname);
					int rowcount = dao.departmentInsert(department);
					System.out.println("반영된 행의 수 : " + rowcount);
					System.out.println("반영된 데이터 : "+ inputDeptno +"/"+inputDname );
					break;
				case 3 : 
					departmentList =dao.departmentList();
					department = new Department();	
					System.out.print("변경할 부서명 : ");
					String dname = sc.nextLine();
					for(int i = 0; i < departmentList.size() ; i++) {
						if(departmentList.get(i).getDname().equals(dname)){
							System.out.println(departmentList.get(i).toString());
						}
					}
					System.out.println("[부서 변경 정보 입력]");
					System.out.print("부서 코드 : ");
					inputDeptno = Integer.parseInt(sc.nextLine());
					department.setDeptno(inputDeptno);
					System.out.print("부서 이름 : ");
					inputDname = sc.nextLine();
					department.setDname(inputDname);
					rowcount = dao.departmentUpdate(department, dname);
					System.out.println("반영된 행의 수 : " + rowcount);
					
					break;
				case 4 : 
					System.out.print("삭제할 부서의 부서 코드 : ");
					inputDeptno = Integer.parseInt(sc.nextLine());
					rowcount = dao.departmentDeleteByDeptno(inputDeptno);
					System.out.println("반영된 행의 수 : " + rowcount);
					break;
				case 5 :
					System.out.print("검색할 부서명 : ");
					department = new Department();
					inputDname = sc.nextLine();
					department = dao.departmetnListByDname(inputDname);
					System.out.println(department);
					break;
				case 6 :
					return;
				default : System.out.println("올바르지 않은 값입니다.");
			}
		}
	
	}

}