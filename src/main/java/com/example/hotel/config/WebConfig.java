package com.example.hotel.config;

import com.example.hotel.Security.UserDetailsServiceImpl;
import com.example.hotel.config.jwt.AuthEntryPointJwt;
import com.example.hotel.config.jwt.AuthTokenFilter;
import com.example.hotel.controller.RoomController;
import com.example.hotel.model.User;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
//Класс WebConfig реализует интерфейс WebMvcConfigurer, у которого есть
//целая куча методов, и настывает все по своему вкусу.
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {


    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        var templateResolver = new ClassLoaderTemplateResolver();
        //Каталог шаблонов
        templateResolver.setPrefix("templates/");
        // Кэшируемый ли шаблон
        templateResolver.setCacheable(false);
        //Задает новый (необязательный) суффикс для добавления
        // ко всем именам шаблонов,
        // чтобы преобразовать имена шаблонов в имена ресурсов
        templateResolver.setSuffix(".html");
        // Шаблн движка обслуживается HTML5
        templateResolver.setTemplateMode("HTML5");
        // Кодировка для чтения
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    //Когда Spring Boot обнаруживает зависимость
    // Thymeleaf в POM-файле Maven,
    //он автоматически настраивает механизм шаблонов Thymeleaf
    @Bean
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
    @Bean
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }


    // Автоматический контроллер
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      /*  http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/index/**").permitAll()
                .anyRequest().authenticated();*/
      //  http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
	.authorizeRequests(authorizeRequests ->
				authorizeRequests
				    .antMatchers("/indexAdmin").hasAnyRole("ADMIN", "WORKER")
                        .antMatchers("/ViewGuest").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewOrders").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewReservation").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewRooms").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewServices").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewTypeRooms").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/ViewUser").hasRole("ADMIN")
                        .antMatchers("/ViewWorker").hasRole("ADMIN")
                        .antMatchers("/ViewPayments").hasRole("ADMIN")
                        .antMatchers("/CreateGuest").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/CreateOrders/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/CreateReservation/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/CreateRoom").hasRole("ADMIN")
                        .antMatchers("/CreateService").hasRole("ADMIN")
                        .antMatchers("/CreateTypeRoom").hasRole("ADMIN")
                        .antMatchers("/signup/*").hasRole("ADMIN")
                        .antMatchers("/CreateWorker").hasRole("ADMIN")
                        .antMatchers("/CreatePayments/*").hasRole("ADMIN")
                        .antMatchers("/EditGuest/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/EditOrders/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/EditReservation/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/EditRoom/*").hasRole("ADMIN")
                        .antMatchers("/EditService/*").hasRole("ADMIN")
                        .antMatchers("/EditTypeRoom/*").hasRole("ADMIN")
                        .antMatchers("/EditUser/*").hasRole("ADMIN")
                        .antMatchers("/EditWorker/*").hasRole("ADMIN")
                        .antMatchers("/EditPayments/*").hasRole("ADMIN")
                        .antMatchers("/DeleteOrders/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/DeleteReservation/*").hasAnyRole("ADMIN","WORKER")
                        .antMatchers("/DeleteRooms/*").hasRole("ADMIN")
                        .antMatchers("/DeleteServices/*").hasRole("ADMIN")
                        .antMatchers("/DeleteTypeRooms/*").hasRole("ADMIN")
                        .antMatchers("/DeleteUser/*").hasRole("ADMIN")
                        .antMatchers("/DeleteWorker/*").hasRole("ADMIN")
                        .antMatchers("/DeletePayments/*").hasRole("ADMIN")
					.antMatchers("/").permitAll())
		.httpBasic().realmName("org team")
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }



}
