package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan
public class Main {
    public static void main(String[] args) {
       ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
       UserService userService = context.getBean(UserService.class);
        //System.out.println("Ищем всех");
        //userService.getAllUsers().stream().forEach(System.out::println);
        userService.deleteAllUsers();
        System.out.println("Добавляем одного пользователя");
        userService.createUser("Егоров Дмитрий Владимирович");
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Добавляем несколько пользователей");
        userService.createUsers(Arrays.asList(
                "Иванов Иван Иванович",
                "Петров Петр Петрочик",
                "Иваной Петр Иванович",
                "Сидоров Петр Андреевич",
                "Козлов Александр Сергеевич"));
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Ищем по имени");
        System.out.println(userService.getUserByName("Петров Петр Петрочик"));

        System.out.println("Удаляем по имени");
        userService.deleteUserByName("Козлов Александр Сергеевич");
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Удаляем всех");
        userService.deleteAllUsers();
        userService.getAllUsers().stream().forEach(System.out::println);


    }
}