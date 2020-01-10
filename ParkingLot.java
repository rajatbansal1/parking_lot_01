package parking_lot;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeSet;

public class ParkingLot {
    private final TreeSet<Integer> freeParkingSlots = new TreeSet<>();
    private final TreeSet<Integer> allParkingSlots = new TreeSet<>();
    private final Map<Integer, Vehicle> parkingVehicles = new HashMap<>();
    private static final CostCalculator CALCULATOR = new CostCalculator();

        public Map<Integer, Vehicle> getParkingVehicles() {
		return parkingVehicles;
	}

	public ParkingLot(int numberOfSlots) {
        Random random = new Random();
        for (int i = 0; i < numberOfSlots; i++) {
            allParkingSlots.add(i);
        }
        // all slots initially free
        freeParkingSlots.addAll(allParkingSlots);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if(!freeParkingSlots.isEmpty()) {
    	int freeSlot=freeParkingSlots.first();
        freeParkingSlots.remove(freeSlot);
        parkingVehicles.put(freeSlot, vehicle);
        System.out.println("Allocated slot number:"+ (freeSlot+1));
        return new Ticket(vehicle);
        }
        else {
        	System.out.println("Sorry, parking lot is full");
        	return null;
        }
    }

    public int unparkVehicle(Ticket ticket, int duration) {
    	int index=0;
    	if(freeParkingSlots.size()!=parkingVehicles.size()) {
        	for(int i=0;i<parkingVehicles.size();i++) {
        		if(ticket.getVehicle().getCar_number().equals(parkingVehicles.get(i).getCar_number())) {
        			parkingVehicles.remove(i);
        			Vehicle v=new Vehicle(null, 0);
        			parkingVehicles.put(i, v);
        			index=i;
        			break;
        		}
        	}
    		freeParkingSlots.add(index);
    		System.out.print("Regitration number "+ticket.getVehicle().getCar_number()+" with slot number "+(index+1) + " is free ");
            }
            else {
            	System.out.println("Parking is empty");
            }
        return ticket.calculateCost(CALCULATOR, duration);
    }
}