package com.mirea.advertappback.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirea.advertappback.controller.UserController;
import com.mirea.advertappback.controller.mapper.AdvertMapperImpl;
import com.mirea.advertappback.controller.mapper.UserMapperImpl;
import com.mirea.advertappback.domain.dto.UserCreateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserMapperImpl.class, AdvertMapperImpl.class})
public class CsrfEnabledIntegrationTest {
    @Autowired
    private MockMvc mvc;

    /*protected RequestPostProcessor testUser() {
        return user("user").password("password");
    }

    protected String getUserCreateDto() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(UserCreateDto.builder()
                .firstName("Test-user first name")
                .lastName("Test-user last name")
                .email("test-user@email.ru")
                .password(new char[] {'p', 'a', 's', 's'})
                .build());
    }

    @Test
    public void givenNoCsrf_whenGetUsers_thenOk() throws Exception {
        mvc.perform(
                get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .with(testUser())
        ).andExpect(status().isOk());
    }

    @Test
    public void givenNoCsrf_whenCreateUser_thenForbidden() throws Exception {
        mvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserCreateDto())
                        .with(testUser())
        ).andExpect(status().isForbidden());
    }

    @Test
    public void givenCsrf_whenCreateUser_thenCreated() throws Exception {
        mvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserCreateDto())
                        .with(testUser()).with(csrf())
        ).andExpect(status().isCreated());
    }*/
}
