import java.util.*;

// ================= EMPLOYEE DATA CLASS ================= //
class Employee {
    Scanner sc = new Scanner(System.in);

    // ANSI color codes
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String CYAN = "\u001B[36m";
    static final String BLUE = "\u001B[34m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";

    String name;
    int id;
    static int count = 0;
    double salary;
    int year;
    String mo_no;
    int dep_no;
    String address;

    Department department;

    // LEAVE LOGIC
    int totalLeavesEntitled = 24;
    int leavesUsed = 0;
    int pendingLeaves = 0;

    class Department {
        int deptid;
        String deptname;

        Department(int id, String name) {
            this.deptid = id;
            this.deptname = name;
        }

        void display() {
            System.out.println(GREEN + "  Department     : " + deptname + " (ID: " + deptid + ")");
        }
    }

    // --- DISPLAY (USING array) ---
    void display_det(Employee[] x, int size) {
        if (size == 0) {
            System.out.println(RED + "No employees found." + RESET);
            return;
        }
        for (int i = 0; i < size; i++) {
            x[i].display_det();
        }
    }

    void display_det() {
        System.out.println(CYAN + "---------------------------------------------------------------" + RESET);
        System.out.println(GREEN + "                  | Employee details |             " + RESET);
        System.out.println(CYAN + "---------------------------------------------------------------" + RESET);

        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Employee ID", id);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Employee Name", name);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Joining Year", year);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Salary per Month", salary);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Address", address);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Mobile Number", mo_no);

        if (department != null) {
            System.out.printf(
                    GREEN + "| %-24s | %-32s |\n" + RESET,
                    "Department",
                    department.deptname + " (ID: " + department.deptid + ")"
            );
        }

        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Leaves Entitled", totalLeavesEntitled);
        System.out.printf(GREEN + "| %-24s | %-32s |\n" + RESET, "Leaves Used", leavesUsed);
        System.out.printf(
                GREEN + "| %-24s | %-32s |\n" + RESET,"leaves balance",(totalLeavesEntitled - leavesUsed));
        System.out.println(CYAN + "---------------------------------------------------------------" + RESET);
    }

    int generate_id() {
        count++;
        this.id = count;
        return this.id;
    }

    // --- SORTING (Using  array) ---
    void sort(Employee[] x, int size) {//by year old to new
        Employee temp;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (x[i].year > x[j].year) {
                    temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
        display_det(x, size);
    }

    void sort2(Employee[] x, int size) {//by year new to old
        Employee temp;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (x[i].year < x[j].year) {
                    temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
        display_det(x, size);
    }

    void sort3(Employee[] x, int size) { // by ID
        Employee temp;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (x[i].id > x[j].id) {
                    temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
        display_det(x, size);
        System.out.println("Employees sorted by ID.");
        System.out.println("-----------------------------------------------");
    }

    int find() {// to  find employee by ID
        System.out.print("Enter ID you want to find  :-");
        return sc.nextInt();
    }

    void searchEmployeeByName(Employee[] x, int size) {
        sc.nextLine();
        System.out.print("Enter Employee Name to search: ");
        String name = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (x[i].name.toLowerCase().contains(name.toLowerCase())) {
                x[i].display_det();
                found = true;
            }
        }
        if (!found) System.out.println(RED + "No employee found." + RESET);
        System.out.println("-----------------------------------------------");
    }

    // --- UPDATE ---
    public void update_name(Employee[] x, int size) {
        System.out.print("Enter ID for update name  :-");
        int b = sc.nextInt();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (x[i].id == b) {
                System.out.println("Enter name = ");
                x[i].name = sc.next();
                x[i].display_det();
                found = true;
                break;
            }
        }
        if (!found) System.out.println(RED + "id is not found!!" + RESET);
        System.out.println("-----------------------------------------------");
    }

    void update_salery(Employee[] x, int size) {
        System.out.print("Enter ID for update salary  :-");
        int b = sc.nextInt();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (x[i].id == b) {
                System.out.println("Enter salary = ");
                x[i].salary = sc.nextDouble();
                x[i].display_det();
                found = true;
                break;
            }
        }
        if (!found) System.out.println(RED + "id is not found!!" + RESET);
        System.out.println("-----------------------------------------------");
    }

    // --- DELETE (Returns new size) ---
    static int deleteEmployee(Employee[] x, int size) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        boolean found = false;
        int deleteIndex = -1;

        for (int i = 0; i < size; i++) {
            if (x[i].id == id) {
                deleteIndex = i;
                found = true;
                break;
            }
        }

        if (found) {
            // Shift all elements after deleteIndex to the left
            for (int i = deleteIndex; i < size - 1; i++) {
                x[i] = x[i + 1];
            }
            x[size - 1] = null; // Clear last element
            System.out.println(GREEN + "Employee deleted successfully!" + RESET);
            size--;
        } else {
            System.out.println(RED + "Employee not found!" + RESET);
        }

        System.out.println("-----------------------------------------------");
        return size;
    }

    static void calculateTotalSalary(Employee[] x, int size) {
        double totalSalary = 0;
        for (int i = 0; i < size; i++) {
            totalSalary += x[i].salary;
        }
        System.out.println("Total salary of all employees: " + totalSalary);
        System.out.println("-----------------------------------------------");
    }

    static void displayEmployeeCount(int size) {
        System.out.println("Number of employees: " + size);
    }

    void incrementSalary(Employee[] x, int size) {
        System.out.print("Enter Employee ID for salary increment: ");
        int id = sc.nextInt();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (x[i].id == id) {
                System.out.print("Enter increment percentage: ");
                double percent = sc.nextDouble();
                double increment = (x[i].salary * percent) / 100;
                x[i].salary += increment;
                System.out.println(GREEN + "Salary updated successfully!" + RESET);
                System.out.println("New Salary: " + x[i].salary);
                found = true;
                break;
            }
        }
        if (!found) System.out.println(RED + "Employee not found!" + RESET);
    }

    // --- LEAVE MANAGEMENT (SPLIT LOGIC) ---
    void leavemanagement(Employee[] x, int size, String userRole) {
        // MANAGER / ADMIN VIEW
        if (userRole.equals("MANAGER")) {
            System.out.println("\n--- MANAGER LEAVE APPROVAL ---");
            boolean hasPending = false;
            for (int i = 0; i < size; i++) {
                if (x[i].pendingLeaves > 0) {
                    System.out.println("ID: " + x[i].id + " | Name: " + x[i].name + " | Request: " + x[i].pendingLeaves + " days");
                    hasPending = true;
                }
            }
            if (!hasPending) {
                System.out.println("No pending requests.");
                return;
            }

            System.out.print("Enter Employee ID to Action (0 to exit): ");
            int id = sc.nextInt();
            if (id == 0) return;

            for (int i = 0; i < size; i++) {
                if (x[i].id == id && x[i].pendingLeaves > 0) {
                    System.out.println("1. Approve");
                    System.out.println("2. Reject");
                    int dec = sc.nextInt();
                    if (dec == 1) {
                        x[i].leavesUsed += x[i].pendingLeaves;
                        x[i].pendingLeaves = 0;
                        System.out.println(GREEN + "Leave Approved." + RESET);
                    } else {
                        x[i].pendingLeaves = 0;
                        System.out.println(RED + "Leave Rejected." + RESET);
                    }
                    return;
                }
            }
            System.out.println("ID not found or no pending leaves.");
        }
        // EMPLOYEE VIEW
        else {
            System.out.print("Enter YOUR Employee ID to verify: ");
            int id = sc.nextInt();
            for (int i = 0; i < size; i++) {
                if (x[i].id == id) {
                    System.out.println("\n" + x[i].name + " - Leave Management");
                    System.out.println("1. View Leave Status");
                    System.out.println("2. Apply for Leave");
                    System.out.print("Choice: ");
                    int opt = sc.nextInt();

                    if (opt == 1) x[i].display_det();
                    else if (opt == 2) x[i].applyLeave();
                    return;
                }
            }
            System.out.println(RED + "Employee ID not found." + RESET);
        }
    }

    void applyLeave() {
        System.out.print("Enter number of leaves to apply: ");
        int days = sc.nextInt();

        if (days <= 0) {
            System.out.println(RED + "Invalid number of days." + RESET);
            return;
        }

        int balance = totalLeavesEntitled - leavesUsed;
        if (days + pendingLeaves > balance) {
            System.out.println(RED + "Insufficient balance (incl. pending requests)!" + RESET);
        } else {
            pendingLeaves += days;
            System.out.println(GREEN + "Leave applied! Sent to Manager for approval." + RESET);
        }
    }
}

