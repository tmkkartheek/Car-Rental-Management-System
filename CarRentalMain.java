import java.io.*;
import java.util.*;
public class CarRentalMain
 {
    public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
        CarRentalSystem carRentalSystem = new CarRentalSystem();
		loadCarDataFromFile(carRentalSystem, "carData.txt");

		System.out.println("");
		System.out.println("*******************************************************");
		System.out.println("*       Welocme to Car Rental Management System       *");
		System.out.println("*******************************************************");
		System.out.println("");
		System.out.print("Enter the number of days to Rent a Car: ");
        int days = scanner.nextInt();
		System.out.println("");

        while (true) 
		{
			System.out.println("");
            System.out.println("*****************************************");
            System.out.println("*         Car Rental System Menu        *");
            System.out.println("*****************************************");
            System.out.println("* 1. Display available cars             *");
            System.out.println("* 2. Filter cars by criteria            *");
            System.out.println("* 3. Rent a car                         *");
            System.out.println("* 4. Return a car                       *");
            System.out.println("* 5. Exit                               *");
            System.out.println("*****************************************");
			System.out.println("");
			System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
			System.out.println("");
			System.out.println("");
            switch (choice) 
			{
                case 1:
                    carRentalSystem.displayAvailableCars();
                    break;
                case 2:
                    while (true) 
					{
                        System.out.println("*********************************************************");
						System.out.println("*           Filter Cars by Criteria                     *");
						System.out.println("*********************************************************");
						System.out.println("* 0. Available Cars                                     *");
						System.out.println("* 1. Filter by make                                     *");
						System.out.println("* 2. Filter by model                                    *");
						System.out.println("* 3. Filter by year                                     *");
						System.out.println("* 4. Filter by transmission type [Automatic/Manual]     *");
						System.out.println("* 5. Filter by seating capacity [4/5/7]                 *");
						System.out.println("* 6. Filter by fuel type [Petrol/Diesel]                *");
						System.out.println("* 7. Go back                                            *");
						System.out.println("*********************************************************");

						System.out.println("");
						System.out.print("Enter your choice: ");
                        int filterChoice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("");
						
                        if (filterChoice == 7) 
						{
                            break;
                        }

                        String filterValue;
                        switch (filterChoice)
						{
							case 0:
                    				carRentalSystem.displayAvailableCars();
                    				break;
                            case 1:
                                System.out.print("Enter make: ");
                                filterValue = scanner.nextLine();
                                List<Car> makeFilteredCars = carRentalSystem.filterCars(filterValue,"", 0, "", 0, "");
                                displayFilteredCars(makeFilteredCars);
								break;
                            case 2:
                                System.out.print("Enter model: ");
                                filterValue = scanner.nextLine();
                                List<Car> modelFilteredCars = carRentalSystem.filterCars("", filterValue, 0, "", 0, "");
                                displayFilteredCars(modelFilteredCars);
                                break;
                            case 3:
                                System.out.print("Enter year: ");
                                int year = scanner.nextInt();
                                scanner.nextLine();
                                List<Car> yearFilteredCars = carRentalSystem.filterCars("", "", year, "", 0, "");
                                displayFilteredCars(yearFilteredCars);
                                break;
                            case 4:
                                System.out.print("Enter transmission type: ");
                                filterValue = scanner.nextLine();
                                List<Car> transmissionFilteredCars = carRentalSystem.filterCars("", "", 0, filterValue, 0, "");
                                displayFilteredCars(transmissionFilteredCars);
                                break;
                            case 5:
                                System.out.print("Enter seating capacity: ");
                                int seatingCapacity = scanner.nextInt();
                                scanner.nextLine();
                                List<Car> seatingCapacityFilteredCars = carRentalSystem.filterCars("", "", 0, "", seatingCapacity, "");
                                displayFilteredCars(seatingCapacityFilteredCars);
                                break;
                            case 6:
                                System.out.print("Enter fuel type: ");
                                filterValue = scanner.nextLine();
                                List<Car> fuelTypeFilteredCars = carRentalSystem.filterCars("", "", 0, "", 0, filterValue);
                                displayFilteredCars(fuelTypeFilteredCars);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                continue;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the car index to rent(1-"+carRentalSystem.numberOfCars()+") :");
                    int rentIndex = scanner.nextInt();
					System.out.print("");
					try
					{
						carRentalSystem.rentCar(rentIndex, days);
					}
					catch (CarStatusException c)
					{
						System.out.println(c);
					}
                    break;
                case 4:
                    System.out.print("Enter the car index to return you have rented: ");
                    int returnIndex = scanner.nextInt();
					System.out.print("");
                    try
					{
						carRentalSystem.returnCar(returnIndex,days);
                    }
					catch(CarStatusException c)
					{
					   System.out.println(c);
					}
                    break;
                case 5:
                    System.out.println("Thank you for using the Car Rental System:)");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
	
	private static void displayFilteredCars(List<Car> cars)
		{
			if (cars.isEmpty()) 
			{
				 System.out.println("No cars matching the criteria found.");
			}
			else 
			{
				
					System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
					System.out.println("| Index    | Make           | Model          | Year | Transmission    | Seating Capacity| Fuel Type | Price per Day |");
					System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");
					for (Car car : cars)
					{
						System.out.printf("| %-8d | %-14s | %-14s | %-4d | %-15s | %-15d | %-9s | $%-12.2f |\n",
								car.getIndex(), car.getMake(), car.getModel(), car.getYear(), car.getTransmissionType(),
								car.getSeatingCapacity(), car.getFuelType(), car.getPricePerDay());
					}
					System.out.println("+----------+----------------+----------------+------+-----------------+-----------------+-----------+---------------+");

			}
  
	}
	private static void loadCarDataFromFile(CarRentalSystem carRentalSystem, String carDetails)
		{
			try (BufferedReader br = new BufferedReader(new FileReader(carDetails))) 
			{
				String line;

				br.readLine();

				while ((line = br.readLine()) != null) 
				{
					String[] carData = line.split(",");
					Car car = new Car(carData);
					carRentalSystem.addCar(car);
				}
			} 
			catch (IOException e) 
			{
				System.out.println("Error reading car data from file: " + e.getMessage());
			}
		}
}
