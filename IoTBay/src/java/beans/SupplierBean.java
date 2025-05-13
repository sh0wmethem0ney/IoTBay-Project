package beans;

public class SupplierBean {
    //member attributes
    private static int idCounter = 0;
    private int supplierID;
    private String supplierName;
    private String address;
    private String phoneNumber;
    private String email;
    private String status;

    //constructor
    public SupplierBean(String supplierName, String address, String phoneNumber, String email, String status) {
        //this ensures each instantiate of object runs generateID method
        this.supplierName = supplierName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

//    //method to assign 6 digit ID value to each object -> need to work on this later
//    private int generateID(){
//        int cur = idCounter++;
//        String str = Integer.toString(cur);
//        while(str.length() < 6){
//            str = "0" + str;
//        }
//        return 0;
//    }

    //accessors and mutators
    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}