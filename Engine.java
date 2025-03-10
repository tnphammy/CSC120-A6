public class Engine {

    private FuelType fuel; // must be one of STEAM, INTERNAL_COMBUSTION, ELECTRIC, OTHER;
    private Double fuelLevel;
    private Double fuelCapacity;

    public Engine(FuelType fuel, Double fuelCapacity) {
        this.fuel = fuel;
        this.fuelCapacity = fuelCapacity;
        this.fuelLevel = this.fuelCapacity; // Start off with a full tank 
    }

    public FuelType getFuelType() {
        return this.fuel;
    }

    public Double getFuelLevel() {
        return this.fuelLevel;
    }

    public void refuel() {
        this.fuelLevel = this.fuelCapacity;
    }

    public void go() {
        if (this.fuelLevel <= 0) {
            throw new RuntimeException("Out of fuel!");
        }
        this.fuelLevel -= 0.1 * this.fuelCapacity; // Decrease fuel by 10%
        System.out.print("We are moving!");
        System.out.println(" - " + this.fuel + " fuel is at " + this.getFuelLevel());
    }

    public static void main(String[] args) {
        Engine myEngine = new Engine(FuelType.ELECTRIC, 100.0);
        try {
            while (true) {
                myEngine.go();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}