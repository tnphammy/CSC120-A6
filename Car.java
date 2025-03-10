import java.util.ArrayList;

public class Car {
    
    private ArrayList<Passenger> passengers;
    private int passengerCapacity;

    public Car(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
        this.passengers = new ArrayList<Passenger>(passengerCapacity);
    }

    public int getCapacity() {
        return this.passengerCapacity;
    }

    public void addPassenger(Passenger p) {
        if (this.passengers.size() < this.passengerCapacity) {
            if (this.passengers.contains(p)) {
                throw new RuntimeException(p.getName() + " is already aboard this car!");
            } else {
                this.passengers.add(p);
            }
        } else {
            throw new RuntimeException("Car is full!");
        }
    }

    public void removePassenger(Passenger p) {
        if (this.passengers.contains(p)) {
            this.passengers.remove(p);
        } else {
            throw new RuntimeException(p.getName() + " is not onboard this car!");
        }
    }

    public int seatsRemaining() {
        return this.passengerCapacity - this.passengers.size();
    }

    public void printManifest(){
        if (this.passengers.size() >0) {
            System.out.println("Passengers aboard this car:");
            for (Passenger passenger : this.passengers) {
                if (passenger != null) {
                    System.out.println(" - " + passenger.getName());
                }
            }
        } else {
            System.out.println("This car is EMPTY.");
        }
    }

    public static void main(String[] args) {
        Car myCar = new Car(20);
        Passenger me = new Passenger("Jordan");
        System.out.println(myCar.seatsRemaining() + " seats remaining.");
        me.boardCar(myCar);
        myCar.printManifest();
        myCar.removePassenger(me);
        myCar.printManifest();
        System.out.println(myCar.seatsRemaining() + " seats remaining.");
        myCar.removePassenger(me); // throw error
    }

}