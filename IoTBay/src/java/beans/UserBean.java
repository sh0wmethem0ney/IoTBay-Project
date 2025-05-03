package beans;

public class UserBean {
    
    //member attributes
    //private static int idCounter = 1;
    private int UserID;
    private String customerName;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;
    private String password;
    private String email;
    private String gender;
    private char role;  // 'c' = Customer, 'a' = Admin, 's' = Staff
    private int orderID;


    //constructor
    public UserBean(){
        //default constructor
        //not sure if parameters are needed
//        this.customerID = generateID();
    }

//    //method to assign 6 digit ID value to each object -> need to work on this later
//    private String generateID() {
//        String str = String.format("%06d", idCounter++);
//        return str;
//    }

    //accessors and mutators
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public String getUserName() {
        return customerName;
    }

    public void setUserName(String customerName) {
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
    
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public char getRole(){
        return role;
    }
    
    public void setRole(char role){
        this.role = role;
    }
    
}