package parking_lot;

public class Vehicle {
    private final String car_number;
    private final int costFactor;
    @Override
	public String toString() {
		return "Vehicle [car_number=" + car_number + ", costFactor=" + costFactor + "]";
	}
	public String getCar_number() {
		return car_number;
	}
	public Vehicle(String car_number, int costFactor) {
        super();
        this.car_number = car_number;
        this.costFactor = costFactor;
    }
    public int getCostFactor() {
        return costFactor;
    }
}
