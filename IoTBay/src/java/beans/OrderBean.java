package beans;
import java.util.*;
public class OrderBean {
    //member attributes
    private static int idCounter = 0;
    private int orderID;
    private String userName;
    private int productID;
    private String orderStatus;
    private Date orderDate;
    private int quantity;
    private double price;
    private double totalPrice;
    private int paymentID;
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

    public OrderBean(int orderID, String userName, double totalPrice, String orderStatus){
        this.orderID = orderID;
        this.userName = userName;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
 
    }
    
    //accessors and mutators
    public int getOrderID() {
        return orderID;}
    public void setOrderID(int orderID) {
        this.orderID = orderID;}
    
    public String getUserName() { 
        return userName; }
    public void setUserName(String userName) { 
        this.userName = userName; }
    
    public int getProductID() { 
        return productID; }
    public void setProductID(int productID) { 
        this.productID = productID; }

    public String getOrderStatus() {
        return orderStatus;}
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;}
    
    public Date getOrderDate() {
        return orderDate;}
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;}
    
    public int getQuantity() { 
        return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; }
    
    public double getPrice() { 
        return price; }
    public void setPrice(double price) { 
        this.price = price; }

    public double getTotalPrice() { 
        return totalPrice; }
    public void setTotalPrice(double totalPrice) { 
        this.totalPrice = totalPrice; }

    public int getPaymentID() {
        return paymentID;}
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;}

    public String getShippingDetails() {
        return shippingDetails;}
    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;}
}