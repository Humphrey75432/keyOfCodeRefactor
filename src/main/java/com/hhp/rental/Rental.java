package com.hhp.rental;

/**
 * @author: Humphrey Hu
 * @date: 19/03/26
 * @description:
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    Movie getMovie() {
        return movie;
    }

    double getCharge() {
        return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    int getDaysRented() {
        return daysRented;
    }
}
