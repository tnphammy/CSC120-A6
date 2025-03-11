import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainTest {

    // Initialize the `Engine`
    private static Engine engine;
    private static double fuelCapacity;

    // Initialize the `Car` & `Passenger`
    private Car car;
    private ArrayList<Passenger> passengers; // I don't know if I need this here...
    private Passenger tammy;
    private int passengerCapacity;

    // Initialize the `Passenger`
    private String name;

    // Initialize the `Train`
    //private final Engine engine; DO I NEED THIS   
    private Train train;
    private ArrayList<Car> cars;
    private int nCars;
    private Car car1;
    private Car car2;

    
    @Before
    public void setUpEngine() {
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
    public void setUpCar() {
        passengerCapacity = 20;
        car = new Car(passengerCapacity);
        passengers = new ArrayList<Passenger>(passengerCapacity);
        String passengerName = "Tammy";
        tammy = new Passenger(passengerName);
    }
    @Test
    public void testCarAddPassenger() {

        // Test 1: Ensure that adding a `Passenger` increases the passenger count.

        // Get the initial number of seats remaining
        int initialSeatsRemaining = car.seatsRemaining(); 

        // Add one passenger & Get the Boolean value
        boolean added = car.addPassenger(tammy);

        // Check if passenger was added sucessfully
        assertTrue(added); 

        // Check to see if the passenger count has INCREASED.
        // by seeing if the seats remaining DECREASED.
        assertEquals(car.seatsRemaining(), initialSeatsRemaining - 1);


    }

    @Test
    public void testCarRemovePassenger() {

        // Test 2: Ensure that removing a `Passenger` decreases the passenger count.

        // Get one passenger on board
        car.addPassenger(tammy);

        // Get the initial number of seats remaining
        int initialSeatsRemaining = car.seatsRemaining(); 

        // Add one passenger & Get the Boolean value
        boolean removed = car.removePassenger(tammy);

        // Check if passenger was added sucessfully
        assertTrue(removed); 

        // Check to see if the passenger count has DECREASED.
        // by seeing if the seats remaining INCREASED.
        assertEquals(car.seatsRemaining(), initialSeatsRemaining + 1);

    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        // Test 1: Test that a `Passenger` can successfully board a `Car`.

        // Get initial number of seats remaining
        int initialSeatsRemaining = car.seatsRemaining();

        // Get a `Passenger` to board a car
        tammy.boardCar(car);

        // Check if the number of seats remaining INCREASED
        assertEquals(car.seatsRemaining(), initialSeatsRemaining - 1);

        //

    }

    @Test(expected = RuntimeException.class)
    public void testPassengerBoardCarFull() {
        // Test 2: Ensure that a `Passenger` cannot board a full `Car`.

        // Fill up the `Car` with dummy passengers (I'm not about to manually add 20 people :) )
        for(int i = 0; i < car.getCapacity(); i++) {
            Passenger randomPassenger = new Passenger("Rando");
            randomPassenger.boardCar(car);
        }

        // Try to board one extra `Passenger`/Add them to the `Car`
        car.addPassenger(tammy);

    }

    // Train Tests
    @Before
    public void setUpTrain() {
        fuelCapacity = 100.0;
        nCars = 5;
        passengerCapacity = 20;
        train = new Train(FuelType.INTERNAL_COMBUSTION, fuelCapacity, nCars, passengerCapacity);
        cars = new ArrayList<>();
        car1 = train.getCar(1);
        car2 = train.getCar(2);
    }
    @Test
    public void testTrainConstructor() {
        // Test 1: Verify that a `Train` initializes correctly with a given number of `Car`s.
        
        // Check the `FuelType`
        assertEquals(FuelType.INTERNAL_COMBUSTION, train.getEngine().getFuelType());

        //Check the `fuelCapacity` (should be at the MAX capacity at the start)
        assertEquals(fuelCapacity, train.getEngine().getFuelLevel(), 0.0);

        //Check the `nCars`
        assertEquals(nCars, cars.size(), 0);

        // Check that each Car exists by using getCar() method
        for (int i = 1; i <= nCars; i++) {
            assertNotNull("Car " + i + " should not be null", train.getCar(i));
        }

        // Check the `passengerCapacity`
        //assertEquals(passengerCapacity * nCars, train.getMaxCapacity(), 0);
        //THIS IS NOT CORRECT

    }

    @Test
    public void testTrainPassengerCount() {
        // Test 2: Ensure the `Train`’s total `Passenger` count updates as `Passenger`s board and leave.
        // Get initial number of seats
        int initialSeats = train.seatsRemaining();

        // Board one passenger in the first car
        tammy.boardCar(train.getCar(1));

        // Check to see if the remaining seats DECREASE by 1
        assertEquals(initialSeats - 1, train.seatsRemaining());
        
        // Passenger gets off
        tammy.getOffCar(train.getCar(1));

        // Seats remaining now should = initial value.
        assertEquals(initialSeats, train.seatsRemaining());
    }
    

    @Test
    public void testTrainGetCar() {

        // Test 3: Check that the `getCar(int i)` method returns the expected `Car`.

        // Check that their capacities match the expected passenger capacity.
        assertEquals(passengerCapacity, car1.getCapacity());
        assertEquals(passengerCapacity, car2.getCapacity());

        // Board a passenger and check that it affects ONLY that car.
        tammy.boardCar(car1);

        // car1 seats remaining should DECREASE, car2 should remain the same.
        assertTrue(car1.seatsRemaining() < passengerCapacity);
        assertEquals(passengerCapacity, car2.seatsRemaining());
    }

    @Test
    public void testTrainPrintManifest() {
        // Test 4: Test the `printManifest()` method to ensure it iterates through the `Train`s `Car`s correctly.
        // Board a unique passenger into each car.
        for (int i = 1; i <= nCars; i++) {
            Passenger p = new Passenger("Passenger Number" + i);
            p.boardCar(train.getCar(i));
        }

 
    }
    
}
