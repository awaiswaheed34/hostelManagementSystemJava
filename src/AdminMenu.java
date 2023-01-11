import api.AdminResource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;


public class AdminMenu {

    private static  AdminResource adminResource = AdminResource.getSingleton();
    
    public static void adminMenu() {
        String line = "";
         
            printMenu();

            try {
                do {
                    
                    line = MainMenu.scanner.next();
                    
                    if(line=="")
                    {
                        continue;
                    }
                    if (line.length() == 1) {
                        switch (line.charAt(0)) {
                            case '1':
                                displayAllCustomers();
                                break;
                            case '2':
                                displayAllRooms();
                                break;
                            case '3':
                                displayAllReservations();
                                break;
                            case '4':
                                addRoom();
                                break;
                            case '5':
                                MainMenu.mainMenu();
                                break;
                            default:
                                System.out.println("Error\n");
                                break;
                        }
                    } else {
                        System.out.println("Error: Invalid action\n");
                    }
                } while (line.charAt(0) != '5' || line.length() != 1);
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("Empty input. Exiting program...");
            }
            //MainMenu.scanner.close();
        
    }

    private static void printMenu() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }

    private static void addRoom() {
         

        System.out.println("Enter room number:");
         String roomNumber = MainMenu.scanner.nextLine();

        System.out.println("Enter price per night:");
         double roomPrice = enterRoomPrice();

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        RoomType roomType = enterRoomType();
        Room room = new Room(roomNumber, roomPrice, roomType);
        boolean isRoomExist = adminResource.addRoom(Collections.singletonList(room));
        if(isRoomExist){
            System.out.println("Entered room already exist!");   
        }else{
            System.out.println("Room added successfully!");
        }
        System.out.println("Would like to add another room? Y/N");
        addAnotherRoom();
    }

    private static double enterRoomPrice() {
        try {
            return Double.parseDouble(MainMenu.scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("Invalid. Please, enter a valid double number. ");
            return enterRoomPrice();
        }
    }

    private static RoomType enterRoomType() {
        try {
            int i = MainMenu.scanner.nextInt();
            for (RoomType roomType : RoomType.values()) {
                if(roomType.ordinal()==i){
                    System.out.println(roomType);
                    return roomType;
                }
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException exp) {
            System.out.println("Invalid room type! Please, choose 1 for single bed or 2 for double bed:");
            return enterRoomType();
        }
    }

    private static void addAnotherRoom() {
        
            try {
                String anotherRoom;

                anotherRoom = MainMenu.scanner.nextLine();

                while ((anotherRoom.charAt(0) != 'Y' && anotherRoom.charAt(0) != 'N')
                        || anotherRoom.length() != 1) {
                    System.out.println("Please enter capital 'Y' (Yes) or capital 'N' (No)");
                    anotherRoom = MainMenu.scanner.nextLine();
                }

                if (anotherRoom.charAt(0) == 'Y') {
                    addRoom();
                } else if (anotherRoom.charAt(0) == 'N') {
                    printMenu();
                } else {
                    addAnotherRoom();
                }
            } catch (StringIndexOutOfBoundsException ex) {
                addAnotherRoom();
            }
        
    }

    private static void displayAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if(rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            adminResource.getAllRooms().forEach(System.out::println);
        }
    }

    private static void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            adminResource.getAllCustomers().forEach(System.out::println);
        }
    }

    private static void displayAllReservations() {
        adminResource.desiplayAllReservations();
    }

    public static void displayMenu() {
        adminMenu();
    }
}
