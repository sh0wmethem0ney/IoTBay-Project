package beans;

public class ProductBean {
    //member attributes
    private static int idCounter = 0;
    private int productID;
    private String productName;
    private String description;
    private String status;
    private int quantity;

    //constructor
    public ProductBean(String productName, String description, String status, int quantity) {
        //this ensures each instantiate of object runs generateID method
        this.productID = generateID();
        this.productName = productName;
        this.description = description;
        this.status = status;
        this.quantity = quantity;
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
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
