package my.edu.utar;

public class Room {

    private int vip;
    private int deluxe;
    private int standard;

    public int getVip() {
        return vip;
    }

    public int getDeluxe() {
        return deluxe;
    }

    public int getStandard() {
        return standard;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public void setDeluxe(int deluxe) {
        this.deluxe = deluxe;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }
    

	public Room(int vip, int deluxe, int standard  ) {
		this.vip = vip;
		this.deluxe = deluxe;
		this.standard = standard;
	}


	public boolean checkRoom(String room_type) {
		switch (room_type) {
		case "vip":
			if (vip > 0) 
			{
				return true;
			}
			else {
				return false;
			}

		case "deluxe":
			if (deluxe > 0) 
			{
				return true;
			} 
			else {
				return false;
			}

		case "standard":
			if (standard > 0) 
			{
				return true;
			} 
			else {
				return false;
			}

		default:
			System.out.println("Not available room type");
			return false;
		}
	}
}

	