// ================= INPUT CLASS ================= //
class EmployeeInput extends Employee {
    EmployeeInput() {
        System.out.println("-----------------------------------------------");
        System.out.print("Enter employee's name :- ");
        this.name = sc.next();
        System.out.println("employee id number is" + " " + generate_id());

        int currentYear = 2026;
        boolean validYear;
        do {
            System.out.println("Enter employee joining year:");
            this.year = sc.nextInt();
            validYear = true;
            if (this.year <= 0 || this.year > currentYear) {
                validYear = false;
                System.out.println("Enter valid joining year");
            }
        } while (!validYear);

        System.out.print("Enter employee's address :- ");
        this.address = sc.next();

        boolean valid;
        do {
            System.out.println("Enter mobile number:");
            mo_no = sc.next();
            valid = true;
            if (mo_no.length() != 10) valid = false;
            else if (mo_no.charAt(0) != '9' && mo_no.charAt(0) != '8' && mo_no.charAt(0) != '7') valid = false;
            else {
                for (int i = 0; i < mo_no.length(); i++) {
                    if (mo_no.charAt(i) < '0' || mo_no.charAt(i) > '9') {
                        valid = false;
                        break;
                    }
                }
            }
            if (!valid) System.out.println("Invalid mobile number.");
        } while (!valid);

        System.out.println("\nDepartments: 1.HR  2.Engineer  3.Finance");
        System.out.print("Enter dept (1-3) :- ");
        this.dep_no = sc.nextInt();
        if (dep_no == 1) { this.department = new Department(1, "HR"); this.salary = 80000; }
        else if (dep_no == 2) { this.department = new Department(2, "Engineer"); this.salary = 50000; }
        else if (dep_no == 3) { this.department = new Department(3, "Finance"); this.salary = 30000; }
        else { this.department = new Department(0, "General"); this.salary = 10000; }

        System.out.println(GREEN + "Employee added successfully!" + RESET);
    }
}

