import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;

// Student Class
class Student {
    String name;
    String rollNumber;
    String dob;
    String courseJoined;
    String batch;
    double cgpa;
    int attendedClasses;
    int totalClasses;
    double attendancePercentage;

    public Student(String name, String rollNumber, String dob, String courseJoined, String batch, double cgpa, int attendedClasses, int totalClasses) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.dob = dob;
        this.courseJoined = courseJoined;
        this.batch = batch;
        this.cgpa = cgpa;
        this.attendedClasses = attendedClasses;
        this.totalClasses = totalClasses;
        this.attendancePercentage = ((double) attendedClasses / totalClasses) * 100;
    }

    public void displayStudentInfo() {
        System.out.printf("Name:              %s\n", name);
        System.out.printf("Roll Number:       %s\n", rollNumber);
        System.out.printf("Date of Birth:     %s\n", dob);
        System.out.printf("Course Joined:     %s\n", courseJoined);
        System.out.printf("Batch:             %s\n", batch);
        System.out.printf("CGPA:              %.2f\n", cgpa);
        System.out.printf("Attendance:        %d/%d\n", attendedClasses, totalClasses);
        System.out.printf("Attendance %%:      %.2f%%\n", attendancePercentage);
    }
}

// Faculty Class
class Faculty {
    String name;
    String facultyID;
    String doj;
    String department;
    String designation;
    int experience;
    double salary;
    int workedDays;
    int totalDays;

    public Faculty(String name, String facultyID, String doj, String department, String designation, int experience, double salary, int workedDays, int totalDays) {
        this.name = name;
        this.facultyID = facultyID;
        this.doj = doj;
        this.department = department;
        this.designation = designation;
        this.experience = experience;
        this.salary = salary;
        this.workedDays = workedDays;
        this.totalDays = totalDays;
    }

    public void displayFacultyInfo() {
        System.out.printf("Name:              %s\n", name);
        System.out.printf("Faculty ID:        %s\n", facultyID);
        System.out.printf("Date of Joining:   %s\n", doj);
        System.out.printf("Department:        %s\n", department);
        System.out.printf("Designation:       %s\n", designation);
        System.out.printf("Experience:        %d years\n", experience);
        System.out.printf("Salary:            %.2f\n", salary);
        System.out.printf("Attendance:        %d/%d\n", workedDays, totalDays);
    }
}

// Main GUI Class
public class CollegeAdminPanelGUI extends JFrame {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Faculty> faculties = new ArrayList<>();
    static int studentRollCounter = 1;
    static int facultyIDCounter = 1;

