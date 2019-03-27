package com.hhp.rental;

/**
 * @author: Humphrey Hu
 * @date: 19/03/27
 * @description:
 */
public class ChildrenPrice extends Price {

    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
