package com.qacart.todo.testcases;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoTest {

    @Test
    public void shouldBeAbleToAddTodo(){

        Todo todo = TodoSteps.generateTodo();

        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(todo,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));


    }
    public void shouldBeAbleToAddTodo_git22222(){

        Todo todo = TodoSteps.generateTodo();

        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(todo,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));


    }
    public void shouldBeAbleToAddTodo_git33333(){

        Todo todo = TodoSteps.generateTodo();

        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(todo,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));


    }
    @Test
    public void shouldNotBeAbleToAddTodoIfIsCompletedIsMissing(){

        Todo todo = new Todo("learn Appuim");
        String token = UserSteps.getUserToken();
        Response response = TodoApi.addTodo(todo,token);
        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED));

    }
    @Test
    public void shouldBeAbleToGetTodo(){


        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateTodo();
        String todoId=TodoSteps.getTodoId(todo,token);
        Response response = TodoApi.getTodo(token,todoId);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),equalTo(false));


    }
    @Test
    public void shouldBeAbleToDeleteTodo(){
        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateTodo();
        String todoId =TodoSteps.getTodoId(todo,token);
        Response response = TodoApi.deleteTodo(token,todoId);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),equalTo(false));

    }
}
