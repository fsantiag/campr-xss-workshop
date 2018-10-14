package com.thoughtworks.securityinourdna;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes=InjectionWorkshopMain.class)
@RunWith(SpringRunner.class)
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testListProducts() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }


    @Test
    public void all_should_not_allow_obvious_xss_attacks() throws Exception {
        this.mockMvc.perform(
                post("/").
                        param("invoice", "<script>alert('xss!')</script>")
        ).andExpect(status().isFound());

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("<script>alert('xss!')</script>"))));
    }

}
