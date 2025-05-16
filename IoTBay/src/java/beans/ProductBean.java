//package beans;
//
//public class ProductBean {
//    // Static counter to generate unique 6-digit product IDs
//    private static int idCounter = 0;
//
//    // Member attributes
//    private int productID;
//    private String productName;
//    private String productType;
//    private String description;
//    private String status;
//    private double unitPrice;
//    private int quantity;
//    private String imageUrl;
//
//    // Default constructor
//    public ProductBean() {
//        this.productID = generateID();
//    }
//
//    // Constructor with parameters
//    public ProductBean(String productName, String productType, String description, String status,
//                       double unitPrice, int quantity, String imageUrl) {
//        this.productID = generateID();
//        this.productName = productName;
//        this.productType = productType;
//        this.description = description;
//        this.status = status;
//        this.unitPrice = unitPrice;
//        this.quantity = quantity;
//        this.imageUrl = imageUrl;
//    }
//
//    // Method to generate a unique 6-digit product ID
//    private int generateID() {
//        idCounter++;
//        return Integer.parseInt(String.format("%06d", idCounter));
//    }
//
//    // Getters and setters
//    public int getProductID() {
//        return productID;
//    }
//
//    public void setProductID(int productID) {
//        this.productID = productID;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductType() {
//        return productType;
//    }
//
//    public void setProductType(String productType) {
//        this.productType = productType;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    // Formatted price as string with currency symbol
//    public String getFormattedPrice() {
//        return String.format("$%.2f", unitPrice);
//    }
//}


package beans;

public class ProductBean {
    // Static counter to generate unique 6-digit product IDs
    private static int idCounter = 0;

    // Member attributes
    private int productID;
    private String productName;
    private String productType;
    private double unitPrice;
    private int quantity;
    private String imageUrl;

    // Default constructor
    public ProductBean() {
        this.productID = generateID();
    }

    // Constructor with parameters
    public ProductBean(String productName, String productType, double unitPrice, int quantity, String imageUrl) {
        this.productID = generateID();
        this.productName = productName;
        this.productType = productType;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Method to generate a unique 6-digit product ID
    private int generateID() {
        idCounter++;
        return Integer.parseInt(String.format("%06d", idCounter));
    }

    // Getters and setters
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Format price as string with currency symbol
    public String getFormattedPrice() {
        return String.format("$%.2f", unitPrice);
    }
}
