package parking_lot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ParkingLotManager {
    private final ParkingLot parkingLot;
    static String[] vehicleTypes = {"park KA-01-HH-1234", "park KA-01-HH-99","park KA-01-HH-0001","park KA-01-HH-7777","park KA-01-HH-2701","park KA-01-HH-3141","leave KA-01-HH-3141 4","status","park KA-01-P-333","park DL-12-AA-9999","leave KA-01-HH-1234 4","leave KA-01-HH-0001 6","leave DL-12-AA-9999 2","park KA-09-HH-0987","park CA-09-IO-1111","park KA-09-HH-0123","status"};
    public ParkingLotManager(int numberOfSlots) {
        parkingLot = new ParkingLot(numberOfSlots);
    }

    public static void main(String[] args) {
    	
    	Scanner sc=new Scanner(System.in);
        System.out.println("create_parking_lot");
    	int numberOfSlots = sc.nextInt();
    	System.out.println("Created parking lot with "+numberOfSlots+" Slot");
    	new ParkingLotManager(numberOfSlots).start(vehicleTypes.length);
        
    }

    private void start(int numberOfIterations) {
        List<Ticket> tickets = new ArrayList<>();
        int duration= 0;
        //int counter=0;
        for (int i = 0; i < numberOfIterations; i++) {
            String[] vehicle=vehicleTypes[i].split(" ");
            if(vehicle[0].equals("park")) {
                try {
                    parkNewVehicle(tickets, i);
                    //counter++;
                } catch (Exception e) {
                    System.out.println("Sorry, parking lot is full");
                }
            }
            else if(vehicle[0].equals("leave")) {
            	int fees=0;
            	boolean flag=false;
            	duration=Integer.parseInt(vehicle[2]);
            	for(int j=0;j<tickets.size();j++) {
            		String[] car_number=tickets.get(j).getVehicle().getCar_number().split(" ");
            		if(car_number[1].equals(vehicle[1])) {
            			fees = parkingLot.unparkVehicle(tickets.get(j), duration);
            			System.out.println("with Charge "+fees);
                    	tickets.remove(j);
                    	//counter--;
                    	flag=true;
            			break;
            		}
            	}
            	if(!flag) {
            		System.out.println("Regitration number "+vehicle[1]+" not found");
            	}
            } 
            else {
            	System.out.println("Slot No.   Registration No.");
            	Map<Integer, Vehicle> parkingVehicles=parkingLot.getParkingVehicles();
            	for(int num=0;num<parkingVehicles.size();num++) {
            	if(parkingVehicles.get(num).getCar_number()!=null)
            		{
            			String[] car_number=parkingVehicles.get(num).getCar_number().split(" ");
            			System.out.println((num+1)+"        "+car_number[1]);
            		}
            	}
            }
        }
    }

    private void parkNewVehicle(List<Ticket> tickets, int i) {
    	 String vehicleType = vehicleTypes[i];
        int costFactor = 10;
        Ticket t=parkingLot.parkVehicle(new Vehicle(vehicleType, costFactor));
        if(t!=null)
        tickets.add(t);
    }
}
