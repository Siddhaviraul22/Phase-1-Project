package Camerarentalaapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CameraRentalApp {
    private List<Camera> cameraList;
    private User user;
    private List<Camera> myCameras;

    public CameraRentalApp() {
        this.cameraList = new ArrayList<>();
        this.user = new User();
        this.myCameras = new ArrayList<>();
    }

    public void addCamera(String brand, String model, double perDayRent, String status) {
        Camera newCamera = new Camera(brand, model, perDayRent, status);
        cameraList.add(newCamera);
    }

    public void removeCamera(int cameraId) {
        for (Camera camera : cameraList) {
            if (camera.getCameraId() == cameraId) {
                cameraList.remove(camera);
                System.out.println("Camera successfully removed from the list.");
                return;
            }
        }
        System.out.println("Camera not found with ID: " + cameraId);
    }

    public void viewMyCameras() {
        if (myCameras.isEmpty()) {
            System.out.println("You don't have any cameras.");
        } else {
            System.out.println("Camera ID\tBrand\tModel\tPrice (Per Day)\tStatus");
            for (Camera camera : myCameras) {
                System.out.println(camera.getCameraId() + "\t\t" + camera.getBrand() + "\t" +
                        camera.getModel() + "\t" + camera.getPerDayRent() + "\t\t" +
                        camera.getStatus());
            }
        }
    }

    public void displayAvailableCameras() {
        if (cameraList.isEmpty()) {
            System.out.println("No Data Present at This Moment.");
        } else {
            System.out.println("Camera ID\tBrand\tModel\tPrice (Per Day)\tStatus");
            for (Camera camera : cameraList) {
                System.out.println(camera.getCameraId() + "\t\t" + camera.getBrand() + "\t" +
                        camera.getModel() + "\t" + camera.getPerDayRent() + "\t\t" +
                        camera.getStatus());
            }
        }
    }

    // Other methods for renting, viewing available cameras, wallet options, etc.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CameraRentalApp rentalApp = new CameraRentalApp();

        while (true) {
            System.out.println("----- Camera Rental App -----");
            System.out.println("1. My Camera");
            System.out.println("2. Rent a Camera");
            System.out.println("3. View All Cameras");
            System.out.println("4. My Wallet");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (mainChoice) {
            case 1:
                // Add, Remove, View My Cameras functionalities
                System.out.println("1. Add");
                System.out.println("2. Remove");
                System.out.println("3. View My Cameras");
                System.out.println("4. Go to Previous Menu");

                System.out.print("Enter your choice: ");
                int subChoice1 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (subChoice1) {
                    case 1:
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter per-day rent amount: ");
                        double perDayRent = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter camera status: ");
                        String status = scanner.nextLine();
                        rentalApp.addCamera(brand, model, perDayRent, status);
                        break;
                    case 2:
                        System.out.println("Camera ID\tBrand\tModel\tPrice (Per Day)\tStatus");
                        rentalApp.displayAvailableCameras(); // Display available cameras
                        System.out.print("Enter the camera ID to remove: ");
                        int cameraIdToRemove = scanner.nextInt();
                        rentalApp.removeCamera(cameraIdToRemove);
                        break;
                    case 3:
                        rentalApp.viewMyCameras();
                        break;
                    case 4:
                        // Go to Previous Menu
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                break;
            case 2:
                // Rent Camera functionality
                System.out.println("----- Rent a Camera -----");
                System.out.println("Available Cameras:");
                rentalApp.displayAvailableCameras();
                System.out.print("Enter the camera ID to rent: ");
                int rentCameraId = scanner.nextInt();
                // Implement rentCamera method here (not provided in the previous responses)
                // rentalApp.rentCamera(rentCameraId);
                break;
            case 3:
                // View All Cameras functionality
                System.out.println("----- View All Cameras -----");
                rentalApp.displayAvailableCameras();
                break;
            case 4:
                // My Wallet functionality
                System.out.println("----- My Wallet -----");
                System.out.println("Wallet Balance: $" + rentalApp.user.getWalletBalance());
                break;
            case 5:
                System.out.println("Exiting the application. Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        }
    }
}