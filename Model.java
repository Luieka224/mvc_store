import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Model {

    private Connection con = null;
    private String fullname;
    private Object[] prodDetails = new Object[5];
    private int totalPrice = 0;

    public boolean checkConnectionToServer() {
		HttpURLConnection connection = null;

        try {
            URL u = new URL("https://freedb.tech/");
    
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("HEAD");
    
            int code = connection.getResponseCode();
    
            if(code == 200) return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
	}

    public boolean checkInternet() {
        HttpURLConnection connection = null;

        try {
            URL u = new URL("http://www.google.com/");
    
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("HEAD");
    
            int code = connection.getResponseCode();
    
            if(code == 200) return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean connectToDB() {
        Config config = new Config();

        try {
            con = DriverManager.getConnection(
                config.getDBURI(),
                config.getDBusername(),
                config.getDBpassword());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
      
        return false;
    }

    private String encryptPass(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(password.getBytes());
            BigInteger bi = new BigInteger(1, digest);
            String hashed = bi.toString(16);

            while (hashed.length() < 32)
                hashed += "0";

            return hashed;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return password;
    }

    public int verifyUser(String username, String password) throws SQLException {
        password = encryptPass(password);

        int role = 0;
        Statement stmt = null;
        String query = "SELECT role, fullName FROM S_users WHERE username = '" + username + "' AND password = '"
                + password + "'";

        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = stmt.executeQuery(query);

        if (rs != null) {
            while (rs.next()) {
                role = rs.getInt("role");
                fullname = rs.getString("fullName");
            }
        }

        return role;
    }

    public String getFullName() {
        return this.fullname;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Object[] getProduct(String prodCode, int qty) throws SQLException {
        String query = "SELECT * FROM S_products WHERE productCode = '" + prodCode + "'";

        String productName = null;
        int price = 0;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                productName = rs.getString("productName");
                price = rs.getInt("price");
            }
        }

        if (!productName.equals(null)) {
            prodDetails[0] = prodCode;
            prodDetails[1] = productName;
            prodDetails[2] = qty;
            prodDetails[3] = price;
            prodDetails[4] = qty * price;

            totalPrice += qty * price;

            return prodDetails;
        }

        return null;
    }

    public Object[][] getUsers() throws SQLException {
        int i = 0, size = 0;
        String role = null;

        String query = "SELECT COUNT(id) FROM S_users";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                size = rs.getInt("COUNT(id)");
            }
        }

        Object[][] users = new Object[size][3];

        query = "SELECT role, username, fullName FROM S_users";
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                if (rs.getInt("role") == 1)
                    role = "User";
                else
                    role = "Admin";

                users[i][0] = role;
                users[i][1] = rs.getString("username");
                users[i][2] = rs.getString("fullName");

                i++;
            }

            return users;
        }

        return null;
    }

    public Object[][] getProducts() throws SQLException {
        int i = 0, size = 0;

        String query = "SELECT COUNT(id) FROM S_products";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                size = rs.getInt("COUNT(id)");
            }
        }

        Object[][] products = new Object[size][3];

        query = "SELECT productCode, productName, price FROM S_products";
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                products[i][0] = rs.getString("productCode");
                products[i][1] = rs.getString("productName");
                products[i][2] = rs.getString("price");

                i++;
            }

            return products;
        }

        return null;
    }

    public boolean registerUser(int role, String username, String password, String fullname) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;

        int count = 0;
        boolean isEmpty = false;

        if(username.equals("") || password.equals("") || fullname.equals("")) {
            isEmpty = !isEmpty;
        }

        query = "SELECT * FROM S_users WHERE username  = '" + username +"'";
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(count == 0 && !isEmpty) {
            password = encryptPass(password);
            query = "INSERT INTO S_users (role, username, password, fullName) VALUES " +
                            "(" + role + ", " +
                            "'" + username + "', " +
                            "'" + password + "', " +
                            "'" + fullname + "')";

            try {
                stmt = con.createStatement();
                stmt.executeUpdate(query);

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return false;
    }
    
    public boolean removeUser(String username) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
    
        int count = 0;
        boolean isEmpty = false;
    
        if(username.equals("")) {
            isEmpty = !isEmpty;
        }
    
        query = "SELECT * FROM S_users WHERE username  = '" + username +"'";
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        if(count == 1 && !isEmpty) {
            query = "DELETE FROM S_users WHERE S_users.username = '"+username+"'";
    
            try {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
    
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return false;
    }

	public boolean addProduct(String productCode, String productName, int price) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;

        int count = 0;
        boolean isEmpty = false;

        if(productCode.equals("") || productName.equals("") || price == 0) {
            isEmpty = !isEmpty;
        }

        query = "SELECT * FROM S_products WHERE productCode  = '" + productCode +"'";
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(count == 0 && !isEmpty) {
            query = "INSERT INTO S_products (productCode, productName, price) VALUES " +
                            "('" + productCode + "', " +
                            "'" + productName + "', " +
                            price + ")";

            try {
                stmt = con.createStatement();
                stmt.executeUpdate(query);

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return false;
    }
    
    public boolean removeProduct(String productCode) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
    
        int count = 0;
        boolean isEmpty = false;
    
        if(productCode.equals("")) {
            isEmpty = !isEmpty;
        }
    
        query = "SELECT * FROM S_products WHERE productCode  = '" + productCode +"'";
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        if(count == 1 && !isEmpty) {
            query = "DELETE FROM S_products WHERE productCode = '"+productCode+"'";
    
            try {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
    
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return false;
    }

    public void emptyProducts() {
        Statement stmt = null;
        String query = "DELETE FROM S_products";
    
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}