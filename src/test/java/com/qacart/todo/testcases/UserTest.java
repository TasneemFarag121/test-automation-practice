package com.qacart.todo.testcases;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.UserSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {

 @Test
 public void shouldBeAbleToRegister(){

     User user = UserSteps.generateUser();

     Response response = UserApi.register(user);

    User returnedUser = response.body().as(User.class);

    assertThat(response.statusCode(),equalTo(201));
    assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));

 }
 @Test
    public void shouldNotBeAbleToRegisterWithTheSameEmail(){

     User user = UserSteps.getReigesterdUser();

     Response response = UserApi.register(user);

     Error error = response.body().as(Error.class);

     assertThat(response.statusCode() , equalTo(400));
     assertThat(error.getMessage(),equalTo(ErrorMessages.EMAIL_IS_ALREADY_REGISTERED));
 }
 @Test
     public  void shouldBeAbleToLogin(){

     User user = UserSteps.getReigesterdUser();
     User loginData = new User(user.getEmail(),user.getPassword());

     Response response = UserApi.login(loginData);

     User returnedUser = response.body().as(User.class);

     assertThat(response.statusCode() , equalTo(200));
     assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));
     assertThat(returnedUser.getAccessToken(),not(equalTo(null)));

 }
 @Test
    public void shouldNotBeAbleToLoginIfThePasswordIsNotCorrect(){

     User user = UserSteps.getReigesterdUser();
     User loginData = new User(user.getEmail(),"wrongPassword");

     Response response = UserApi.login(loginData);

     Error error = response.body().as(Error.class);

     assertThat(response.statusCode() , equalTo(401));
     assertThat(error.getMessage(),equalTo(ErrorMessages.EMAIL_OR_PASSWORD_IS_WRONG));

      }



}
