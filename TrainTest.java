import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainTest {

    // Initialize the Engine
    private static Engine engine;
    private static double fuelCapacity;

    @Before
    public static void setUp() {
        fuelCapacity = 100.0;
        engine = new Engine(FuelType.ELECTRIC, fuelCapacity);
    }


    // Engine Tests

    @Test
    public void testEngineConstructor() {

        //Test 1: Verify that the `Engine` constructor initializes correctly 
        //with the given `FuelType` and fuel level.

        assertEquals(FuelType.ELECTRIC,engine.getFuelType()); //FuelType is correct
        assertEquals(fuelCapacity, engine.getFuelLevel().doubleValue(), 0.0); //Fuel levels are at FULL capacity at the start

    }

    @Test
    public void testEngineGo() {

        //Test 2:  Test the `go()` method to ensure that 
        //fuel consumption reduces the fuel level correctly.

        engine.go(); //Move the engine once
        assertEquals(fuelCapacity - fuelCapacity * 0.1, engine.getFuelLevel(), 0.0);

        engine.go(); //Move the engine a second time
        assertEquals(fuelCapacity - 2 * (fuelCapacity * 0.1), engine.getFuelLevel(), 0.0);

    }

    // Car Tests
    @Test
    public void testCarAddPassenger() {

        //Initialize the Car 
        int passengerCapacity;
        fail();
    }

    @Test
    public void testCarRemovePassenger() {
        fail();
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
