import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainTest {

    // Initialize the Engine
    private static Engine engine;
    private static double fuelCapacity;

    // Initialize the Car & Passenger
    private static Car car;
    private ArrayList<Passenger> passengers;
    private Passenger tammy;
    private int passengerCapacity;

    // Initialize the Passenger
    private String name;

    
    @Before
    public void setUp() {
        fuelCapacity = 100.0;
        engine = new Engine(FuelType.ELECTRIC, fuelCapacity);
    }

    // Engine Tests

    @Test
    public void testEngineConstructor() {

        // Test 1: Verify that the `Engine` constructor initializes correctly 
        // with the given `FuelType` and fuel level.

        assertEquals(FuelType.ELECTRIC,engine.getFuelType()); //FuelType is correct
        assertEquals(fuelCapacity, engine.getFuelLevel().doubleValue(), 0.0); //Fuel levels are at FULL capacity at the start

    }

    @Test
    public void testEngineGo() {

        // Test 2:  Test the `go()` method to ensure that 
        // fuel consumption reduces the fuel level correctly.

        engine.go(); // Move the engine once
        assertEquals(fuelCapacity - fuelCapacity * 0.1, engine.getFuelLevel(), 0.0);

        engine.go(); // Move the engine a second time
        assertEquals(fuelCapacity - 2 * (fuelCapacity * 0.1), engine.getFuelLevel(), 0.0);

    }

    // Car Tests
    @Before
    public void setup() {
        passengerCapacity = 20;
        car = new Car(passengerCapacity);
        passengers = new ArrayList<Passenger>(passengerCapacity);
        String passengerName = "Tammy";
        tammy = new Passenger(passengerName);
    }
    @Test
    public void testCarAddPassenger() {

        // Test 1: Ensure that adding a `Passenger` increases the passenger count.

        int initialSeatsRemaining = car.seatsRemaining(); // Get the initial number of seats remaining

        car.addPassenger(tammy); // Add one passenger

        assertTrue(car.seatsRemaining() < initialSeatsRemaining); // Check to see if the passenger count has increased.
        //by looking at the seats remaining
        


    }

    @Test
    public void testCarRemovePassenger() {

        // Test 2: Ensure that removing a `Passenger` decreases the passenger count.

        car.addPassenger(tammy); // Add one passenger

        int initialSeatsRemaining = car.seatsRemaining(); 

        car.removePassenger(tammy); // Add one passenger

        assertTrue(car.seatsRemaining() > initialSeatsRemaining); // Checks to see if the passenger count has increased.
        //by looking at the seats remaining

    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        fail();
    }

    @Test
    public void testPassengerBoardCarFull() {
        fail();
    }

    // Train Tests
    @Test
    public void testTrainConstructor() {
        fail();
    }

    @Test
    public void testTrainPassengerCount() {
        fail();
    }

    @Test
    public void testTrainGetCar() {
        fail();
    }

    @Test
    public void testTrainPrintManifest() {
        fail();
    }
    
}
