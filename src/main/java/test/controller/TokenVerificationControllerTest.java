package main.java.test.controller;

import main.java.config.MvcWebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MvcWebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@WebAppConfiguration("/WebContent")
class TokenVerificationControllerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).apply(springSecurity()).build();
    }


    @Test
    @WithMockUser(username = "admin", password = "qwer", authorities = {"ROLE_ADMIN"})
    void shouldReturnOkForAdminAccessingAdminPage() throws Exception {

        this.mockMvc.perform(get("/account/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "przemyslaw.gesieniec@gmail.com", password = "qwer", authorities = {"ROLE_USER"})
    void shouldReturnAccessDeniedForUserAccessingAdminPage() throws Exception {

        this.mockMvc.perform(get("/account/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "qwer", authorities = {"ROLE_ADMIN"})
    void shouldReturnAccessDeniedForAdminAccessingUserPage() throws Exception {

        this.mockMvc.perform(get("/account/user"))
                .andExpect(status().isForbidden());
    }

}
