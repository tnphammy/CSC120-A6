import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;


public class TrainTest {

    // Intializing
    private Engine testEngine;
    private Car testCar;
    private Car testFullCar;
    private Passenger tammy;
    private Train testTrain;
    private Car carOne;
    private Car carTwo;


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
        testTrain = new Train(FuelType.STEAM, 100, 2, 40);
    }

    @Test
    public void carCountTest() {
        int newCarCount = testTrain.getCarCount();

        assertEquals(2, newCarCount, 0.0);

    }

    @Test
    public void passengerCountTest() {
        carOne = testTrain.getCar(1);

        //Passenger boards first car of testTrain
        tammy.boardCar(carOne);

        // Access passenger count through seats remaining
        int newPassengerCount = testTrain.seatsRemaining();

        assertEquals((2 * 40 - 1), newPassengerCount);

        // Passenger leaves car
        tammy.getOffCar(carOne);

        // Access passenger count through seats remaining
        int newestPassengerCount = testTrain.seatsRemaining();

        assertEquals((2 * 40), newestPassengerCount);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getCarTest() {
        // Should throw error when using wrong car index (Only accepts 1 - 4)
        carOne = testTrain.getCar(0);

        carTwo = testTrain.getCar(5);

    }

    @Test
    public void getCarNumberTest() {
        carOne = testTrain.getCar(1);

        assertTrue(carOne == testTrain.getCar(1));
    }

    @Test
    public void printManifestTest() {
        // Put one passenger (Tammy) into carOne
        carOne = testTrain.getCar(1);
        tammy.boardCar(carOne);

        // Call ByteArrayOutputStream to get the printing to the console (I found this online)
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Let it print
        testTrain.printManifest();

        // Reset
        System.setOut(System.out);

        String expectedPrint = "PASSENGER CAR #1\n" + //
                        "Passengers aboard this car:\n" + //
                        " - Tammy\n" + //
                        "PASSENGER CAR #2\n" + //
                        "This car is EMPTY.\n";

        String realPrint = outContent.toString();
        assertEquals(expectedPrint, realPrint);
    }




   


    
}
