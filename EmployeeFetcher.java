import java.sql.*;

public class EmployeeFetcher {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            while (rs.next()) {
                System.out.printf("EmpID: %d, Name: %s, Salary: %.2f%n",
                    rs.getInt("EmpID"), rs.getString("Name"), rs.getDouble("Salary"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
