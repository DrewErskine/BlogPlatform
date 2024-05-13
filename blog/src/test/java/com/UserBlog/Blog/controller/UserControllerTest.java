package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        User user = new User();
        when(userService.findById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/profile/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testEditUserProfile() throws Exception {
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/edit-profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/edit-profile"))
                .andExpect(model().attributeExists("user"));
    }
}
