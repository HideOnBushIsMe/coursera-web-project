package sample.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sample.models.Product;
import sample.utils.DBUtils;

public class ProductDAO {

    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product(
                            rs.getString("productId"),
                            rs.getNString("productName"),
                            rs.getString("productImage"),
                            rs.getNString("brief"),
                            rs.getDate("postedDate"),
                            rs.getInt("typeId"),
                            rs.getString("account"),
                            rs.getNString("unit"),
                            rs.getInt("price"),
                            rs.getInt("discount")
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

    public List<Product> searchProductsByName(String name) throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products WHERE productName LIKE ?";
                ptm = conn.prepareStatement(sql);
                ptm.setNString(1, "%" + name + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product(
                            rs.getString("productId"),
                            rs.getNString("productName"),
                            rs.getString("productImage"),
                            rs.getNString("brief"),
                            rs.getDate("postedDate"),
                            rs.getInt("typeId"),
                            rs.getString("account"),
                            rs.getNString("unit"),
                            rs.getInt("price"),
                            rs.getInt("discount")
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

    public List<Product> getProductsByCategory(int typeId) throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products WHERE typeId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, typeId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product(
                            rs.getString("productId"),
                            rs.getNString("productName"),
                            rs.getString("productImage"),
                            rs.getNString("brief"),
                            rs.getDate("postedDate"),
                            rs.getInt("typeId"),
                            rs.getString("account"),
                            rs.getNString("unit"),
                            rs.getInt("price"),
                            rs.getInt("discount")
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

    public List<Product> getFilteredProducts(Integer minPrice, Integer maxPrice, Boolean isDiscount, String sortOrder) throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                StringBuilder sql = new StringBuilder("SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products WHERE 1=1");
                if (minPrice != null) sql.append(" AND price >= ").append(minPrice);
                if (maxPrice != null) sql.append(" AND price <= ").append(maxPrice);
                if (isDiscount != null) {
                    if (isDiscount) sql.append(" AND discount > 0");
                    else sql.append(" AND discount = 0");
                }
                if (sortOrder != null) {
                    if (sortOrder.equalsIgnoreCase("ASC")) sql.append(" ORDER BY price ASC");
                    else if (sortOrder.equalsIgnoreCase("DESC")) sql.append(" ORDER BY price DESC");
                }

                ptm = conn.prepareStatement(sql.toString());
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product(
                            rs.getString("productId"),
                            rs.getNString("productName"),
                            rs.getString("productImage"),
                            rs.getNString("brief"),
                            rs.getDate("postedDate"),
                            rs.getInt("typeId"),
                            rs.getString("account"),
                            rs.getNString("unit"),
                            rs.getInt("price"),
                            rs.getInt("discount")
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

    public boolean insertProduct(Product p) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO products(productId, productName, productImage, brief, typeId, account, unit, price, discount) VALUES(?,?,?,?,?,?,?,?,?)";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, p.getProductId());
                ptm.setNString(2, p.getProductName());
                ptm.setString(3, p.getProductImage());
                ptm.setNString(4, p.getBrief());
                ptm.setInt(5, p.getTypeId());
                ptm.setString(6, p.getAccount());
                ptm.setNString(7, p.getUnit());
                ptm.setInt(8, p.getPrice());
                ptm.setInt(9, p.getDiscount());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean deleteProduct(String productId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM products WHERE productId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, productId);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public Product getProductById(String id) throws ClassNotFoundException, SQLException {
        Product p = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products WHERE productId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    p = new Product(
                            id,
                            rs.getNString("productName"),
                            rs.getString("productImage"),
                            rs.getNString("brief"),
                            rs.getDate("postedDate"),
                            rs.getInt("typeId"),
                            rs.getString("account"),
                            rs.getNString("unit"),
                            rs.getInt("price"),
                            rs.getInt("discount")
                    );
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return p;
    }

    public boolean updateProduct(Product p) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE products SET productName=?, productImage=?, brief=?, typeId=?, unit=?, price=?, discount=? WHERE productId=?";
                ptm = conn.prepareStatement(sql);
                ptm.setNString(1, p.getProductName());
                ptm.setString(2, p.getProductImage());
                ptm.setNString(3, p.getBrief());
                ptm.setInt(4, p.getTypeId());
                ptm.setNString(5, p.getUnit());
                ptm.setInt(6, p.getPrice());
                ptm.setInt(7, p.getDiscount());
                ptm.setString(8, p.getProductId());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
}
