package com.example.hotel.model;

import com.example.hotel.services.GuestServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

//Обьект Гость
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastname; //Фамилия
    private String firstname; //Имя
    private String secendname; //Отчество
    private String email; //Почта
    private Date birthday; //Дата рождения

    /*public void SetToServices(){
        GuestServices guestServices = new GuestServices();
        Guest newGuest = guestServices.findGuest(this.id);
        if (newGuest != null){
            this.lastname = newGuest.lastname;
            this.firstname = newGuest.firstname;
            this.secendname = newGuest.secendname;
            this.email = newGuest.email;
            this.birthday = newGuest.birthday;
        }
    }*/
    public String getFullName(){
        return lastname + " " + firstname + " " + secendname;
    }

}
