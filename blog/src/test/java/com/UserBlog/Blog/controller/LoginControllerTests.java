package com.UserBlog.Blog.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTests{

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void testLogin() {
        String viewName = loginController.login();
        assertEquals("login.html", viewName);
    }
}
