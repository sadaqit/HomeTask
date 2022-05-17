package com.springboot.swisscom;

import com.springboot.swisscom.dob.AccountService;
import com.springboot.swisscom.model.MyEmailGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AccountService emails;
    public void tempEmails(){
        emails.save(new MyEmailGUI("sdaqit@bluewin.ch", false, "Free Email"));
        emails.save(new MyEmailGUI("ali@bluewin.ch", false, "Free Email"));
    }
    public List<MyEmailGUI> findAll() {
        return emails.findAll();
    }

    public List<MyEmailGUI> findById(int Id) {
        return emails.findById(Id);
    }

    public void addEmail(MyEmailGUI newEmail) {
        emails.save(newEmail);
    }

    public void purchasePremium(int id){
        MyEmailGUI temp = emails.getById(id);
        emails.getById(id).setPremium(true);
        temp.setPremium(true);
        temp.setAddressBook("Premium Email Service");
        temp.setQuote(temp.getQuote()+1);
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        if(month ==12)
        {
            month =1;
            year++;
        }else month++;
        temp.setExpireDate(LocalDate.of(year, month, temp.getStartDate().getDayOfMonth()));
        temp.setId(temp.getId());
        emails.save(temp);
    }

    public void applyFilter(int id){
        MyEmailGUI temp = emails.getById(id);
        if(temp.getAddressBook().equals("Premium Email Service"))
            temp.setAddressBook("Premium Email Service with Filter");
        else temp.setAddressBook("Premium Filter Purchased");
        temp.setQuote(temp.getQuote()+1);
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        if(month ==12)
        {
            month =1;
            year++;
        }else month++;
        temp.setExpireDate(LocalDate.of(year, month, temp.getStartDate().getDayOfMonth()));
        temp.setId(temp.getId());
        emails.save(temp);
    }
    public List<LocalDate> upcommingPayments()
    {
        List<LocalDate> expireDate = new ArrayList<>();
        for(MyEmailGUI e: emails.findAll()){
            expireDate.add(e.getExpireDate());
        }
        return expireDate;
    }
    public boolean removeFilter(int id){
        MyEmailGUI temp = emails.getById(id);
        boolean removed = false;
        if(temp.getAddressBook().equals("Premium Filter Purchased")) {
            temp.setAddressBook("Free Email Service");
            removed = true;
        }

        if(temp.getAddressBook().equals("Premium Email Service with Filter")) {
            temp.setAddressBook("Premium Email Service");
            removed = true;
        }
        temp.setQuote(temp.getQuote()-1);
        temp.setExpireDate(null);
        temp.setId(temp.getId());
        emails.save(temp);
        return removed;

    }

    public void deleteEmail(int id) {
        emails.deleteById(id);
    }
}
