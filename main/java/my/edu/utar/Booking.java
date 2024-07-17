package my.edu.utar;

import my.edu.utar.Room;
import my.edu.utar.User;
import my.edu.utar.WaitingList;

public class Booking {
	private Room availableRooms; //stub
	private User currentUser;  //stub
	private WaitingList waitinglist; //mock
	
    public Booking(Room room) {
        this.availableRooms = room;
    }
    
    public Room getAvailableRooms() {
    	return availableRooms;
    }
    
    public void setUser(User currentUser) {
    	this.currentUser=currentUser;
    }
    
    public void setWaitingList(WaitingList waitinglist) {
    	this.waitinglist=waitinglist;
    }

    public Room setBooking(int noOfRooms) {
        Room roomsBooked = new Room(0,0,0); 

        String memberType = currentUser.getMember_type();
        boolean excl_Reward = currentUser.getExcl_reward();
        
        int availableVIP = availableRooms.getVip();
        int availableDeluxe = availableRooms.getDeluxe();
        int availableStandard = availableRooms.getStandard();
        
        if (memberType.equalsIgnoreCase("vip")) {
            // VIP member booking rules
            if (availableVIP >= noOfRooms) {
                roomsBooked.setVip(noOfRooms); // Book all available VIP rooms
            } else {
                // Book available VIP rooms
                roomsBooked.setVip(availableVIP);
                int remainingRooms = noOfRooms - availableVIP;
                
                // Check if Deluxe rooms are available
                if (availableDeluxe >= remainingRooms) {
                    // Book remaining rooms as Deluxe
                    roomsBooked.setDeluxe(remainingRooms);
                } else {
                    // Book all available Deluxe rooms
                    roomsBooked.setDeluxe(availableDeluxe);
                    remainingRooms -= availableDeluxe;
                    
                    // Check if Standard rooms are available
                    if (availableStandard >= remainingRooms) {
                        // Book remaining rooms as Standard
                        roomsBooked.setStandard(remainingRooms);
                    } else {
                        // All room types fully booked, add user to VIP waiting list
                        System.out.println("Insufficient rooms available. Placing VIP member in waiting list.");
                        waitinglist.addWaiting(currentUser);
                        return roomsBooked;
                    }
                }
            }
        }else if (memberType.equalsIgnoreCase("normal")) {
            // Normal member booking rules
            if (excl_Reward) {
                // Check if VIP room is available
                if (availableVIP > 0 && noOfRooms >= 1) {
                    roomsBooked.setVip(1); // Book 1 VIP room
                    excl_Reward = false; // Set exclusive reward to false after booking VIP room
                    noOfRooms -= 1;
                }

                // Check if Deluxe and Standard rooms are available
                if (availableDeluxe + availableStandard >= noOfRooms) {
                    if (availableDeluxe >= noOfRooms) {
                        roomsBooked.setDeluxe(noOfRooms); // Book all requested rooms as Deluxe
                    } else {
                        roomsBooked.setDeluxe(availableDeluxe); // Book all available Deluxe rooms
                        roomsBooked.setStandard(noOfRooms - availableDeluxe); // Book remaining rooms as Standard
                    }
                } else {
                    System.out.println("Insufficient Deluxe and Standard rooms available. Please try again later.");
                    waitinglist.addWaiting(currentUser);
                    return roomsBooked; // Return without booking any rooms
                }
            } else {
                // Normal member without exclusive reward
                // Check if Deluxe and Standard rooms are available
                if (availableDeluxe + availableStandard >= noOfRooms) {
                    if (availableDeluxe >= noOfRooms) {
                        roomsBooked.setDeluxe(noOfRooms); // Book all requested rooms as Deluxe
                    } else {
                        roomsBooked.setDeluxe(availableDeluxe); // Book all available Deluxe rooms
                        roomsBooked.setStandard(noOfRooms - availableDeluxe); // Book remaining rooms as Standard
                    }
                } else {
                    System.out.println("Insufficient Deluxe and Standard rooms available. Please try again later.");
                    waitinglist.addWaiting(currentUser);
                    return roomsBooked; // Return without booking any rooms
                }
            }
        } else {
            // Non-member booking rules
            if (availableStandard >= noOfRooms) {
                roomsBooked.setStandard(noOfRooms); // Book Standard rooms
            } else {
                // Add Non-member to the waiting list
                if (noOfRooms == 1) {
                    waitinglist.addWaiting(currentUser);
                } else {
                    System.out.println("Non-members can only book 1 room.");
                    return roomsBooked; // Return without booking any rooms
                }
            }
        }

        
        // Update the available rooms
        availableRooms.setVip(availableVIP - roomsBooked.getVip());
        availableRooms.setDeluxe(availableDeluxe - roomsBooked.getDeluxe());
        availableRooms.setStandard(availableStandard - roomsBooked.getStandard());
        
        return roomsBooked;
    }

    public void cancelBooking(Room roomsBooked) {
        // Get user details
        String memberType = currentUser.getMember_type();

        // Check the booked rooms and update the available rooms accordingly
        int vipRoomsBooked = roomsBooked.getVip();
        int deluxeRoomsBooked = roomsBooked.getDeluxe();
        int standardRoomsBooked = roomsBooked.getStandard();

        switch (memberType.toLowerCase()) {
            case "vip":
                availableRooms.setVip(availableRooms.getVip() + vipRoomsBooked);
                availableRooms.setDeluxe(availableRooms.getDeluxe() + deluxeRoomsBooked);
                availableRooms.setStandard(availableRooms.getStandard() + standardRoomsBooked);
                System.out.println("Canceled " + (vipRoomsBooked + deluxeRoomsBooked + standardRoomsBooked) + " rooms for VIP member " + currentUser.getName() + ".");
                break;

            case "normal":
                availableRooms.setVip(availableRooms.getVip() + vipRoomsBooked);
                availableRooms.setDeluxe(availableRooms.getDeluxe() + deluxeRoomsBooked);
                availableRooms.setStandard(availableRooms.getStandard() + standardRoomsBooked);
                System.out.println("Canceled " + (vipRoomsBooked + deluxeRoomsBooked + standardRoomsBooked) + " rooms for normal member " + currentUser.getName() + ".");
                break;

            case "non-member":
                availableRooms.setStandard(availableRooms.getStandard() + standardRoomsBooked);
                System.out.println("Canceled " + standardRoomsBooked + " rooms for non-member " + currentUser.getName() + ".");
                break;

            default:
                System.out.println("Invalid member type.");
                break;
        }

        // Remove user from waiting list if applicable
        waitinglist.removeWaiting(currentUser);
        
    }


 
}

