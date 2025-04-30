package Advanced_Internship_mangement_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Internship_management_system_Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Supervisor> supervisors = new ArrayList<>();
        ArrayList<Company> companies = new ArrayList<>();
        ArrayList<Internship> internships = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Internship Management System ---");
            System.out.println("1. Register Student");
            System.out.println("2. Register Supervisor");
            System.out.println("3. Assign Internship");
            System.out.println("4. Generate Reports");
            System.out.println("5. Search Internship by Student");
            System.out.println("6. Search Internship by University");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = input.nextLine();
                    System.out.print("Enter Full Name: ");
                    String fullName = input.nextLine();
                    System.out.print("Enter University (ULK, UR, AUCA, UK): ");
                    String university = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    Student student = new Student(studentId, fullName, university, email);
                    students.add(student);
                    System.out.println("Student registered successfully!");
                    break;
                case 2:
                    System.out.print("Enter Supervisor ID: ");
                    String supervisorId = input.nextLine();
                    System.out.print("Enter Full Name: ");
                    String supervisorName = input.nextLine();
                    System.out.print("Enter Qualification (Bachelors, Masters, PhD): ");
                    String qualification = input.nextLine();
                    System.out.print("Enter Email: ");
                    String supervisorEmail = input.nextLine();
                    Supervisor supervisor = new Supervisor(supervisorId, supervisorName, qualification, supervisorEmail);
                    supervisors.add(supervisor);
                    System.out.println("Supervisor registered successfully!");
                    break;
                case 3:
                    System.out.println("Available Students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ". " + students.get(i).getFullname());
                    }
                    System.out.print("Select Student by index: ");
                    int studentIndex = input.nextInt();
                    input.nextLine();

                    System.out.println("Available Supervisors:");
                    for (int i = 0; i < supervisors.size(); i++) {
                        System.out.println(i + ". " + supervisors.get(i).getFullName());
                    }
                    System.out.print("Select Supervisor by index: ");
                    int supervisorIndex = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Company ID: ");
                    String companyId = input.nextLine();
                    System.out.print("Enter Company Name: ");
                    String companyName = input.nextLine();
                    System.out.print("Enter Industry Type (IT, Finance, Health, Education): ");
                    String industryType = input.nextLine();
                    System.out.print("Enter Location: ");
                    String location = input.nextLine();
                    Company company = new Company(companyId, companyName, industryType, location);
                    companies.add(company);

                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(input.nextLine());
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(input.nextLine());

                    System.out.print("Enter Internship Type (ULK, UR, AUCA, UK, Remote): ");
                    String internshipType = input.nextLine();

                    Internship internship = null;
                    String internshipId = UUID.randomUUID().toString();

                    if (internshipType.equalsIgnoreCase("ULK")) {
                        internship = new ULKInternship(internshipId, students.get(studentIndex), companyName, supervisors.get(supervisorIndex), startDate, endDate, "ONgoing");
                    } else if (internshipType.equalsIgnoreCase("UR")) {
                        internship = new URInternship(internshipId, students.get(studentIndex), companyName, supervisors.get(supervisorIndex), startDate, endDate, "ONGOING");
                    } else if (internshipType.equalsIgnoreCase("AUCA")) {
                        internship = new AUCAInternship(internshipId, students.get(studentIndex), companyName, supervisors.get(supervisorIndex), startDate, endDate, "ONGOING");
                    } else if (internshipType.equalsIgnoreCase("UK")) {
                        internship = new UKInternship(internshipId, students.get(studentIndex), companyName, supervisors.get(supervisorIndex), startDate, endDate,  "PENDING" );
                    } else if (internshipType.equalsIgnoreCase("Remote")) {
                        internship = new RemoteInternship(internshipId, students.get(studentIndex), companyName, supervisors.get(supervisorIndex), startDate, endDate,  "PENDING" );
                    }

                    if (internship != null) {
                        internships.add(internship);
                        System.out.println("Internship assigned successfully!");
                    } else {
                        System.out.println("Invalid Internship Type!");
                    }
                    break;
                case 4:
                    for (Internship i : internships) {
                        System.out.println("\n--- Internship Report ---");
                        System.out.println("Student: " + i.getStudent().getFullname() + " (" + i.getStudent().getUniversity() + ")");
                        System.out.println("Duration: " + i.getStartDate() + " to " + i.getEndDate());
                        System.out.println("Company: " + i.getCompanyName());
                        System.out.println("Supervisor: " + i.getSupervisor().getFullName() + " (" + i.getSupervisor().getQualification() + ")");
                        System.out.println("Status: " + i.getStatus());
                        System.out.println("Progress Notes: In Progress");
                        System.out.println("Completion Status: Pending");
                        System.out.println("---------------------------");
                    }
                    break;
                case 5:
                    System.out.print("Enter Student Full Name to search: ");
                    String searchName = input.nextLine();
                    for (Internship i : internships) {
                        if (i.getStudent().getFullname().equalsIgnoreCase(searchName)) {
                            System.out.println("Found Internship for: " + i.getStudent().getFullname());
                            System.out.println("Company: " + i.getCompanyName());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter University to search: ");
                    String searchUniversity = input.nextLine();
                    for (Internship i : internships) {
                        if (i.getStudent().getUniversity().equalsIgnoreCase(searchUniversity)) {
                            System.out.println("Internship for: " + i.getStudent().getFullname() + " at " + i.getCompanyName());
                        }
                    }
                    break;
                case 7:
                    System.out.println("Exiting Program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

