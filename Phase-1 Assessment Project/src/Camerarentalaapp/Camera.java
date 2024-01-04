package Camerarentalaapp;

public class Camera {
	 private static int nextCameraId = 1;

	    private int cameraId;
	    private String brand;
	    private String model;
	    private double perDayRent;
	    private String status;

	    public Camera(String brand, String model, double perDayRent, String status) {
	        this.cameraId = nextCameraId++;
	        this.brand = brand;
	        this.model = model;
	        this.perDayRent = perDayRent;
	        this.status = status;
	    }

	    public int getCameraId() {
	        return cameraId;
	    }

	    public String getBrand() {
	        return brand;
	    }

	    public String getModel() {
	        return model;
	    }

	    public double getPerDayRent() {
	        return perDayRent;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}
