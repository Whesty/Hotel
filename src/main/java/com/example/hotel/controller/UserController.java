package com.example.hotel.controller;

import com.example.hotel.Security.UserDetailsImpl;
import com.example.hotel.config.jwt.JwtUtils;
import com.example.hotel.forms.UserForm;
import com.example.hotel.model.ERole;
import com.example.hotel.model.Role;
import com.example.hotel.model.User;
import com.example.hotel.services.WorkerServices;
import com.example.hotel.repository.RoleRepository;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRespository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    private final WorkerServices workerServices;
    public static User auth_user;

    public UserController(WorkerServices workerServices) {
        this.workerServices = workerServices;
    }

    @PostMapping("/signin")
    public String authUser(Model model, @ModelAttribute("userForm") UserForm userForm) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userForm.getLogin(),
                        userForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().toString();
        User user = userRespository.getById(userDetails.getId());
        role = role.substring(8, role.length());
        model.addAttribute("user", user);
        auth_user = user;
        model.addAttribute("role", role);
        if (role.equals("[ROLE_ADMIN]")) {
            return "redirect:/indexAdmin";
        } else if (role.equals("[ROLE_WORKER]")) {
            return "redirect:/indexWorker";
        } else {
            return "redirect:/index";
        }
    }
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/indexAdmin")
    public String indexAdmin(Model model) {
        model.addAttribute("user", auth_user);
        return "indexAdmin";
    }
    @GetMapping("/indexWorker")
    public String indexWorker(Model model) {
        model.addAttribute("user", auth_user);
        return "indexWorker";
    }
    @GetMapping("/menu")
    public String Menu(Model model) {
        model.addAttribute("User", auth_user);
        return "menu";
    }
    @PostMapping("/signup")
    public ModelAndView registerUser(Model model, @ModelAttribute("signUpForm") UserForm signUpRequest) {
        signUpRequest.setRole("ROLE_WORKER");
        ModelAndView modelAndView = new ModelAndView("index");
        ModelAndView modelAndView2 = new ModelAndView("signup");
        if (userRespository.existsByLogin(signUpRequest.getLogin())) {
            return modelAndView2;
        }

        if (userRespository.existsByLogin(signUpRequest.getLogin())) {
            return modelAndView2;
        }
        User user = new User(
                signUpRequest.getLogin(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();


                switch (signUpRequest.getRole()) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "ROLE_WORKER":
                        Role modRole = roleRepository
                                .findByName(ERole.ROLE_WORKER)
                                .orElseThrow(() -> new RuntimeException("Error, Role WORKER is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_GUEST)
                                .orElseThrow(() -> new RuntimeException("Error, Role GUEST is not found"));
                        roles.add(userRole);

        }
        user.setRoles(roles.stream().findFirst().get());
        user.setWorker(workerServices.findById(signUpRequest.getWorker_id()));
        userRespository.save(user);
        return  modelAndView;}

    @GetMapping("/signin")
    public ModelAndView authUser(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }
    @GetMapping("/signup")
    public ModelAndView RegUser(Model model) {
        UserForm signUpForm = new UserForm();
        model.addAttribute("signUpForm", signUpForm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

}