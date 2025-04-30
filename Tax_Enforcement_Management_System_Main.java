package Advanced_Tax_Enforcement_Management_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Tax_Enforcement_Management_System_Main {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<TaxPayer> taxPayers = new ArrayList<TaxPayer>();
    private static ArrayList<TaxDeclaration> declarations = new ArrayList<TaxDeclaration>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n====== RRA Tax Enforcement Management System ======");
            System.out.println("1. Register Taxpayer");
            System.out.println("2. Declare PAYE");
            System.out.println("3. Declare VAT");
            System.out.println("4. Declare Withholding Tax");
            System.out.println("5. View Declarations");
            System.out.println("6. Audit Declarations");
            System.out.println("7. View Compliance History");
            System.out.println("8. Print Tax Receipts");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine(); // consume newline
            switch (choice) {
                case 1 -> registerTaxpayer();
                case 2 -> declarePAYE();
                case 3 -> declareVAT();
                case 4 -> declareWithholding();
                case 5 -> viewDeclarations();
                case 6 -> auditDeclarations();
                case 7 -> viewComplianceHistory();
                case 8 -> printReceipts();
                case 9 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");


            }


        }
    }

    private static void registerTaxpayer() {
        System.out.print("Enter TIN (9 digits): ");
        String tin = input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Type (Individual/Company): ");
        String type = input.nextLine();

        try {
            TaxPayer taxpayer = new TaxPayer(tin, name, type, 100);
            taxPayers.add(taxpayer);
            System.out.println("✅ Taxpayer registered successfully!");
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }

    private static void declarePAYE() {
        System.out.print("Enter Declaration ID: ");
        String id = input.nextLine();
        System.out.print("Enter Taxpayer TIN: ");
        String tin = input.nextLine();
        System.out.print("Enter Declaration Date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine());
        input.nextLine();
        System.out.print("Enter Gross Salary: ");
        double salary = input.nextDouble();
        input.nextLine();
        System.out.print("Is Tax Paid? (true/false): ");
        boolean paid = input.nextBoolean();
        input.nextLine();

        TaxPayer taxpayer = findTaxpayerByTIN(tin);
        if (taxpayer == null) {
            System.out.println("⚠️ Taxpayer not found. Please register first.");
            return;
        }
        PAYE_Declaration paye = new PAYE_Declaration(id, taxpayer.getName(), tin, date, salary, paid);

        try {
            paye.validateDeclaration();
            paye.calculateTax();
            declarations.add(paye);
            System.out.println("✅ PAYE Declaration created successfully!");
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }


    }




    private static void declareVAT() {
        System.out.print("Enter Declaration ID: ");
        String id = input.nextLine();
        System.out.print("Enter Taxpayer TIN: ");
        String tin = input.nextLine();
        System.out.print("Enter Declaration Date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine());
        System.out.println("enter tax amount:");
        double taxAmount = input.nextDouble();
        input.nextLine();
        System.out.print("Enter Sales: ");
        double sales = input.nextDouble();
        input.nextLine();
        System.out.println("enter non-taxable sales :");
        double non_taxableSales = input.nextDouble();
        input.nextLine();
        System.out.print("Enter Purchases: ");
        double purchases = input.nextDouble();
        input.nextLine();
        System.out.print("Is Tax Paid? (true/false): ");
        boolean paid = input.nextBoolean();
        input.nextLine();

        TaxPayer taxpayer = findTaxpayerByTIN(tin);
        if (taxpayer == null) {
            System.out.println("⚠️ Taxpayer not found. Please register first.");
            return;
        }

        VAT_Declaration vat = new VAT_Declaration(id,taxpayer.getName(),tin,date,taxAmount,paid,non_taxableSales,purchases,sales);
        try {
            vat.validateDeclaration();
            vat.calculateTax();
            declarations.add(vat);
            System.out.println("✅ VAT Declaration created successfully!");
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }

    private static void declareWithholding() {
        System.out.print("Enter Declaration ID: ");
        String id = input.nextLine();
        System.out.print("Enter Taxpayer TIN: ");
        String tin = input.nextLine();
        System.out.print("Enter Declaration Date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine());
        input.nextLine();
        System.out.println("enter tax amount: ");
        double taxAmount=input.nextDouble();
        input.nextLine();
        System.out.print("Enter Income Type (rent/services/dividends): ");
        String type = input.nextLine();
        System.out.print("Enter Income Amount: ");
        double amount = input.nextDouble();
        System.out.print("Is Tax Paid? (true/false): ");
        boolean paid = input.nextBoolean();
        input.nextLine();

        TaxPayer taxpayer = findTaxpayerByTIN(tin);
        if (taxpayer == null) {
            System.out.println("⚠️ Taxpayer not found. Please register first.");
            return;
        }

        Withholding_Tax_Declaration withholding = new Withholding_Tax_Declaration(id, taxpayer.getName(), tin, date, taxAmount,paid, type, amount);
        try {
            withholding.validateDeclaration();
            withholding.calculateTax();
            declarations.add(withholding);
            System.out.println("✅ Withholding Tax Declaration created successfully!");
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }
    private static void viewDeclarations() {
        if (declarations.isEmpty()) {
            System.out.println("⚠️ No tax declarations available.");
        } else {
            for (TaxDeclaration decl : declarations) {
                System.out.println(decl.getDeclarationId() + " | " + decl.getTaxpayerName() + " | " + decl.getTaxAmount() + " | Paid: " + (decl.isPaid() ? "Yes" : "No"));
            }
        }
    }

    private static void auditDeclarations() {
        System.out.print("Enter Officer ID: ");
        String id = input.nextLine();
        System.out.print("Enter Officer Full Name: ");
        String name = input.nextLine();
        System.out.print("Enter Assigned Region: ");
        String region = input.nextLine();

        Taxofficer officer = new Taxofficer(id, name, region);

        for (TaxDeclaration decl : declarations) {
            TaxPayer taxpayer = findTaxpayerByTIN(decl.getTaxpayerTIN());
            if (taxpayer != null) {
                officer.auditDeclaration(decl, taxpayer);
            }
        }

        officer.generateAuditSummary();
    }

    private static void viewComplianceHistory() {
        System.out.print("Enter TIN to view history: ");
        String tin = input.nextLine();
        TaxPayer taxpayer = findTaxpayerByTIN(tin);
        if (taxpayer != null) {
            taxpayer.printComplianceHistory();
        } else {
            System.out.println("Taxpayer not found.");
        }
    }

    private static void printReceipts() {
        for (TaxDeclaration decl : declarations) {
            decl.generateReceipt();
        }
    }







    private static TaxPayer findTaxpayerByTIN(String tin) {
        for (TaxPayer t : taxPayers) {
            if (t.getTin().equals(tin)) {
                return t;
            }
        }
        return null;
    }

}



