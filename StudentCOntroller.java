import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private Connection con;

    public StudentController() throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "password");
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public void addStudent(Student s) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Students VALUES (?, ?, ?, ?)");
        ps.setInt(1, s.getStudentId());
        ps.setString(2, s.getName());
        ps.setString(3, s.getDepartment());
        ps.setDouble(4, s.getMarks());
        ps.executeUpdate();
    }

    public Student getStudent(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Students WHERE StudentID = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Students");

        while (rs.next()) {
            list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        return list;
    }

    public void updateMarks(int id, double marks) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE Students SET Marks=? WHERE StudentID=?");
        ps.setDouble(1, marks);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Students WHERE StudentID=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
