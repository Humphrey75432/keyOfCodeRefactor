package com.hhp.rental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: Humphrey Hu
 * @date: 19/03/26
 * @description:
 */
public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statements() {
        Enumeration myRentals = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (myRentals.hasMoreElements()) {
            Rental each = (Rental) myRentals.nextElement();
//            show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }
//        Add footer line
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points.";
        return result;
    }

    // Add a new function
    public String htmlStatements() {
        Enumeration htmlRentals = rentals.elements();
        String result = "<h1>Rentals for <em>" + getName() +
                "</em></h1><p>\n";
        while (htmlRentals.hasMoreElements()) {
            Rental each = (Rental) htmlRentals.nextElement();
            result += each.getMovie().getTitle() + " : " +
                    String.valueOf(each.getCharge()) + "<br>\n";
        }
        result += "<p>You owe <em>" + String.valueOf(getTotalCharge()) +
                "<em><p>\n";
        result += "On this rental you earned <em>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</em>frequent renter points<p>";
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration myRentals = rentals.elements();
        while (myRentals.hasMoreElements()) {
            Rental each = (Rental) myRentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration myRentals = rentals.elements();
        while (myRentals.hasMoreElements()) {
            Rental each = (Rental) myRentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }


}
