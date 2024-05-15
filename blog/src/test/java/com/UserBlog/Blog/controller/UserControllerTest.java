package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testUserProfile() throws Exception {
        Long userId = 1L;
        User user = new User(); // Assuming default constructor exists
        when(userService.findById(userId)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/api/user/profile/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"));
    }

    @Test
    public void testEditUserProfile() throws Exception {
        User currentUser = new User(); // Assuming default constructor exists
        when(userService.getCurrentUser()).thenReturn(currentUser);

        mockMvc.perform(get("/api/user/edit-profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/edit-profile"));
    }

    @Test
    public void testUpdateUserProfile() throws Exception {
        // Assuming we have a User object set up
        User user = new User();
        user.setId(1L);  // Set user ID as something not null

        // Mock the behavior of userService if required, for example:
        // when(userService.updateUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user/edit-profile")
                .flashAttr("userForm", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/**/profile/{userId}"));  // Correct usage
    }
}
