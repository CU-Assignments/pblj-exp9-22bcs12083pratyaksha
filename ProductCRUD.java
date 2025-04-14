import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/store";
    static final String USER = "root";
    static final String PASSWORD = "password";
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);
            Class.forName("com.mysql.cj.jdbc.Driver");

            while (true) {
                System.out.println("\n1.Add 2.View 3.Update 4.Delete 5.Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewProducts();
                    case 3 -> updateProduct();
                    case 4 -> deleteProduct();
                    case 5 -> System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct() throws SQLException {
        System.out.print("Enter Name, Price, Quantity: ");
        String name = sc.next();
        double price = sc.nextDouble();
        int qty = sc.nextInt();

        PreparedStatement ps = con.prepareStatement("INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, qty);
        ps.executeUpdate();

        con.commit();
        System.out.println("Product added.");
    }

    static void viewProducts() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        while (rs.next()) {
            System.out.printf("ID: %d | Name: %s | Price: %.2f | Quantity: %d%n",
                    rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
        }
    }

    static void updateProduct() throws SQLException {
        System.out.print("Enter ProductID to update and new Quantity: ");
        int id = sc.nextInt();
        int qty = sc.nextInt();

        PreparedStatement ps = con.prepareStatement("UPDATE Product SET Quantity=? WHERE ProductID=?");
        ps.setInt(1, qty);
        ps.setInt(2, id);
        int i = ps.executeUpdate();
        con.commit();

        if (i > 0) System.out.println("Updated successfully.");
        else System.out.println("Product not found.");
    }

    static void deleteProduct() throws SQLException {
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE ProductID=?");
        ps.setInt(1, id);
        int i = ps.executeUpdate();
        con.commit();

        if (i > 0) System.out.println("Deleted successfully.");
        else System.out.println("Product not found.");
    }
}
