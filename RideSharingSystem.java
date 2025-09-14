import java.util.Scanner;

// Custom Exception for invalid ride types
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract base class
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Encapsulation through getters
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method for polymorphism
    public abstract double calculateFare();
}

// Subclass for Bike rides
class BikeRide extends Ride {
    public BikeRide(String driver, String vehicle, double distance) {
        super(driver, vehicle, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 10; // ₹10 per km
    }
}

// Subclass for Car rides
class CarRide extends Ride {
    public CarRide(String driver, String vehicle, double distance) {
        super(driver, vehicle, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 20;
    }
}


public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter ride type (bike/car): ");
            String type = sc.nextLine().trim().toLowerCase();

            System.out.print("\nEnter distance (km): ");
            double distance = sc.nextDouble();

            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be positive.");
            }

            Ride ride;

        
            if (type.equals("bike")) {
                ride = new BikeRide("Ravi Kumar", "KA-19-B1234", distance);
            } else if (type.equals("car")) {
                ride = new CarRide("Anita Sharma", "KA-05-C5678", distance);
            } else {
                throw new InvalidRideTypeException("Only 'bike' or 'car' rides are allowed.");
            }

       
            System.out.println("\n--- Ride Details ---");
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}


