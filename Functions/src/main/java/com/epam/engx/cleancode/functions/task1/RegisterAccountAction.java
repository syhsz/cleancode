package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccount(account);
        setCreateDate(account);
        setAccountAddress(account);
        createAccount(account);
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

    private void validateAccount(Account account){
        if (isNotValidNameLength(account)){
            throw new WrongAccountNameException();
        }
        if (isNotValidPasswordLength(account)) {
            throw new WrongPasswordException();
        }
        if (isNotValidPassword(account)) {
            throw new WrongPasswordException();
        }
    }

    private Boolean isNotValidNameLength(Account account){
        return account.getName().length() <= 5;
    }

    private Boolean isNotValidPasswordLength(Account account){
        return passwordChecker.validate(account.getPassword()) != OK;
    }

    private Boolean isNotValidPassword(Account account){
        return account.getPassword().length() <= 8;
    }

    private void setCreateDate(Account account){
        account.setCreatedDate(new Date());
    }

    private void setAccountAddress(Account account){
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }

    private void createAccount(Account account){
        accountManager.createNewAccount(account);
    }

}
