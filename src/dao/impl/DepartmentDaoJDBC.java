package dao.impl;

import dao.DepartmentDao;
import db.DB;
import db.DbException;
import entities.Department;
import entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;
    public DepartmentDaoJDBC(Connection conn){
        this.connection = conn;
    }
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO department\n" +
                            "(Name)\n" +
                            "VALUES\n" +
                            "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "UPDATE department\n" +
                            "SET Name = ? \n" +
                            "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM department\n" +
                    "WHERE Id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE department.Id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Department dep = new Department();

                dep.setId(resultSet.getInt("Id"));
                dep.setName(resultSet.getString("Name"));
                return dep;
            }
            return null;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department");
            resultSet = preparedStatement.executeQuery();

            List<Department> list = new ArrayList<>();
            while (resultSet.next()){
                Department obj = new Department();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
