package com.example.hotel.forms;

import com.example.hotel.model.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerForm {
    private Integer id;
    private String lastname; //Фамилия
    private String firstname; //Имя
    private String secendname; //Отчество
    private String position; // Должность
    private String phone; //
    private String email;
    private int experience; // Опыт работы
    public WorkerForm(Worker worker){
        this.id = worker.getId();
        this.lastname = worker.getLastname();
        this.firstname = worker.getFirstname();
        this.secendname = worker.getSecendname();
        this.position = worker.getPosition();
        this.phone = worker.getPhone();
        this.email = worker.getEmail();
        this.experience = worker.getExperience();
    }
    public Worker toWorker(){
        return new Worker(id, lastname, firstname, secendname, position, phone, email, experience);
    }
}
