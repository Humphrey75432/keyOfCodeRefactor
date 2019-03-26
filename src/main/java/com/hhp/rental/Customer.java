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
            Rental each = (Rental) myRentals.nextElement();
//             add frequent renter points
            frequenceRenterPoints++;
//            add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) {
                frequenceRenterPoints++;
            }
//            show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }
//        Add footer line
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequenceRenterPoints) + " frequent renter points.";
        return result;
    }

    //            determine amounts for each line
    private double amountFor(Rental aRental) {
        return aRental.getCharge();
//        double thisAmount = 0;
//        switch (aRental.getMovie().getPriceCode()) {
//            case Movie.REGULAR:
//                thisAmount += 2;
//                if (aRental.getDaysRented() > 2) {
//                    thisAmount += (aRental.getDaysRented() - 2) * 1.5;
//                }
//                break;
//            case Movie.NEW_RELEASE:
//                thisAmount += aRental.getDaysRented() * 3;
//                break;
//            case Movie.CHILDRENS:
//                thisAmount += 1.5;
//                if (aRental.getDaysRented() > 3) {
//                    thisAmount += (aRental.getDaysRented() - 3) * 1.5;
//                }
//                break;
//            default:
//                break;
//        }
//        return thisAmount;
    }


}
