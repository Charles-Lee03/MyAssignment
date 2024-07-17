package my.edu.utar;
import java.util.ArrayList;
import java.util.List;

public class WaitingList 
{

	private List<User> vipWaitingList;
    private List<User> normalWaitingList;
    private List<User> nonMemberWaitingList;

    // Constructor to initialize waiting lists
    public WaitingList() 
    {
        vipWaitingList = new ArrayList<>();
        normalWaitingList = new ArrayList<>();
        nonMemberWaitingList = new ArrayList<>();
    }

    // Method to add a user to the waiting list
    public void addWaiting(User user) 
    {
        switch (user.getMember_type().toLowerCase()) 
        {
            case "vip":
                vipWaitingList.add(user);
                break;
            case "normal":
                normalWaitingList.add(user);
                break;
            case "non-member":
                nonMemberWaitingList.add(user);
                break;
            default:
                System.out.println("Invalid member type.");
                break;
        }
    }

    // Method to remove a user from the waiting list
    public void removeWaiting(User user) 
    {
        switch (user.getMember_type().toLowerCase()) 
        {
            case "vip":
                vipWaitingList.remove(user);
                break;
                
            case "normal":
                normalWaitingList.remove(user);
                break;
                
            case "non-member":
                nonMemberWaitingList.remove(user);
                break;
                
            default:
                System.out.println("Invalid member type.");
                break;
        }
    }

    // Method to print waiting lists
    public void printWaitingLists() 
    {
        System.out.println("VIP Waiting List:");
        for (User user : vipWaitingList) 
        {
            System.out.println("- " + user.getName());
        }

        System.out.println("Normal Member Waiting List:");
        for (User user : normalWaitingList) 
        {
            System.out.println("- " + user.getName());
        }

        System.out.println("Non-member Waiting List:");
        for (User user : nonMemberWaitingList) 
        {
            System.out.println("- " + user.getName());
        }
    }
}
