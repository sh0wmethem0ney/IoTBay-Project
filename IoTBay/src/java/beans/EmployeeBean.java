package beans;

public class EmployeeBean {
    
    //member attributes
    private static int idCounter = 0;
    private int employeeID;
    private String employeeName;
    private String employeeRole;
    private String phoneNumber;
    private String email;

    //constructor
    public EmployeeBean(String employeeName, String employeeRole, String phoneNumber, String email) {
        //this ensures each instantiate of object runs generateID method
        this.employeeID = generateID();
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //method to assign 6 digit ID value to each object -> need to work on this later
    private int generateID(){
        int cur = idCounter++;
        String str = Integer.toString(cur);
        while(str.length() < 6){
            str = "0" + str;
        }
        return 0;
    }

    //accessors and mutators
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
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