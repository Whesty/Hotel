package com.example.hotel.config;

import com.example.hotel.controller.RoomController;
import com.example.hotel.repository.RoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
public class WebConfig implements WebMvcConfigurer {
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





}
