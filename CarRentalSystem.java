import java.util.*;

class CarStatusException extends Exception
{
	public CarStatusException(String str)
	{
		super(str);
	}
}

class CarRentalSystem
 {
     List<Car> cars;

    public CarRentalSystem()
	{
        cars = new ArrayList<>();
    }

    public void addCar(Car car)
	{
        cars.add(car);
    }
	public int numberOfCars()
	 {
		return cars.size();
	 }

    public double rentCar(int carIndex,int days) throws CarStatusException
	{
        if (carIndex >= 1 && carIndex <= cars.size()) 
		{
            Car car = cars.get(carIndex-1);
            if (car.isAvailable())
			{
                car.setAvailable(false);
				double totalPrice = car.getPricePerDay() * days;
                System.out.println("Car rented successfully:\n");
				
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
				System.out.println("| Index    | Make           | Model          | Year | Transmission    | Seating Capacity| Fuel Type | Price per Day |");
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
				System.out.printf("| %-8d | %-14s | %-14s | %-4d | %-15s | %-15d | %-9s | $%-12.2f |\n",
								car.getIndex(), car.getMake(), car.getModel(), car.getYear(), car.getTransmissionType(),
								car.getSeatingCapacity(), car.getFuelType(), car.getPricePerDay());
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");


				System.out.println("Total Price for " + days + " days: $" + totalPrice);
                return totalPrice;
            }
			else
			{
                throw new CarStatusException("Sorry, the car is not available for rent.");
            }
        } 
		else
		{
            System.out.println("Invalid car index.");
        }
		return 0.0;
    }

    public void returnCar(int carIndex,int days) throws CarStatusException
	{
        if (carIndex >= 1 && carIndex <= cars.size())
		{
            Car car = cars.get(carIndex-1);
            if (!car.isAvailable())
			{
                car.setAvailable(true);
				double totalPrice1 = car.getPricePerDay() * days;
                System.out.println("Car returned successfully:\n");
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
				System.out.println("| Index    | Make           | Model          | Year | Transmission    | Seating Capacity| Fuel Type | Price per Day |");
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
				System.out.printf("| %-8d | %-14s | %-14s | %-4d | %-15s | %-15d | %-9s | $%-12.2f |\n",
								car.getIndex(), car.getMake(), car.getModel(), car.getYear(), car.getTransmissionType(),
								car.getSeatingCapacity(), car.getFuelType(), car.getPricePerDay());
				System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
				System.out.println("Paid Successfully! for " + days + " days: $" + totalPrice1);

            } 
			else 
			{
				throw new CarStatusException("Sorry!,The Car is not rented.");
            }
        } 
		else 
		{
            System.out.println("Invalid car index.");
        }
    }

     public void displayAvailableCars() {
        System.out.println("Available Cars:");
        System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
		System.out.println("| Index    | Make           | Model          | Year | Transmission    | Seating Capacity| Fuel Type | Price per Day |");
		System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
		  for (int i = 1; i <= cars.size(); i++)
			{
				Car car = cars.get(i - 1);
				if (car.isAvailable()) 
				{
					System.out.printf("| %-8d | %-14s | %-14s | %-4d | %-15s | %-15d | %-9s | $%-12.2f |\n",
							car.getIndex(), car.getMake(), car.getModel(), car.getYear(), car.getTransmissionType(),
							car.getSeatingCapacity(), car.getFuelType(), car.getPricePerDay());
				}
			}
			System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
		}


    public List<Car> filterCars(String make, String model, int year, String transmissionType, int seatingCapacity, String fuelType) {
       
		List<Car> filteredCars = new ArrayList<>();

        for (Car car : cars) 
		{
            if (car.isAvailable() &&
                    (car.getMake().equalsIgnoreCase(make) ||
                            car.getModel().equalsIgnoreCase(model) ||
                            car.getYear() == year ||
                            car.getTransmissionType().equalsIgnoreCase(transmissionType) ||
                            car.getSeatingCapacity() == seatingCapacity ||
                            car.getFuelType().equalsIgnoreCase(fuelType))) 
			{
					filteredCars.add(car);
            }
        }
		return filteredCars;
	}
		
}