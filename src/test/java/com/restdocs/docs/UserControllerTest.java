package com.restdocs.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restdocs.domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("docs/output");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void getUsers() throws Exception{
        this.mockMvc.perform(get("/api/user")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(document("user/get-all-users"));

        // Invoke the /api/users of the service and indicate that an
        // application/json response required.

        // Assert that the service produced the expected response

        // Document the call to service, writing the snippets into a
        // directory user/...
        // The snippets are written by a RestDocumentationResultHandler
        // You can obtain an instance of this class from the static
        // document method on MockMvcRestDocumentation
    }

    @Test
    public void getUserById() throws Exception{
        this.mockMvc.perform(get("/api/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/get-user-by-id"));
    }

    @Test
    public void deleteUserByUsername() throws Exception{
        this.mockMvc.perform(delete("/api/user/beka"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/delete-user-by-username"));
    }

    @Test
    public void addUser() throws Exception{
        User u1 = new User();
        u1.setUsername("test");
        u1.setEmail("test@tim.com");
        u1.setFirstName("mest");
        u1.setLastName("oldum");
        u1.setPhoneNumber("1231231222");

        String body = (new ObjectMapper()).valueToTree(u1).toString();

        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andDo(document("user/add-user"));
    }

    @Test
    public void updateUser() throws Exception{
        User u1 = new User();
        u1.setId(5L);
        u1.setUsername("test");
        u1.setEmail("test@tim.com");
        u1.setFirstName("mest");
        u1.setLastName("oldum");
        u1.setPhoneNumber("1231231222");

        String body = (new ObjectMapper()).valueToTree(u1).toString();

        this.mockMvc.perform(put("/api/user/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/update-user-by-id"));

    }

}
