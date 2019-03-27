package com.hhp.rental;

/**
 * @author: Humphrey Hu
 * @date: 19/03/27
 * @description:
 */
public abstract class Price {
    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