// ================= USER & AUTH ================= //
class User {
    String username;
    String password;
    String role; // ADMIN, MANAGER, EMPLOYEE

    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

class AuthenticationSystem {
    static User[] users = new User[100]; // Maximum 100 users
    static int userCount = 0;
    static Scanner sc = new Scanner(System.in);

    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String BLUE = "\u001B[34m";
    static final String CYAN = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";

    public static String generateRandomPassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder(8);
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#*";
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public static void register() {
        if (userCount >= 100) {
            System.out.println(RED + "Maximum user limit reached!" + RESET);
            return;
        }

        System.out.println(CYAN + "\n=== USER REGISTRATION ===" + RESET);
        System.out.println("Select Role to Register:");
        System.out.println("1. Admin");
        System.out.println("2. Manager");
        System.out.println("3. Employee");
        System.out.print("Choice: ");
        int r = sc.nextInt();

        String role = "EMPLOYEE";

        // CHECK IF ADMIN OR MANAGER ALREADY EXISTS
        if (r == 1) {
            for (int i = 0; i < userCount; i++) {
                if (users[i].role.equals("ADMIN")) {
                    System.out.println(RED + "Error: An Admin is already registered! Only one allowed." + RESET);
                    return;
                }
            }
            role = "ADMIN";
        }
        else if (r == 2) {
            for (int i = 0; i < userCount; i++) {
                if (users[i].role.equals("MANAGER")) {
                    System.out.println(RED + "Error: A Manager is already registered! Only one allowed." + RESET);
                    System.out.println("Ask Admin to change manager if needed.");
                    return;
                }
            }
            role = "MANAGER";
        }

        System.out.print("Enter username: ");
        String username = sc.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                System.out.println(RED + "Username taken!" + RESET);
                return;
            }
        }

