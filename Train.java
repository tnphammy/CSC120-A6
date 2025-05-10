import java.util.ArrayList;

public class Train {
    
    // In Java, we use the keyword "final" to establish a composition relationship
    private final Engine engine; 
    private ArrayList<Car> cars;
    private int carCount;

    public Train(FuelType fuelType, double fuelCapacity, int nCars, int passengerCapacity) {
        this.engine = new Engine(fuelType,fuelCapacity);
        this.cars = new ArrayList<Car>();
        this.carCount = 0;
        for (int i = 0; i < nCars; i ++) {
            this.cars.add(new Car(passengerCapacity));
            this.carCount += 1;
        }
    }
    public int getCarCount() {
        return this.carCount;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Car getCar(int i) {
        return this.cars.get(i-1); // throws IndexOutOfBoundsException if i > number of cars
    }

    public int getMaxCapacity() {
        int maxCapacity = 0;
        for (Car c : this.cars) {
            maxCapacity += c.getCapacity();
        }
        return maxCapacity;
    }

    public int seatsRemaining() {
        int seatsRemaining = 0;
        for (Car c : this.cars) {
            seatsRemaining += c.seatsRemaining();
        }
        return seatsRemaining;
    }

    public String toString() {
        return "ABOUT THIS TRAIN:"
            .concat("\n")
            .concat("  Engine:")
            .concat("\n")
            .concat("     Fuel Type:  " + this.engine.getFuelType())
            .concat("\n")
            .concat("     Fuel Level: " + this.engine.getFuelLevel())
            .concat("\n")
            .concat("  Number of Passenger Cars: " + this.cars.size())
            .concat("\n")
            .concat("  Seats Available: " + this.seatsRemaining() + "/" + this.getMaxCapacity());
    }

    public void printManifest() {
        for (Car c : this.cars) {
            System.out.println("PASSENGER CAR #" + (this.cars.indexOf(c)+1));
            c.printManifest();
        }
    }

    public static void main(String[] args) {
        Train myTrain = new Train(FuelType.STEAM, 100, 2, 40);
        //System.out.println(myTrain);
        Passenger me = new Passenger("Tammy");
        me.boardCar(myTrain.getCar(1));
        myTrain.printManifest();
    }

}
