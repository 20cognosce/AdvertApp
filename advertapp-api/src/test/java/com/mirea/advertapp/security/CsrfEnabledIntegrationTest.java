package com.mirea.advertapp.security;

import com.mirea.advertapp.controller.UserController;
import com.mirea.advertapp.controller.mapper.AdvertMapperImpl;
import com.mirea.advertapp.controller.mapper.UserMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
