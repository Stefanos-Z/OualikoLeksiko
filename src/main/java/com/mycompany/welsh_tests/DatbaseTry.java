/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oneZt
 */
public class DatbaseTry {
    public static void main(String[] args) {
        try {
            DatabaseManager manager = new DatabaseManager();
        manager.createTable();
        } catch (IOException ex) {
            System.out.println("error IO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatbaseTry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseTry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
