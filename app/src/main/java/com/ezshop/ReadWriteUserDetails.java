package com.ezshop;

public class ReadWriteUserDetails {

    public String country, cp_num, dob, gender;

    public ReadWriteUserDetails(){}

    public ReadWriteUserDetails( String country,String cp_num, String dob, String gender)
    {
        this.country = country;
        this.cp_num = cp_num;
        this.dob = dob;

        this.gender = gender;
    }
}
