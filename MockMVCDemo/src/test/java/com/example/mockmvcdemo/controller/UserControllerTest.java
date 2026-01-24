package com.example.mockmvcdemo.controller;

import com.example.mockmvcdemo.exception.UserNotFoundException;
import com.example.mockmvcdemo.model.User;
import com.example.mockmvcdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest
{
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private UserService userService;

   @Test
    void testGetUser() throws Exception
   {
       User user = new User(1L,"Priyanka","priyanka@test.com");

       Mockito.when(userService.getById(1L)).thenReturn(user);

       mockMvc.perform(get("/api/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Priyanka"));
   }

   @Test
    void testCreatedUser() throws Exception
   {
       User user = new User(1L, "Priyanka", "priyanka@test.com");

       Mockito.when(userService.save(any(User.class))).thenReturn(user);
       mockMvc.perform(post("/api/users")
               .contentType(MediaType.APPLICATION_JSON)
               .content("""
                       {
                       "name":"Priyanka",
                       "email":"priyanka@test.com"
                       }
                       """))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.email").value("priyanka@test.com"));
   }

   @Test
    void testValidationError()throws Exception
   {
       mockMvc.perform(post("/api/users")
               .contentType(MediaType.APPLICATION_JSON)
               .content("""
                       {
                       "name":"",
                       "email":"invalid"
                       }
                       """))
               .andExpect(status().isBadRequest());
   }

   @Test
    void testUserNotFound()throws Exception
   {
       Mockito.when(userService.getById(99L))
               .thenThrow(new
                       UserNotFoundException("Not Found"));

       mockMvc.perform(get("/api/users/99"))
               .andExpect(status().isNotFound());
   }


}
