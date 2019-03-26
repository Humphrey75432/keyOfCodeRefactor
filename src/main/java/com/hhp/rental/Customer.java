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
        double totalAmount = 0;
        int frequenceRenterPoints = 0;
        Enumeration myRentals = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (myRentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) myRentals.nextElement();
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
                default:
                    break;
            }
            frequenceRenterPoints++;
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) {
                frequenceRenterPoints++;
            }
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequenceRenterPoints) + " frequent renter points.";
        return result;
    }

}
