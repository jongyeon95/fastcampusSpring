package com.example.study.model;


public class SearchParam {
    private String account;
    private String email;
    private  int page;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public int getPage() {
        return page;
    }
}
