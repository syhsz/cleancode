package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model){

        try {
            String totalMessage = getUserTotalMessage(userId);
            model.addAttribute("userTotalMessage", totalMessage);
            return "userTotal";
        } catch (NullDAOException e) {
            return "technicalError";
        }
    }

    private String getUserTotalMessage(String userId) {

        try{
            Double amount = userReportBuilder.getUserTotalOrderAmount(userId);
            return "User Total: " + amount + "$";
        } catch (NoUserException e) {
            return "WARNING: User ID doesn't exist.";
        } catch (NoSubmittedOrderException e) {
            return "WARNING: User have no submitted orders.";
        } catch (WrongOrderAccountException e) {
            return "ERROR: Wrong order amount.";
        }
    }


    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
