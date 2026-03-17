package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.models.Account;
import sample.utils.DBUtils;

public class AccountDAO {

    private static final String LOGIN = "SELECT lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts WHERE account=? AND pass=? AND isUse=1";

    public Account checkLogin(String accountId, String password) throws ClassNotFoundException, SQLException {
        Account user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, accountId);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String lastName = rs.getNString("lastName");
                    String firstName = rs.getNString("firstName");
                    java.sql.Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    user = new Account(accountId, password, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public java.util.List<Account> getAllAccounts() throws ClassNotFoundException, SQLException {
        java.util.List<Account> list = new java.util.ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT account, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String accountId = rs.getString("account");
                    String lastName = rs.getNString("lastName");
                    String firstName = rs.getNString("firstName");
                    java.sql.Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    list.add(new Account(accountId, "", lastName, firstName, birthday, gender, phone, isUse, roleInSystem));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean insertAccount(Account acc) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem) VALUES(?,?,?,?,?,?,?,?,?)";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, acc.getAccount());
                ptm.setString(2, acc.getPass());
                ptm.setNString(3, acc.getLastName());
                ptm.setNString(4, acc.getFirstName());
                ptm.setDate(5, acc.getBirthday());
                ptm.setBoolean(6, acc.isGender());
                ptm.setString(7, acc.getPhone());
                ptm.setBoolean(8, acc.isIsUse());
                ptm.setInt(9, acc.getRoleInSystem());

                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deleteAccount(String accountId) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM accounts WHERE account=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, accountId);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public Account getAccountById(String accountId) throws ClassNotFoundException, SQLException {
        Account user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts WHERE account=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String pass = rs.getString("pass");
                    String lastName = rs.getNString("lastName");
                    String firstName = rs.getNString("firstName");
                    java.sql.Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    user = new Account(accountId, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

// 2. Hàm Update thông tin xuống DB
    public boolean updateAccount(Account acc) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE accounts SET pass=?, lastName=?, firstName=?, birthday=?, gender=?, phone=?, isUse=?, roleInSystem=? WHERE account=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, acc.getPass());
                ptm.setNString(2, acc.getLastName());
                ptm.setNString(3, acc.getFirstName());
                ptm.setDate(4, acc.getBirthday());
                ptm.setBoolean(5, acc.isGender());
                ptm.setString(6, acc.getPhone());
                ptm.setBoolean(7, acc.isIsUse());
                ptm.setInt(8, acc.getRoleInSystem());
                ptm.setString(9, acc.getAccount()); // Điều kiện WHERE

                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
