package com.springboot.swisscom;

import com.springboot.swisscom.model.MyEmailGUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class PlateformService {

    static Logger logger = LogManager.getLogger(PlateformService.class);
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(PlateformService.class, args);
    }

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/emails")
    public List<MyEmailGUI> accounts() {
        //accountService.save(new BankAccount(0.0));
        return userService.findAll();
    }
    @GetMapping("/email/{Id}")
    public List<MyEmailGUI> greeting(@PathVariable int Id){
        return userService.findById(Id);
    }
    @PostMapping(value = "/tempemails")
    public String addTempMails(){
        userService.tempEmails();
        return "Successfully added Temp emails";
    }

    @PostMapping(value = "/addemail")
    public String addAccount(@RequestBody MyEmailGUI myEmailGUI){
        userService.addEmail(myEmailGUI);
        return "Successfully added";
    }


    @PutMapping(value = "/purchasePremium")
    public String addAccount(@RequestParam int id){

        userService.purchasePremium(id);
        return "Successfully purchased ";
    }

    @PutMapping(value = "/purchaseFilter")
    public String addFilder(@RequestParam int id){

        userService.applyFilter(id);
        return "Filter Applied Successfully";
    }

    @PutMapping(value = "/removeFilter")
    public String removeFilder(@RequestParam int id){

        logger.error(userService.removeFilter(id));
        return "Filter Removed Successfully";
    }

    @GetMapping(value = "/upcommngPayments")
    public List<LocalDate> upcommingPayments()
    {
        return userService.upcommingPayments();
    }

    @DeleteMapping(value = "/delete")
    public String deleteEmail(@RequestParam int id){
        userService.deleteEmail(id);
        return "Successfully Deleted";
    }
}