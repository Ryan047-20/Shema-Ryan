package Vehicle_Tax_Management_System;

import java.util.ArrayList;
import java.util.Scanner;

public class Vehicle_tax_management_Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
       while (true){
           System.out.println("1.register vehicle");
           System.out.println("2.view the registered vehicle");
           System.out.println("3.calculate its tax");
           System.out.println("4.generate its tax report");
           System.out.println("5.exit");
           System.out.println("choose an option");
           int option =input.nextInt();
           input.nextLine();
           switch (option){
               case 1:
                   //Ask
                   System.out.println("enter the vehicle type please:");
                   String VehicleType = input.nextLine().trim();
                   //validates
                  if (!VehicleType.equalsIgnoreCase("Car")&&!VehicleType.equalsIgnoreCase("Truck")
                  &&!VehicleType.equalsIgnoreCase("motorcycle")&&!VehicleType.equalsIgnoreCase("bus")&&!VehicleType.equalsIgnoreCase("suv"))
                  {
                      System.out.println("invalid vehicle type try again");
                      break;
                  }
                  //collect
                   System.out.println("enter vehicleId: ");
                  String id= input.nextLine().trim();
                   System.out.println("enter registration ID: ");
                   String regId=input.nextLine().trim();
                   //check first of all for this duplicates
                   boolean dos =vehicles.stream().anyMatch(v->v.getVehicleID().equalsIgnoreCase(id)||v.getRegistrationNumber().equalsIgnoreCase(regId));
                   if(dos){
                       System.out.println("please every id and reg number are unique stop duplicates");
                   break;
                   }
                   System.out.println("enter owner name: ");
                   String name=input.nextLine().trim();
                   System.out.println("enter year of fabrication: ");
                   int year = input.nextInt();
                   input.nextLine();
                   System.out.println("enter base tax rate: ");
                   double tax = input.nextDouble();
                   input.nextLine();
                   try {
                       switch (VehicleType.toLowerCase()){
                           case "car":
                               System.out.println("is it electric: ");
                               boolean isElectric = input.nextBoolean();
                               input.nextLine();
                               vehicles.add(new Car(id,name,year,regId,tax,VehicleType,isElectric));
                               System.out.println("car is registered");
                               break;
                           case "truck":
                               System.out.println("enter the load capacity: ");
                               double load=input.nextDouble();
                               input.nextLine();
                               vehicles.add(new Truck(id,name,year,regId,tax,VehicleType,load));
                               System.out.println("truck is registered");
                               break;
                           case "motorcycle":
                               System.out.println("enter your engine capacity: ");
                               int engine = input.nextInt();
                               input.nextLine();
                               vehicles.add(new Motocycle(id,name,year,regId,tax,VehicleType,engine));
                               System.out.println("bike is registered");
                               break;
                           case "bus":
                               System.out.println("enter passenger capacity: ");
                               int pasCapacity = input.nextInt();
                               input.nextLine();
                               vehicles.add(new Bus(id,name,year,regId,tax,VehicleType,pasCapacity));
                               System.out.println("bus is registered");
                               break;
                           case "suv":
                               System.out.println("is your SUV a four wheel: ");
                               boolean fourwheels=input.nextBoolean();
                               input.nextLine();
                               vehicles.add(new SUV(id,name,year,regId,tax,VehicleType,fourwheels));
                               System.out.println("SUV Registered");
                               break;
                           default:
                               System.out.println("invalid vehicle type");
                       }
                   }catch (Exception e){
                       System.out.println("invalid input registration failed try again");
                       input.nextLine();
                   }


                   break;
               case 2:
                   if (vehicles.isEmpty()){
                       System.out.println("no vehicles where registered");
                   }else {
                       System.out.println(" the registered vehicles: ");
                       for (Vehicle v:vehicles){
                           System.out.println(v);
                       }
                   }
                   break;
               case 3:
                   if (vehicles.isEmpty()){
                       System.out.println("no vehicle to calculate tax for");
                   }else {
                       for (Vehicle v:vehicles){
                           System.out.println("vehicle:"+v.getVehicleID()+"tax:"+v.calculateTax());
                       }
                   }

                   break;
               case 4:
                   if (vehicles.isEmpty()){
                       System.out.println("no tax report");
                   }else {
                       for (Vehicle v:vehicles){
                           v.generateTaxReport();
                       }
                   }
                   break;
               case 5:
                   System.out.println("see you next time");
                   input.close();
                   break;
               default:
                   System.out.println("please try again");
           }
       }

    }
}




/*
 vehicles.add();
        vehicles.add();
        vehicles.add();
        vehicles.add();
        vehicles.add();
 */