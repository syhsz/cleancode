package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    /**
     *
     * @param p principal amount
     * @param t term of mortgage in years
     * @param r rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(
            int p, int t, double r) {

        if (p < 0 || t <= 0 || r < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        r /= 100.0;

        double tim = t * 12;

        if(r==0)
            return  p/tim;

        // convert into monthly rate
        double m = r / 12.0;

        // The Math.pow() method is used calculate values raised to a power
        double monthlyPayment = (p * m) / (1 - Math.pow(1 + m, -tim));

        return monthlyPayment;
    }
}
