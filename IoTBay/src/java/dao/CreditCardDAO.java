/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ayden
 */

import beans.CreditCardBean;  // Import your CreditCardBean class
import java.sql.*;
import java.util.*;

public class CreditCardDAO {
    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";  // Your DB URL
    private final String dbUser = "app";  // DB Username
    private final String dbPassword = "app";  // DB Password

    public CreditCardDAO() {
    }

    public void insertCreditCard(CreditCardBean card) {
        String sqlState = "INSERT INTO CREDIT_CARDS (user_id, card_number, card_name, expiry_date, cvv) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlState)) {

            stmt.setInt(1, card.getUserID());
            stmt.setString(2, card.getCardNumber());
            stmt.setString(3, card.getCardName());
            stmt.setString(4, card.getExpiryDate());
            stmt.setString(5, card.getCvv());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Credit card inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<CreditCardBean> getCreditCardsByUserID(int userID) {
        List<CreditCardBean> cards = new ArrayList<>();

        String sql = "SELECT * FROM CREDIT_CARDS WHERE user_id = ?";  // table name and column adjusted for consistency

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CreditCardBean card = new CreditCardBean();
                    card.setCardID(rs.getInt("card_id")); //hopefully this doesnt break anything
                    card.setUserID(rs.getInt("user_id"));
                    card.setCardNumber(rs.getString("card_number"));
                    card.setCardName(rs.getString("card_name"));
                    card.setExpiryDate(rs.getString("expiry_date"));
                    card.setCvv(rs.getString("cvv"));
                    cards.add(card);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }
    
    public boolean deleteCardByID(String cardID) {
        String sql = "DELETE FROM credit_cards WHERE card_id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cardID);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}

