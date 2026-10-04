package demo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeTest {

    public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);

        Employee employee = new Employee();

        Attendance attendance = new Attendance();

        Salary salary = new Salary();

        int choice;

        String fileName = "employee_payroll.txt";

        
        do {

            System.out.println("\n========================================");
            System.out.println(" EMPLOYEE ATTENDANCE & PAYROLL SYSTEM");
            System.out.println("========================================");

            System.out.println("1. Enter Employee Details");
            System.out.println("2. Calculate Attendance");
            System.out.println("3. Calculate Salary");
            System.out.println("4. Display Employee Details");
            System.out.println("5. Save Employee Details to File");
            System.out.println("6. Read Employee Details from File");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            
            switch (choice) {

                case 1:
                    System.out.println("\n--- ENTER EMPLOYEE DETAILS ---");

                    System.out.print("Enter Employee ID: ");
                    employee.employeeId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Employee Name: ");
                    employee.employeeName = sc.nextLine();

                    System.out.println("\nSelect Department:");
                    System.out.println("1. IT");
                    System.out.println("2. HR");
                    System.out.println("3. Finance");
                    System.out.println("4. Marketing");

                    System.out.print("Enter Department Choice: ");
                    employee.departmentChoice = sc.nextInt();

                    
                    switch (employee.departmentChoice) {

                        case 1:
                            employee.department = "IT";
                            break;

                        case 2:
                            employee.department = "HR";
                            break;

                        case 3:
                            employee.department = "Finance";
                            break;

                        case 4:
                            employee.department = "Marketing";
                            break;

                        default:
                            employee.department = "Unknown";
                            System.out.println( "Invalid department choice.");
                    }

                    System.out.print("Enter Basic Salary: ");
                    employee.basicSalary = sc.nextDouble();

                    
                    if (employee.basicSalary > 0) {
                        System.out.println("Employee details entered successfully.");
                    } else {
                        System.out.println("Invalid salary. Salary must be greater than zero.");
                    }

                    break;

                
                case 2:
                    System.out.println("\n--- ATTENDANCE CALCULATION ---");

                    System.out.print("Enter Total Working Days: ");
                    attendance.totalWorkingDays = sc.nextInt();

                                        if (attendance.totalWorkingDays > 0) {

                        attendance.presentDays = 0;
                        attendance.absentDays = 0;

                        
                        for (int day = 1; day  <= attendance.totalWorkingDays;day++) {

                            System.out.print(  "Day " + day  + " - Enter 1 for Present, 0 for Absent: ");

                            attendance.attendance = sc.nextInt();

                            
                            if (attendance.attendance == 1) {
                                attendance.presentDays++;
                            } else if (attendance.attendance == 0) {
                                attendance.absentDays++;
                            } else {
                                System.out.println("Invalid input. Enter only 1 or 0.");
                            }
                        }

                        attendance.attendancePercentage = ((double) attendance.presentDays / attendance.totalWorkingDays) * 100;

                        System.out.println("\nPresent Days : "+ attendance.presentDays);

                        System.out.println( "Absent Days : "  + attendance.absentDays);

                        System.out.println( "Attendance % : "  + attendance.attendancePercentage + "%");

                        
                        if (attendance.attendancePercentage >= 75) {
                            System.out.println("Attendance Status: Eligible");
                        } else {
                            System.out.println("Attendance Status: Not Eligible");
                        }

                    } else {
                        System.out.println( "Working days must be greater than zero.");
                    }

                    break;

                
                case 3:
                    System.out.println("\n--- SALARY CALCULATION ---");

                                        if (employee.basicSalary > 0) {

                        
                        if (attendance.totalWorkingDays > 0) {

                            
                            if (attendance.attendancePercentage >= 90) {

                                                   salary.incentive =employee.basicSalary * 0.10;

                                salary.deduction = 0;

                                salary.finalSalary = employee.basicSalary + salary.incentive;

                                System.out.println("Attendance Category: Excellent");

                                System.out.println("Attendance Incentive: 10%");

                            } else if (
                                    attendance.attendancePercentage >= 75) {

                                                        salary.incentive = 0;
                                                         salary.deduction = 0;

                                salary.finalSalary =employee.basicSalary;

                                System.out.println( "Attendance Category: Good");

                                System.out.println( "Attendance Incentive: 0%");

                            } else {

                                
                                salary.incentive = 0;

                                salary.deduction =employee.basicSalary * 0.10;

                                salary.finalSalary =employee.basicSalary- salary.deduction;

                                System.out.println("Attendance Category: Low");

                                System.out.println("Attendance Deduction: 10%");
                            }

                            System.out.println("Basic Salary : ₹"  + employee.basicSalary);

                            System.out.println("Final Salary : ₹" + salary.finalSalary);

                        } else {
                            System.out.println(   "Please calculate attendance first.");
                        }

                    } else {
                        System.out.println("Please enter valid employee details first.");
                    }

                    break;

                
                case 4:
                    System.out.println("\n--- EMPLOYEE DETAILS ---");

                    if (employee.employeeId != 0) {

                        System.out.println("Employee ID : "+ employee.employeeId);

                        System.out.println("Employee Name : " + employee.employeeName);

                        System.out.println("Department : "  + employee.department);

                        System.out.println("Basic Salary : ₹"+ employee.basicSalary);

                        System.out.println("Present Days : " + attendance.presentDays);

                        System.out.println("Absent Days : " + attendance.absentDays);

                        System.out.println("Attendance % : " + attendance.attendancePercentage  + "%");

                        System.out.println( "Final Salary : ₹" + salary.finalSalary);

                    } else {
                        System.out.println("No employee details available.");
                    }

                    break;

                
                case 5:
                    System.out.println("\n--- SAVE EMPLOYEE TO FILE ---");

                    
                    try {

                        
                        FileWriter fw = new FileWriter(fileName);

                        
                        PrintWriter pw = new PrintWriter(fw);

                        
                        pw.println("EMPLOYEE PAYROLL DETAILS");
                        pw.println("========================");

                        pw.println("Employee ID: " + employee.employeeId);

                        pw.println("Employee Name: " + employee.employeeName);

                        pw.println("Department: " + employee.department);

                        pw.println("Basic Salary: "+ employee.basicSalary);

                        
                        pw.println("Total Working Days: " + attendance.totalWorkingDays);

                        pw.println("Present Days: " + attendance.presentDays);

                        pw.println("Absent Days: " + attendance.absentDays);

                        pw.println("Attendance Percentage: " + attendance.attendancePercentage);

                        
                        pw.println("Final Salary: " + salary.finalSalary);

                                                pw.close();

                        System.out.println("Employee details saved successfully.");

                        System.out.println("File Name: " + fileName);

                    } catch (IOException e) {

                         System.out.println("Error while writing to file.");

                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("\n--- READ EMPLOYEE DETAILS FROM FILE ---");

                    try {

                        
                        FileReader fr = new FileReader(fileName);

                        BufferedReader br =
                                new BufferedReader(fr);

                        String line;

                        
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }

                        
                        br.close();

                    } catch (IOException e) {

                        System.out.println("Error while reading file.");

                        System.out.println("Please save employee details first.");
                    }

                    break;

                
                default:
                    System.out.println( "Invalid menu choice. " + "Please enter 1 to 6.");
            }

            
        } while (choice != 6);

        sc.close();
    }
}