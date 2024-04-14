import java.util.*;

class Car 
{
	private int index;
    private String make;
    private String model;
    private int year;
    private String transmissionType;
    private int seatingCapacity;
    private String fuelType;
    private boolean available;
	private double pricePerDay;

    public Car(String[] carData)	
	{
		try
		{
			this.index =Integer.parseInt(carData[0].trim());
            this.make = carData[1].trim();
            this.model = carData[2].trim();
            this.year = Integer.parseInt(carData[3].trim());
            this.transmissionType = carData[4].trim();
            this.seatingCapacity = Integer.parseInt(carData[5].trim());
            this.fuelType = carData[6].trim();
            this.available = true;
            this.pricePerDay = Double.parseDouble(carData[7].trim());
		}
		catch (Exception e)
		{
			System.out.println("Error parsing car data: " + e.getMessage());
		}
	}


    // Getters and setters
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index=index;
	}
    public String getMake()
	{
        return make;
    }

    public void setMake(String make)
	{
        this.make = make;
    }

    public String getModel()
	{
        return model;
    }

    public void setModel(String model) 
	{
        this.model = model;
    }

    public int getYear()
	{
        return year;
    }

    public void setYear(int year) 
	{
        this.year = year;
    }

    public String getTransmissionType()
	{
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) 
	{
        this.transmissionType = transmissionType;
    }

    public int getSeatingCapacity()
	{
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) 
	{
        this.seatingCapacity = seatingCapacity;
    }

    public String getFuelType() 
	{
        return fuelType;
    }

    public void setFuelType(String fuelType)
	{
        this.fuelType = fuelType;
    }

    public boolean isAvailable() 
	{
        return available;
    }

    public void setAvailable(boolean available) 
	{
        this.available = available;
    }
	 public double getPricePerDay() 
	{
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) 
	{
        this.pricePerDay = pricePerDay;
    }
}