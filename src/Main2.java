import dao.DaoFactory;
import dao.DepartmentDao;
import entities.Department;
import entities.Department;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(); // por que?
        System.out.println("=== Test 1: Department findById ===");
        Department department = departmentDao.findById(3);
        System.out.println(department);
        System.out.println();

        /*System.out.println("=== Test 2: Department findByDepartment ===");
        Department department = new Department(2, null);
        List<Department> list = DepartmentDao.findByDepartment(department);

        for (Department obj: list) {
            System.out.println(obj);
        }
        System.out.println();*/

        System.out.println("=== Test 3: Department findAll ===");
        List<Department> listAll = departmentDao.findAll();
        for (Department obj: listAll) {
            System.out.println(obj);
        }
        System.out.println();

        System.out.println("=== Test 4: Department insert ===");
        Department newDepartment = new Department(7, "Computerss");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());
        System.out.println();

        System.out.println("=== Test 5: Department update ===");
        department = departmentDao.findById(1);
        department.setName("Tvss");
        departmentDao.update(department);
        System.out.println("Update completed!");
        System.out.println();

        System.out.println("=== Test 6: Department deleteById ===");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id for delete test: ");
        Integer idForDelete = sc.nextInt();
        departmentDao.deleteById(idForDelete);
        System.out.println("Delete completed!");
        System.out.println();

    }
}
