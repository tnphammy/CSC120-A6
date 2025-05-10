public class Passenger {
    
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void boardCar(Car c) {
        c.addPassenger(this);
        
        if (c.seatsRemaining() == 0) {
            throw new RuntimeException(this.name + " says: 'Guess I'll wait for the next one..'.'");
        }
    }

    public void getOffCar(Car c) {
        try {
            c.removePassenger(this);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(this.name + " says: 'Guess I'll wait for the next one...'");
        }
    }

}
