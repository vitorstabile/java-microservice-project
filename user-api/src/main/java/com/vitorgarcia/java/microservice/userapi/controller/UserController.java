package com.vitorgarcia.java.microservice.userapi.controller;

import com.vitorgarcia.java.microservice.userapi.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public static List<UserDTO> users = new ArrayList<UserDTO>();

    @PostConstruct
    public void initiateList()	{
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Snow");
        userDTO.setTaxId("111222333");
        userDTO.setAddress("Lombard Street");
        userDTO.setEmail("johnsnow@abc.com");
        userDTO.setPhone("9191919191");
        userDTO.setSubscriptionDate(new Date());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setName("Mike Creed");
        userDTO2.setTaxId("444555666");
        userDTO2.setAddress("Gold Street");
        userDTO2.setEmail("mikecreed@abc.com");
        userDTO2.setPhone("9292929292");
        userDTO2.setSubscriptionDate(new Date());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setName("Luci Lee");
        userDTO3.setTaxId("777888999");
        userDTO3.setAddress("Green Street");
        userDTO3.setEmail("lucilee@abc.com");
        userDTO3.setPhone("9393939393");
        userDTO3.setSubscriptionDate(new Date());

        users.add(userDTO);
        users.add(userDTO2);
        users.add(userDTO3);
    }


    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return users;
    }

    @GetMapping("/users/{taxId}")
    public UserDTO getUserByTaxId(@PathVariable String taxId) {
        for (UserDTO user : users) {
            if (user.getTaxId().equalsIgnoreCase(taxId)) {
                return user;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    public UserDTO addUser(@RequestBody UserDTO obj) {
        obj.setSubscriptionDate(new Date());
        users.add(obj);
        return obj;
    }

    @DeleteMapping("/users/{taxId}")
    public boolean deleteUser(@PathVariable String taxId) {
        for (UserDTO user : users) {
            if (user.getTaxId().equalsIgnoreCase(taxId)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }
}
