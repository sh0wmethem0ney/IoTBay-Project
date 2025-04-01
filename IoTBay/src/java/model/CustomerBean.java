package model;

public class CustomerBean {
    
    //member attributes
    private static int idCounter = 0;
    private int customerID;
    private String customerName;
    private String dateOfBirth;
    private String phoneNumber;
    private String userID;
    private String address;
    private int orderID;
    private String password;
    private String email;

    //constructor
    public CustomerBean(String customerName, String dateOfBirth, String phoneNumber, String userID, String address, String email, String password) {
        //this ensures each instantiate of object runs generateID method
        this.customerID = generateID();
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.address = address;
        this.password = password;
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
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}