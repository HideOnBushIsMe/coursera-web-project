package sample.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sample.models.Category;
import sample.utils.DBUtils;

public class CategoryDAO {

    public List<Category> getAllCategories() throws ClassNotFoundException, SQLException {
        List<Category> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT typeId, categoryName, memo FROM categories";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Category(
                            rs.getInt("typeId"),
                            rs.getNString("categoryName"),
                            rs.getNString("memo")
                    ));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }

    public boolean insertCategory(Category category) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO categories(categoryName, memo) VALUES(?,?)";
                ptm = conn.prepareStatement(sql);
                ptm.setNString(1, category.getCategoryName());
                ptm.setNString(2, category.getMemo());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean updateCategory(Category category) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE categories SET categoryName=?, memo=? WHERE typeId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setNString(1, category.getCategoryName());
                ptm.setNString(2, category.getMemo());
                ptm.setInt(3, category.getTypeId());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean deleteCategory(int typeId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM categories WHERE typeId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, typeId);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public Category getCategoryById(int typeId) throws ClassNotFoundException, SQLException {
        Category category = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT typeId, categoryName, memo FROM categories WHERE typeId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, typeId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    category = new Category(
                            rs.getInt("typeId"),
                            rs.getNString("categoryName"),
                            rs.getNString("memo")
                    );
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return category;
    }
}
