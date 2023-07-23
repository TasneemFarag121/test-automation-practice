package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

public class UserSteps {

    public static User generateUser(){
        Faker faker = new Faker();
        String fisrtName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String paswword = "iLikeTestAutomation";

        return new User(fisrtName,lastName,email,paswword);


    }

    public static User getReigesterdUser(){
        User user = generateUser();
        UserApi.register(user);
        return user;
    }

    public static String getUserToken(){
        User user = generateUser();
        Response response = UserApi.register(user);
        //response.body().path("access_token"); -- This one works fine as well ?!!
        return response.path("access_token");

    }
}
