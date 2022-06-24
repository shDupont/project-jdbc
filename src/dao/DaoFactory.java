package dao;

import dao.impl.SellerDaoImplJDBC;
import db.DB;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoImplJDBC(DB.getConnection());
    }
}
