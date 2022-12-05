package com.example.hotel.controller;

import com.example.hotel.Security.UserDetailsImpl;
import com.example.hotel.config.jwt.JwtUtils;
import com.example.hotel.forms.UserForm;
import com.example.hotel.model.ERole;
import com.example.hotel.model.Role;
import com.example.hotel.model.User;
import com.example.hotel.model.Worker;
import com.example.hotel.services.EmailSenderService;
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
import java.util.List;
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
    private final EmailSenderService emailSenderService;

    @Autowired
    JwtUtils jwtUtils;

    private final WorkerServices workerServices;
    User auth_user;

    public UserController(EmailSenderService emailSenderService, WorkerServices workerServices) {
        this.emailSenderService = emailSenderService;
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

        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getId());
        System.out.println(SecurityContextHolder.getContext());
        String role = userDetails.getAuthorities().stream().findFirst().toString();
        User user = userRespository.getById(userDetails.getId());
        role = role.substring(8, role.length());
        model.addAttribute("user", user);
        auth_user = user;
        //emailSenderService.sendSimpleEmail(user.getWorker().getEmail(), "Вход в систему", "Вы успешно вошли в систему");
        model.addAttribute("role", role);
        if (role.equals("[ADMIN]") || role.equals("[WORKER]")) {
            return "redirect:/indexAdmin";
        }  else {
            return "redirect:/index";
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        if(auth_user != null) {
            if (auth_user.getRoles().equals("[ADMIN]") || auth_user.getRoles().equals("[WORKER]")) {
                return "redirect:/indexAdmin";
            } else {
                return "index";
            }
        }
        return "index";
    }
    @GetMapping("/logouT")
    public String logout(Model model) {
        auth_user = null;
        SecurityContextHolder.clearContext();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        "GUEST",
                        "GUEST"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/index";
    }
    @GetMapping("/indexAdmin")
    public String indexAdmin(Model model) {
        System.out.println(SecurityContextHolder.getContext());
        model.addAttribute("user", auth_user);
        return "indexAdmin";
    }

   /* @GetMapping("/menu")
    public String Menu(Model model) {
        model.addAttribute("User", auth_user);
        return "menu";
    }*/
    @PostMapping("/signup")
    public ModelAndView registerUser(Model model, @ModelAttribute("signUpForm") UserForm signUpRequest) {

        ModelAndView modelAndView = new ModelAndView("index");
        ModelAndView modelAndView2 = new ModelAndView("signup");
        Worker worker = null;
        try {
            worker = workerServices.findById(signUpRequest.getWorker_id());
        } catch (Exception e) {
            modelAndView2.addObject("message", "Такого пользователя не существует не существует");
            return modelAndView2;
        }
        if(worker != null){

        if(worker.getPosition().equals("Admin")){
            signUpRequest.setRole("ADMIN");
        }
        else signUpRequest.setRole("WORKER");
        }else signUpRequest.setRole("GUEST");
        if (userRespository.existsByLogin(signUpRequest.getLogin())) {
            model.addAttribute("errorMessage", "Такого рабочего не существует");
            return modelAndView2;
        }
        try{
        User user = new User(
                signUpRequest.getLogin(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        user.setWorker(worker);
        Set<Role> roles = new HashSet<>();


                switch (signUpRequest.getRole()) {
                    case "ADMIN":
                        Role adminRole = roleRepository
                                .findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "WORKER":
                        Role modRole = roleRepository
                                .findByName(ERole.WORKER)
                                .orElseThrow(() -> new RuntimeException("Error, Role WORKER is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.GUEST)
                                .orElseThrow(() -> new RuntimeException("Error, Role GUEST is not found"));
                        roles.add(userRole);

        }
        user.setRoles(roles.stream().findFirst().get());

        userRespository.save(user);
        return  modelAndView;}
        catch (Exception err){
            modelAndView2.addObject("errorMessage", err.getMessage());
            return modelAndView2;
        }
    }

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

    @GetMapping("/ViewUsers")
    public String ListUesers(Model model){
        List<User> userList = userRespository.findAll();
        model.addAttribute("userList", userList);
        return "ViewUsers";
    }

}