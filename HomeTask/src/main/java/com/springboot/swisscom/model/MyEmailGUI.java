package com.springboot.swisscom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEmailGUI {
	@Id
	@GeneratedValue
	private int Id;
	private String email;
	private	LocalDate startDate = LocalDate.now();
	private boolean isPremium;
	private LocalDate expireDate;
	private String addressBook;
	private int quote;
	public MyEmailGUI(String email, boolean isPremium, String addressBook){
		this.email= email;
		this.isPremium = isPremium;
		this.addressBook = addressBook;
	}
	public void purchasePremium(){
		startDate = LocalDate.now();
		int month = startDate.now().getMonthValue();
		int year = startDate.now().getYear();
		if(month==12){
			month = 1;
			year++;
			expireDate = LocalDate.of(year, month, startDate.getDayOfMonth());
			addressBook = "Premium Package";
		}
		quote = 1;
	}

	public void getFilter(){

	}
}
