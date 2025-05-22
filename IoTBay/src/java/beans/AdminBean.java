/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package beans;

//model that grabs data from the SQL Databases
public class AdminBean {
//Initialise values for statistics
    private int totalUsers;
    private int totalCustomers;
    private int totalEmployees;
    private int totalDeactivatedUsers;
    
    // Getters from model DAO
    
    public int getTotalUsers(){
        return totalUsers;
    }
    public int getTotalCustomers(){
        return totalCustomers;
    }
    public int getTotalEmployees(){
        return totalEmployees;
    }
    public int getTotalDeactivatedUsers(){
        return totalDeactivatedUsers;
    }
    
    // Setters from model DAO but dont really need them
    public void setTotalUsers(int totalUsers){
        this.totalUsers = totalUsers;
    }
    public void setTotalCustomers(int totalCustomers){
        this.totalCustomers = totalCustomers;
    }
    public void setTotalEmployees(int totalEmployees){
        this.totalEmployees = totalEmployees;
    }
    public void setTotalDeactivatedUsers(int totalDeactivatedUsers){
        this.totalDeactivatedUsers = totalDeactivatedUsers;
    }
}
    
    
    