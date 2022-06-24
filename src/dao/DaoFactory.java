package dao;

import dao.impl.SellerDaoImplJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoImplJDBC();
    }
}
