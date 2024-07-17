package my.edu.utar;

import java.util.Scanner;

public class BookingApplication {

    public static void main(String[] args) {
        WaitingList waitinglist = new WaitingList();
        // Set the default available rooms to 10 for every types of rooms
        Room allHotelRooms = new Room(1, 1, 1);
        Booking bookingSystem = new Booking(allHotelRooms);
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("Welcome to Hotel Room Booking System");
            System.out.println("======================================");
            System.out.println("Rooms Available Now");
            System.out.println("VIP rooms : " + allHotelRooms.getVip());
            System.out.println("Deluxe rooms : " + allHotelRooms.getDeluxe());
            System.out.println("Standard rooms : " + allHotelRooms.getStandard());
            System.out.println("--------------------------------------");

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your member type (VIP/Normal/Non-member): ");
            String memberType = scanner.nextLine();
            System.out.print("Do you have an exclusive reward? (true/false): ");
            boolean exclReward = scanner.nextBoolean();
            User user = new User(name, memberType, exclReward);

            System.out.println();
            int noOfRooms;
            Room bookedRoom = new Room(0, 0, 0);
            bookingSystem.setUser(user);
            bookingSystem.setWaitingList(waitinglist);

            switch (user.getMember_type().toLowerCase()) {
                case "vip":
                    int maxRoomsVIP = 3;
                    System.out.print("How many rooms do you want to book (MAX = 3)? ");
                    noOfRooms = scanner.nextInt();
                    if (noOfRooms > maxRoomsVIP) {
                        System.out.println("You can book at most " + maxRoomsVIP + " rooms as a VIP member.");
                        noOfRooms = maxRoomsVIP;
                    }
                    bookedRoom = bookingSystem.setBooking(noOfRooms);
                    break;

                case "normal":
                    int maxRoomsNormal = 2;
                    System.out.print("How many rooms do you want to book (MAX = 2)? ");
                    noOfRooms = scanner.nextInt();
                    if (noOfRooms > maxRoomsNormal) {
                        System.out.println("You can book at most " + maxRoomsNormal + " rooms as a normal member.");
                        noOfRooms = maxRoomsNormal;
                    }
                    bookedRoom = bookingSystem.setBooking(noOfRooms);
                    break;

                case "non-member":
                    int maxRoomsNonMember = 1;
                    System.out.print("How many rooms do you want to book (MAX = 1)? ");
                    noOfRooms = scanner.nextInt();
                    if (noOfRooms > maxRoomsNonMember) {
                        System.out.println("Non-members can only book 1 room.");
                        noOfRooms = maxRoomsNonMember;
                    }
                    bookedRoom = bookingSystem.setBooking(noOfRooms);
                    break;

                default:
                    System.out.println("Invalid member type.");
                    break;
            }
            scanner.nextLine(); // Consume newline left by nextInt()

            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("Successfully booked rooms:");
            System.out.println("VIP Rooms: " + bookedRoom.getVip());
            System.out.println("Deluxe Rooms: " + bookedRoom.getDeluxe());
            System.out.println("Standard Rooms: " + bookedRoom.getStandard());
            System.out.println();

            System.out.println("--------------------------------------");
            System.out.println("Number of VIP rooms left: " + bookingSystem.getAvailableRooms().getVip());
            System.out.println("Number of deluxe rooms left: " + bookingSystem.getAvailableRooms().getDeluxe());
            System.out.println("Number of standard rooms left: " + bookingSystem.getAvailableRooms().getStandard());
            System.out.println();

            System.out.println("--------------------------------------");
            waitinglist.printWaitingLists();

            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("1. Do you want to make another booking? ");
            System.out.println("2. Do you want to cancel booking? ");
            System.out.println("3. Exit ");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();
            if (choice.equals("3")) {
                break;
            }
            if (choice.equals("2")) {
            	bookingSystem.cancelBooking(bookedRoom);
            }
        }

        scanner.close();
    }

}
