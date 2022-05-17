package com.springboot.swisscom.dob;

import com.springboot.swisscom.model.MyEmailGUI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountService extends JpaRepository<MyEmailGUI, Integer> {
    List<MyEmailGUI> findById(int Id);
    List<MyEmailGUI> findByEmail(String email);
}
