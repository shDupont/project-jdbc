package dao;

import dao.impl.DepartmentDaoJDBC;
import dao.impl.SellerDaoImplJDBC;
import db.DB;
import entities.Department;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoImplJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
