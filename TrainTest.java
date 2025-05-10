import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TrainTest {

    // Intializing
    private Engine testEngine;
    private Car testCar;
    private Car testFullCar;
    private Passenger tammy;
    private Train testTrain;

    // Engine 
    @Before
    public void initializeEngine(){
        testEngine = new Engine(FuelType.STEAM, 200.0);
    }
    @Test
    public void engineShouldInitialize(){
        assertEquals(FuelType.STEAM, testEngine.getFuelType());
    }

    @Test
    public void engineShouldGo() {
        testEngine.go();
        double fuelAfterGoing = testEngine.getFuelLevel();
        assertEquals(200 - (200 * 0.1),  fuelAfterGoing, 0.0);
    }


    // Car 
    @Before
    public void initializeCar() {
        testCar = new Car(20);
    }

    public void initializeFullCar() {
        testFullCar = new Car(0);
    }

    @Test
    public void addPassengerTest() {
        testCar.addPassenger(tammy);

        // Get the passenger count through seatsRemaining
        int newSeatsRemaining = testCar.seatsRemaining();

        assertEquals(19, newSeatsRemaining);

    }

    @Test
    public void removePassengerTest() {
        testCar.addPassenger(tammy);
        testCar.removePassenger(tammy);

        // Get the passenger count through seatsRemaining
        int newSeatsRemaining = testCar.seatsRemaining();

        assertEquals(20, newSeatsRemaining);
    }

    // Passenger
    @Before
    public void initializePassenger() {
        tammy = new Passenger("Tammy");
    }

    @Test
    public void boardCarTest() {
        tammy.boardCar(testCar);

        // Get the passenger count through seatsRemaining
        int newSeatsRemaining = testCar.seatsRemaining();

        assertEquals(19, newSeatsRemaining);


    }

    @Test(expected = RuntimeException.class)
    public void boardFullCarTest() {
        initializeFullCar();
        tammy.boardCar(testFullCar);
    }

    // Train
    @Before 
    public void initializeTrain() {
        testTrain = new Train(FuelType.STEAM, 100, 4, 40);
    }

    @Test
    public void carCountTest() {
        int newCarCount = testTrain.getCarCount();

        assertEquals(4, newCarCount, 0.0);

    }

    @Test
    public void passengerCountTest() {
        
    }





   


    
}
