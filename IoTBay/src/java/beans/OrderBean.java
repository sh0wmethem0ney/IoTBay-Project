package beans;

public class OrderBean {
    //member attributes
    private static int idCounter = 0;
    private int orderID;
    private String orderDesc;
    private String orderStatus;
    private String paymentID;
    private String shippingDetails;

    //constructor
    public OrderBean() {
        //this ensures each instantiate of object runs generateID method
//        this.orderID = generateID();
    }

    //method to assign 6 digit ID value to each object -> need to work on this later
//    private int generateID(){
//        int cur = idCounter++;
//        String str = Integer.toString(cur);
//        while(str.length() < 6){
//            str = "0" + str;
//        }
//        return 0;
//    }

    //accessors and mutators
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }
}
