package com.example.lab5spring;

import com.example.lab5spring.entity._Teacher2_;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.lab5spring.entity._Grupy_;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Lab5SpringApplicationTests {
     static int addedteacher,addedrate,addedgroup;

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testGetAllTeachers() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Order(2)
    void testGetAllGroups() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/group").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    void testGetAllRates() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/rating").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    void testGetTeacherbyID() throws Exception {
        Integer i = 1;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}",i).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    void testFill() throws Exception {
        Integer i = 1;
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/group/{id}/fill",i).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(6)
    void testgroupTeacher() throws Exception {
        Integer i = 1;
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/group/{id}/teacher",i).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(7)
    void testAddGroup() throws Exception {
        String groupJson = "{\"nazwa\": \"grupa1\", \"pojemnść\": 5}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/group")
                        .content(groupJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        String response = resultActions.andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        _Grupy_ addedGroup = objectMapper.readValue(response, _Grupy_.class);

        this.addedgroup = addedGroup.getId();
        System.out.println(addedgroup);
    }

    @Test
    @Order(8)
    void testAddRate() throws Exception {
        String rateJson = "{\"ocena\": 6, \"dataWystawieniaOceny\": \"2023-12-10\", \"komentarz\": \"test\", \"grupy\": 2}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/rating")
                        .content(rateJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        String response = resultActions.andReturn().getResponse().getContentAsString();

        System.out.println(response);

        if (response.contains("Rate created with ID: ")) {
            String[] parts = response.split(": ");
            this.addedrate = Integer.parseInt(parts[1].trim());
        }

        System.out.println(addedrate);
    }

    @Test
    @Order(9)
    void testAddTeacher() throws Exception {
        String postData = "{"
                + "\"imie\": \"test\","
                + "\"nazwisko\": \"test\","
                + "\"stan\": \"obecny\","
                + "\"rokUrodzenia\": 2000,"
                + "\"wynagrodzenie\": 4000.23,"
                + "\"grupa\": 1"
                + "}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/teacher")
                        .content(postData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        String response = resultActions.andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        _Teacher2_ addedGroup = objectMapper.readValue(response, _Teacher2_.class);

        addedteacher = addedGroup.getId();
        System.out.println(addedteacher);
    }


    @Test
    @Order(98)
    void Zdeleteteacher() throws Exception {
        System.out.println(addedteacher);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/teacher/{id}", addedteacher)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(99)
    void ZdeleteGroup() throws Exception {
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/group/{id}", addedgroup)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(100)
    void ZdeleteRate() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/rating/{id}", addedrate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(101)
    public void testGetTeachersAsCSV() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/teacher/csv")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/csv"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Disposition", "attachment; filename=teachers.csv"))
                .andDo(MockMvcResultHandlers.print());
    }
}
