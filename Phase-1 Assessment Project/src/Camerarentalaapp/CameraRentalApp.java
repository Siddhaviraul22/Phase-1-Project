package Camerarentalaapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CameraRentalApp {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password123";

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

    public void depositToWallet(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid deposit amount. Amount must be greater than 0.");
            }
            user.setWalletBalance(user.getWalletBalance() + amount);
            System.out.println("Deposit successful. New wallet balance: $" + user.getWalletBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed. " + e.getMessage());
        }
    }

    public void rentCamera(int cameraId) {
        Camera selectedCamera = null;
        for (Camera camera : cameraList) {
            if (camera.getCameraId() == cameraId) {
                selectedCamera = camera;
                break;
            }
        }

        if (selectedCamera != null) {
            double rentAmount = selectedCamera.getPerDayRent();
            if (user.getWalletBalance() >= rentAmount) {
                user.setWalletBalance(user.getWalletBalance() - rentAmount);
                myCameras.add(selectedCamera);
                cameraList.remove(selectedCamera);
                selectedCamera.setStatus("Rented");
                System.out.println("Camera rented successfully. Remaining balance: $" + user.getWalletBalance());
            } else {
                System.out.println("Insufficient wallet balance to rent the camera.");
            }
        } else {
            System.out.println("Camera not found with ID: " + cameraId);
        }
    }

    public void displaySortedAvailableCameras() {
        if (cameraList.isEmpty()) {
            System.out.println("No Data Present at This Moment.");
        } else {
            Collections.sort(cameraList, Comparator.comparing(Camera::getBrand));

            System.out.println("Camera ID\tBrand\tModel\tPrice (Per Day)\tStatus");
            for (Camera camera : cameraList) {
                System.out.println(camera.getCameraId() + "\t\t" + camera.getBrand() + "\t" +
                        camera.getModel() + "\t" + camera.getPerDayRent() + "\t\t" +
                        camera.getStatus());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CameraRentalApp rentalApp = new CameraRentalApp();

        System.out.println("+-----------------------------+");
        System.out.println("| WELCOME TO CAMERA RENTAL APP |");
        System.out.println("+-----------------------------+");
        System.out.println("\nPLEASE LOGIN TO CONTINUE");

        System.out.print("USERNAME: ");
        String username = scanner.nextLine();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful!\n");

            while (true) {
                System.out.println("----- Camera Rental App -----");
                System.out.println("1. My Camera");
                System.out.println("2. Rent a Camera");
                System.out.println("3. View All Cameras");
                System.out.println("4. My Wallet");
                System.out.println("5. Deposit to Wallet");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int mainChoice = scanner.nextInt();
                scanner.nextLine();

                switch (mainChoice) {
                    case 1:
                        System.out.println("1. Add");
                        System.out.println("2. Remove");
                        System.out.println("3. View My Cameras");
                        System.out.println("4. Go to Previous Menu");

                        System.out.print("Enter your choice: ");
                        int subChoice1 = scanner.nextInt();
                        scanner.nextLine();

                        switch (subChoice1) {
                            case 1:
                                System.out.print("Enter brand: ");
                                String brand = scanner.nextLine();
                                System.out.print("Enter model: ");
                                String model = scanner.nextLine();
                                System.out.print("Enter per-day rent amount: ");
                                double perDayRent = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter camera status: ");
                                String status = scanner.nextLine();
                                rentalApp.addCamera(brand, model, perDayRent, status);
                                break;
                            case 2:
                                System.out.println("Camera ID\tBrand\tModel\tPrice (Per Day)\tStatus");
                                rentalApp.displayAvailableCameras();
                                System.out.print("Enter the camera ID to remove: ");
                                int cameraIdToRemove = scanner.nextInt();
                                rentalApp.removeCamera(cameraIdToRemove);
                                break;
                            case 3:
                                rentalApp.viewMyCameras();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("----- Rent a Camera -----");
                        System.out.println("Available Cameras:");
                        rentalApp.displaySortedAvailableCameras();
                        System.out.print("Enter the camera ID to rent: ");
                        int rentCameraId = scanner.nextInt();
                        rentalApp.rentCamera(rentCameraId);
                        break;
                    case 3:
                        System.out.println("----- View All Cameras -----");
                        rentalApp.displaySortedAvailableCameras();
                        break;
                    case 4:
                        System.out.println("----- My Wallet -----");
                        System.out.println("Wallet Balance: $" + rentalApp.user.getWalletBalance());
                        break;
                    case 5:
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        rentalApp.depositToWallet(depositAmount);
                        break;
                    case 6:
                        System.out.println("Exiting the application. Thank you!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Exiting the application.");
        }
    }
}