        String password = generateRandomPassword();
        users[userCount] = new User(username, password, role);
        userCount++;

        System.out.println(GREEN + "Registered as " + role + " successfully!" + RESET);
        System.out.println(BLUE + "Username: " + username + RESET);
        System.out.println(BLUE + "Password: " + password + RESET);
        System.out.println(RED + "PLEASE SAVE THIS PASSWORD." + RESET);
    }

    public static User login() {
        System.out.println(CYAN + "\n=== LOGIN ===" + RESET);
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].authenticate(password)) {
                System.out.println(GREEN + "Welcome " + username + " (" + users[i].role + ")" + RESET);
                return users[i];
            }
        }
        System.out.println(RED + "Invalid credentials." + RESET);
        return null;
    }

    public static void adminChangeManager() {
        System.out.println(YELLOW + "\n--- ADMIN: CHANGE MANAGER ---" + RESET);
        System.out.println("This deletes the current Manager account so a new one can be registered.");

        boolean found = false;
        int managerIndex = -1;

        for (int i = 0; i < userCount; i++) {
            if (users[i].role.equals("MANAGER")) {
                managerIndex = i;
                found = true;
                System.out.println("Current Manager: " + users[i].username);
                System.out.print("Do you want to remove them? (1.Yes 2.No): ");
                int confirm = sc.nextInt();
                if (confirm == 1) {
                    // Shift all users after managerIndex to the left
                    for (int j = managerIndex; j < userCount - 1; j++) {
                        users[j] = users[j + 1];
                    }
                    users[userCount - 1] = null;
                    userCount--;
                    System.out.println(GREEN + "Manager Removed. You can now register a new Manager." + RESET);
                } else {
                    System.out.println("Cancelled.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println(RED + "No Manager account found currently." + RESET);
        }
    }
}

// ================= MAIN RUN CLASS ================= //
class Run {
    static Employee[] empList = new Employee[100]; // Maximum 100 employees
    static int empCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Employee.BLUE + "***********************************************");
        System.out.println("      EMPLOYEE MANAGEMENT SYSTEM        ");
        System.out.println("***********************************************" + Employee.RESET);

        while (true) {
            // 1. AUTH LOOP
            User currentUser = null;
            while (currentUser == null) {
                System.out.println("\n1. Register | 2. Login | 3. Exit");
                System.out.print("Choice: ");
                int ch = sc.nextInt();
                if (ch == 1) AuthenticationSystem.register();
                else if (ch == 2) currentUser = AuthenticationSystem.login();
                else if (ch == 3) System.exit(0);
                else System.out.println("Invalid.");
            }

            // 2. DASHBOARD LOOP (Based on Role)
            boolean loggedIn = true;
            Employee ob = new Employee();

            while (loggedIn) {
                System.out.println(Employee.YELLOW + "\n╔════ " + currentUser.role + " MENU ════╗" + Employee.RESET);

                // --- ADMIN MENU ---
                if (currentUser.role.equals("ADMIN")) {
                    System.out.println("1. View All Users");
                    System.out.println("2. Change Manager (Remove current Manager)");
                    System.out.println("3. Logout");
                    System.out.print("Choice: ");
                    int c = sc.nextInt();
                    switch(c) {
                        case 1:
                            for (int i = 0; i < AuthenticationSystem.userCount; i++) {
                                System.out.println("- " + AuthenticationSystem.users[i].username +
                                        " (" + AuthenticationSystem.users[i].role + ")");
                            }
                            break;
                        case 2: AuthenticationSystem.adminChangeManager(); break;
                        case 3: loggedIn = false; break;
                    }
                }

                // --- MANAGER MENU ---
                else if (currentUser.role.equals("MANAGER")) {
                    System.out.println(" 1. View All Employees");
                    System.out.println(" 2. Add New Employee");
                    System.out.println(" 3. Delete Employee");
                    System.out.println(" 4. Search Employee");
                    System.out.println(" 5. Update Employee");
                    System.out.println(" 6. Sort Employees");
                    System.out.println(" 7. Payroll (Total Salary)");
                    System.out.println(" 8. Employee Count");
                    System.out.println(" 9. incrementSalary");
                    System.out.println(" 10. Leave Approval");
                    System.out.println(" 11. Logout");
                    System.out.print("Choice: ");
                    int c = sc.nextInt();

                    switch(c) {
                        case 1:
                            ob.display_det(empList, empCount);
                            break;
                        case 2:
                            if (empCount >= 100) {
                                System.out.println(Employee.RED + "Maximum employee limit reached!" + Employee.RESET);
                            } else {
                                empList[empCount] = new EmployeeInput();
                                empCount++;
                            }
                            break;
                        case 3:
                            empCount = Employee.deleteEmployee(empList, empCount);
                            break;
                        case 4:
                            System.out.println("1. By ID | 2. By Name");
                            if (sc.nextInt() == 1) {
                                int id = ob.find();
                                boolean f = false;
                                for (int i = 0; i < empCount; i++) {
                                    if (empList[i].id == id) {
                                        empList[i].display_det();
                                        f = true;
                                    }
                                }
                                if (!f) System.out.println("Not Found");
                            } else {
                                ob.searchEmployeeByName(empList, empCount);
                            }
                            break;
                        case 5:
                            System.out.println("1. Name | 2. Salary");
                            if (sc.nextInt() == 1) ob.update_name(empList, empCount);
                            else ob.update_salery(empList, empCount);
                            break;
                        case 6:
                            System.out.println("1. Year(Old-New) 2. Year(New-Old) 3. ID");
                            int s = sc.nextInt();
                            if (s == 1) ob.sort(empList, empCount);
                            else if (s == 2) ob.sort2(empList, empCount);
                            else ob.sort3(empList, empCount);
                            break;
                        case 7:
                            Employee.calculateTotalSalary(empList, empCount);
                            break;
                        case 8:
                            Employee.displayEmployeeCount(empCount);
                            break;
                        case 9:
                            ob.incrementSalary(empList, empCount);
                            break;
                        case 10:
                            ob.leavemanagement(empList, empCount, "MANAGER");
                            break;
                        case 11:
                            loggedIn = false;
                            break;
                    }
                }

                // --- EMPLOYEE MENU ---
                else {
                    System.out.println(" 1. View My Data");
                    System.out.println(" 2. Apply for Leave");
                    System.out.println(" 3. Logout");
                    System.out.print("Choice: ");
                    int c = sc.nextInt();

                    switch(c) {
                        case 1:
                            System.out.print("Enter your Employee ID to view: ");
                            int id = sc.nextInt();
                            boolean f = false;
                            for (int i = 0; i < empCount; i++) {
                                if (empList[i].id == id) {
                                    empList[i].display_det();
                                    f = true;
                                }
                            }
                            if (!f) System.out.println(Employee.RED + "ID Not Found." + Employee.RESET);
                            break;
                        case 2:
                            System.out.print("Enter your Employee ID to apply: ");
                            int id2 = sc.nextInt();
                            boolean f2 = false;
                            for (int i = 0; i < empCount; i++) {
                                if (empList[i].id == id2) {
                                    empList[i].applyLeave();
                                    f2 = true;
                                }
                            }
                            if (!f2) System.out.println(Employee.RED + "ID Not Found." + Employee.RESET);
                            break;
                        case 3:
                            loggedIn = false;
                            break;
                    }
                }
            }
        }
    }
}