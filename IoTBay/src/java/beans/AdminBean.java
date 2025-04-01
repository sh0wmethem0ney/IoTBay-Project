package beans;

public class AdminBean {

    //member attributes
    private static int idCounter = 0;
    private int adminID;
    private String adminName;
    private String phoneNumber;
    private String email;

    //constructor
    public AdminBean(String adminName, String phoneNumber, String email) {
        //this ensures each instantiate of object runs generateID method
        this.adminID = generateID();
        this.adminName = adminName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //method to assign 6 digit ID value to each object
    private int generateID(){
        int cur = idCounter++;
        String str = Integer.toString(cur);
        while(str.length() < 6){
            str = "0" + str;
        }
        return 0;
    }

    //accessors and mutators
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}