package parking_lot;

public class Ticket {

    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
    }
   
    public int calculateCost(CostCalculator calculator, int duration){
        return calculator.getCost(duration, vehicle.getCostFactor());
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

	@Override
	public String toString() {
		return "Ticket [vehicle=" + vehicle + "]";
	}
    
}