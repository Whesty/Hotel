package com.example.hotel.forms;

import com.example.hotel.model.ERole;
import com.example.hotel.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String login;
    private String password;
    private String role;
    private int worker_id;
    private int user_id;
}
