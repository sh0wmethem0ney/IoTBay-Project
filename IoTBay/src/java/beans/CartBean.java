package beans;

import java.util.*;

public class CartBean {
    private ProductBean productName;
    private int quantity;
    
    public CartBean(ProductBean productName, int quantity){
        this.productName = productName;
        this.quantity = quantity;
    }
    
    public ProductBean getProductName() { 
        return productName; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }

    public double getTotalPrice() {
        return productName.getUnitPrice() * quantity;
    }
}
    
/*    public void addProduct(ProductBean productName){
        int id = product.getProductID();
        if (items.containsKey(id)){
            ProductBean existing = items.get(id);
            existing.setQuantity(existing.getQuantity() + product.getQuantity());
        } else {
            items.put(id,product);
        }
    }
    
    public void updateProduct(int id,int quantity){
        if (items.containsKey(id)){
            items.get(id).setQuantity(quantity);
        }
    }
    
    public void removeProduct(int id){
        items.remove(id);
    }
    
    public Collection<ProductBean> getProducts(){
        return items.values();
    }
    
    public double getTotalPrice(){
        return items.values().stream()
                .mapToDouble(p -> p.getUnitPrice()*p.getQuantity())
                .sum();
    }
    
    public void clearCart() {
        items.clear();
    }
}
*/