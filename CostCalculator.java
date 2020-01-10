package parking_lot;

public class CostCalculator {
    public int getCost(int parkingDuration, int costFactor) {
        if(parkingDuration<=2)
    	return parkingDuration * costFactor;
        else
        	return ((parkingDuration * costFactor)-10);
    }
}