    public CollegeAdminPanelGUI() {
        setTitle("College Administration Panel");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a JPanel with a violet color strip for the top
        JPanel topColorStrip = new JPanel();
        topColorStrip.setBackground(new Color(138, 43, 226)); // Violet color
        topColorStrip.setPreferredSize(new Dimension(getWidth(), 100)); // Set height of the strip
        add(topColorStrip, BorderLayout.NORTH);  // Adds the top strip to the frame

        // Create a JPanel with a violet color strip for the bottom
        JPanel bottomColorStrip = new JPanel();
        bottomColorStrip.setBackground(new Color(138, 43, 226)); // Violet color
        bottomColorStrip.setPreferredSize(new Dimension(getWidth(), 100)); // Set height of the strip
        add(bottomColorStrip, BorderLayout.SOUTH);  // Adds the bottom strip to the frame

        // Add the "KRGI" brand name at the top with a specific font
        JLabel krgiLabel = new JLabel("KRGI", JLabel.CENTER);
        krgiLabel.setFont(new Font("Serif", Font.BOLD, 40));
        krgiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(krgiLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        // Add the phrase label "COLLEGE ADMINISTRATION PANEL"
        JLabel titleLabel = new JLabel("COLLEGE ADMINISTRATION PANEL", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add the login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> login());
        panel.add(loginButton);
        panel.add(Box.createVerticalGlue());

        // Add the panel to the center of the frame
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void login() {
        String username = JOptionPane.showInputDialog("Enter Admin Username:");
        String password = JOptionPane.showInputDialog("Enter Admin Password:");

        if ("admin".equals(username) && "admin123".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }

    public void showMainMenu() {
        JFrame mainMenu = new JFrame("Main Menu");
        mainMenu.setSize(400, 300);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));

        JButton studentDatabaseButton = new JButton("Student Database");
        JButton facultyDatabaseButton = new JButton("Faculty Database");
        JButton exitButton = new JButton("Exit");

        menuPanel.add(studentDatabaseButton);
        menuPanel.add(facultyDatabaseButton);
        menuPanel.add(exitButton);

        studentDatabaseButton.addActionListener(e -> showStudentMenu());
        facultyDatabaseButton.addActionListener(e -> showFacultyMenu());
        exitButton.addActionListener(e -> System.exit(0));

        mainMenu.add(menuPanel);

        // Center the main menu window on the screen
        mainMenu.setLocationRelativeTo(null);  // This centers the window on the screen

        mainMenu.setVisible(true);
    }

    public void showStudentMenu() {
        JFrame studentMenu = new JFrame("Student Menu");
        studentMenu.setSize(400, 300);
        studentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addStudentButton = new JButton("Add Student");
        JButton modifyStudentButton = new JButton("Modify Student");
        JButton searchStudentButton = new JButton("Search Student");
        JButton deleteStudentButton = new JButton("Delete Student");
        JButton overallReportButton = new JButton("Overall Student Report");

        panel.add(addStudentButton);
        panel.add(modifyStudentButton);
        panel.add(searchStudentButton);
        panel.add(deleteStudentButton);
        panel.add(overallReportButton);

        addStudentButton.addActionListener(e -> addStudent());
        modifyStudentButton.addActionListener(e -> modifyStudent());
        searchStudentButton.addActionListener(e -> searchStudent());
        deleteStudentButton.addActionListener(e -> deleteStudent());
        overallReportButton.addActionListener(e -> overallStudentReport());

        studentMenu.add(panel);
        studentMenu.setLocationRelativeTo(null);
        studentMenu.setVisible(true);
    }




private static HashMap<String, Integer> departmentStudentCount = new HashMap<>();

    public void addStudent() {
        String name = JOptionPane.showInputDialog("Enter Student Name:");
        String dob = JOptionPane.showInputDialog("Enter Date of Birth (DD/MM/YYYY):");
        String courseJoined = JOptionPane.showInputDialog("Enter Course Joined (Abbreviation in CAPS):");
        String batch = JOptionPane.showInputDialog("Enter Batch:");
        double cgpa = Double.parseDouble(JOptionPane.showInputDialog("Enter CGPA:"));
        int attendedClasses = Integer.parseInt(JOptionPane.showInputDialog("Enter Attended Classes:"));
        int totalClasses = Integer.parseInt(JOptionPane.showInputDialog("Enter Total Classes:"));

        // Generate department-wise roll number
        int departmentCount = departmentStudentCount.getOrDefault(courseJoined, 0) + 1;
        departmentStudentCount.put(courseJoined, departmentCount);

        String rollNumber = courseJoined + "-STD" + String.format("%03d", departmentCount);

        // Create and add the new student
        Student newStudent = new Student(name, rollNumber, dob, courseJoined, batch, cgpa, attendedClasses, totalClasses);
        students.add(newStudent);

        JOptionPane.showMessageDialog(this, "Student Added Successfully!\nRoll Number: " + rollNumber);
    }
    public void modifyStudent() {
        String rollNumber = JOptionPane.showInputDialog("Enter Roll Number of Student to Modify:");
        Student studentToModify = findStudentByRollNumber(rollNumber);

        if (studentToModify != null) {
            String newName = JOptionPane.showInputDialog("Enter New Name (Leave blank to keep current):");
            if (!newName.isEmpty()) studentToModify.name = newName;

            String newDob = JOptionPane.showInputDialog("Enter New DOB (Leave blank to keep current):");
            if (!newDob.isEmpty()) studentToModify.dob = newDob;

            String newCourse = JOptionPane.showInputDialog("Enter New Course [Abbreviation in CAPS](Leave blank to keep current):");
            if (!newCourse.isEmpty()) studentToModify.courseJoined = newCourse;

            String newBatch = JOptionPane.showInputDialog("Enter New Batch (Leave blank to keep current):");
            if (!newBatch.isEmpty()) studentToModify.batch = newBatch;

            JOptionPane.showMessageDialog(this, "Student Details Updated Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private Student findStudentByRollNumber(String rollNumber) {
        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                return student;
            }
        }
        return null; // Return null if student is not found
    }

    public void searchStudent() {
        String rollNumber = JOptionPane.showInputDialog("Enter Roll Number of Student to Search:");
        Student student = findStudentByRollNumber(rollNumber);

        if (student != null) {
            StringBuilder studentInfo = new StringBuilder();
            studentInfo.append("Name: ").append(student.name).append("\n")
                    .append("Roll Number: ").append(student.rollNumber).append("\n")
                    .append("Date of Birth: ").append(student.dob).append("\n")
                    .append("Course Joined: ").append(student.courseJoined).append("\n")
                    .append("Batch: ").append(student.batch).append("\n")
                    .append("CGPA: ").append(student.cgpa).append("\n")
                    .append("Attendance: ").append(student.attendedClasses).append("/").append(student.totalClasses).append("\n")
                    .append("Attendance Percentage: ").append(String.format("%.2f%%", student.attendancePercentage));
            JOptionPane.showMessageDialog(this, studentInfo.toString(), "Student Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    public void deleteStudent() {
        String rollNumber = JOptionPane.showInputDialog("Enter Roll Number of Student to Delete:");
        Student student = findStudentByRollNumber(rollNumber);

        if (student != null) {
            students.remove(student);
            JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }


    public void overallStudentReport() {
        StringBuilder report = new StringBuilder("Overall Student Report:\n\n");
        for (Student student : students) {
            report.append("Roll Number: ").append(student.rollNumber).append("\n")
                    .append("Name: ").append(student.name).append("\n")
                    .append("Date of Birth: ").append(student.dob).append("\n")
                    .append("Course Joined: ").append(student.courseJoined).append("\n")
                    .append("Batch: ").append(student.batch).append("\n")
                    .append("CGPA: ").append(student.cgpa).append("\n")
                    .append("Attendance: ").append(student.attendedClasses).append("/").append(student.totalClasses).append("\n")
                    .append("Attendance Percentage: ").append(String.format("%.2f%%", student.attendancePercentage)).append("\n")
                    .append("-----------------------------\n");
        }
        JOptionPane.showMessageDialog(this, report.toString(), "Overall Student Report", JOptionPane.INFORMATION_MESSAGE);
    }


    public void showFacultyMenu() {
        JFrame facultyMenu = new JFrame("Faculty Menu");
        facultyMenu.setSize(400, 300);
        facultyMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addFacultyButton = new JButton("Add Faculty");
        JButton modifyFacultyButton = new JButton("Modify Faculty");
        JButton searchFacultyButton = new JButton("Search Faculty");
        JButton deleteFacultyButton = new JButton("Delete Faculty");
        JButton overallFacultyReportButton = new JButton("Overall Faculty Report");

        panel.add(addFacultyButton);
        panel.add(modifyFacultyButton);
        panel.add(searchFacultyButton);
        panel.add(deleteFacultyButton);
        panel.add(overallFacultyReportButton);

        addFacultyButton.addActionListener(e -> addFaculty());
        modifyFacultyButton.addActionListener(e -> modifyFaculty());
        searchFacultyButton.addActionListener(e -> searchFaculty());
        deleteFacultyButton.addActionListener(e -> deleteFaculty());
        overallFacultyReportButton.addActionListener(e -> overallFacultyReport());

        facultyMenu.add(panel);
        facultyMenu.setLocationRelativeTo(null);
        facultyMenu.setVisible(true);
    }

    private static HashMap<String, Integer> departmentFacultyCount = new HashMap<>();

    public void addFaculty() {
        String name = JOptionPane.showInputDialog("Enter Faculty Name:");
        String doj = JOptionPane.showInputDialog("Enter Date of Joining (DD/MM/YYYY):");
        String department = JOptionPane.showInputDialog("Enter Department (Abbreviation in CAPS):");
        String designation = JOptionPane.showInputDialog("Enter Designation:");
        int experience = Integer.parseInt(JOptionPane.showInputDialog("Enter Experience (in years):"));
        double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary:"));
        int workedDays = Integer.parseInt(JOptionPane.showInputDialog("Enter Worked Days:"));
        int totalDays = Integer.parseInt(JOptionPane.showInputDialog("Enter Total Working Days:"));

        // Generate department-wise faculty ID
        int departmentCount = departmentFacultyCount.getOrDefault(department, 0) + 1;
        departmentFacultyCount.put(department, departmentCount);

        String facultyID = department + "-FAC" + String.format("%03d", departmentCount);

        // Create and add the new faculty
        Faculty newFaculty = new Faculty(name, facultyID, doj, department, designation, experience, salary, workedDays, totalDays);
        faculties.add(newFaculty);

        JOptionPane.showMessageDialog(this, "Faculty Added Successfully!\nFaculty ID: " + facultyID);
    }

    public void modifyFaculty() {
        String facultyID = JOptionPane.showInputDialog("Enter Faculty ID to Modify:");
        Faculty facultyToModify = findFacultyByID(facultyID);

        if (facultyToModify != null) {
            String newName = JOptionPane.showInputDialog("Enter New Name (Leave blank to keep current):");
            if (!newName.isEmpty()) facultyToModify.name = newName;

            String newDepartment = JOptionPane.showInputDialog("Enter New Department [Abbreviation in CAPS] (Leave blank to keep current):");
            if (!newDepartment.isEmpty()) facultyToModify.department = newDepartment;

            String newDesignation = JOptionPane.showInputDialog("Enter New Designation (Leave blank to keep current):");
            if (!newDesignation.isEmpty()) facultyToModify.designation = newDesignation;

            String newExperience = JOptionPane.showInputDialog("Enter New Experience (Leave blank to keep current):");
            if (!newExperience.isEmpty()) facultyToModify.experience = Integer.parseInt(newExperience);

            String newSalary = JOptionPane.showInputDialog("Enter New Salary (Leave blank to keep current):");
            if (!newSalary.isEmpty()) facultyToModify.salary = Double.parseDouble(newSalary);

            JOptionPane.showMessageDialog(this, "Faculty Details Updated Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Faculty not found!");
        }
    }

    private Faculty findFacultyByID(String facultyID) {
        for (Faculty faculty : faculties) {
            if (faculty.facultyID.equals(facultyID)) {
                return faculty;
            }
        }
        return null; // Return null if faculty is not found
    }

    public void searchFaculty() {
        String facultyID = JOptionPane.showInputDialog("Enter Faculty ID to Search:");
        Faculty faculty = findFacultyByID(facultyID);

        if (faculty != null) {
            StringBuilder facultyInfo = new StringBuilder();
            facultyInfo.append("Name: ").append(faculty.name).append("\n")
                    .append("Faculty ID: ").append(faculty.facultyID).append("\n")
                    .append("Date of Joining: ").append(faculty.doj).append("\n")
                    .append("Department: ").append(faculty.department).append("\n")
                    .append("Designation: ").append(faculty.designation).append("\n")
                    .append("Experience: ").append(faculty.experience).append(" years\n")
                    .append("Salary: ").append(String.format("%.2f", faculty.salary)).append("\n")
                    .append("Attendance: ").append(faculty.workedDays).append("/").append(faculty.totalDays).append("\n");
            JOptionPane.showMessageDialog(this, facultyInfo.toString(), "Faculty Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Faculty not found!");
        }
    }

    public void deleteFaculty() {
        String facultyID = JOptionPane.showInputDialog("Enter Faculty ID to Delete:");
        Faculty faculty = findFacultyByID(facultyID);

        if (faculty != null) {
            faculties.remove(faculty);
            JOptionPane.showMessageDialog(this, "Faculty Deleted Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Faculty not found!");
        }
    }

    public void overallFacultyReport() {
        StringBuilder report = new StringBuilder("Overall Faculty Report:\n\n");
        for (Faculty faculty : faculties) {
            report.append("Faculty ID: ").append(faculty.facultyID).append("\n")
                    .append("Name: ").append(faculty.name).append("\n")
                    .append("Date of Joining: ").append(faculty.doj).append("\n")
                    .append("Department: ").append(faculty.department).append("\n")
                    .append("Designation: ").append(faculty.designation).append("\n")
                    .append("Experience: ").append(faculty.experience).append(" years\n")
                    .append("Salary: ").append(String.format("%.2f", faculty.salary)).append("\n")
                    .append("Attendance: ").append(faculty.workedDays).append("/").append(faculty.totalDays).append("\n")
                    .append("-----------------------------\n");
        }
        JOptionPane.showMessageDialog(this, report.toString(), "Overall Faculty Report", JOptionPane.INFORMATION_MESSAGE);
    }



    public static void main(String[] args) {
        new CollegeAdminPanelGUI();
    }
}
