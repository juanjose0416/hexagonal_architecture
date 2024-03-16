package com.students.grades_hexagonal.infraestructure.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.AverageGradesDto;
import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.app.usecase.GradeHandlerUseCase;
import com.students.grades_hexagonal.infraestructure.in.handler.RestExceptionHandler;

@ExtendWith(MockitoExtension.class)
class StudentGradesControllerTest {

    @Mock
    private GradeHandlerUseCase gradeHandlerUseCase;

    @InjectMocks
    private StudentGradesController studentGradesController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentGradesController)
                .setControllerAdvice(new RestExceptionHandler()).build();
        mapper = new ObjectMapper().findAndRegisterModules().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void createGrade() throws Exception {
        CreateGradeRequest createGradeRequest = new CreateGradeRequest();
        MvcResult mvcResult = mockMvc.perform(
                post("/grades/student1/1").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createGradeRequest))).andExpect(status().isCreated()).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        verify(gradeHandlerUseCase).create(createGradeRequest, "student1", 1L);
    }

    @Test
    void getGrades() throws Exception {
        GradeStudentResponse gradeStudentResponse = new GradeStudentResponse();
        when(gradeHandlerUseCase.getAllGrades(anyString())).thenReturn(gradeStudentResponse);
        MvcResult mvcResult = mockMvc.perform(get("/grades/studentId")).andExpect(status().isOk()).andReturn();
        verify(gradeHandlerUseCase).getAllGrades("studentId");
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateGrade() throws Exception {
        CreateGradeRequest createGradeRequest = new CreateGradeRequest();
        MvcResult mvcResult = mockMvc.perform(put("/grades/student1/1").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createGradeRequest))).andExpect(status().isOk()).andReturn();
        verify(gradeHandlerUseCase).updateGrade("student1", 1L, createGradeRequest);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void getAverage() throws Exception {
        GradeResponseDto gradeResponseDto = new GradeResponseDto();
        when(gradeHandlerUseCase.getAverage(anyString(), anyLong())).thenReturn(gradeResponseDto);
        MvcResult mvcResult = mockMvc.perform(get("/grades/average/studentId/1")).andExpect(status().isOk())
                .andReturn();
        verify(gradeHandlerUseCase).getAverage("studentId", 1L);
        assertEquals(asJsonString(gradeResponseDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllStudents() throws Exception {
        AverageGradesDto averageGradesDto = new AverageGradesDto();
        when(gradeHandlerUseCase.getAllAverage(anyLong())).thenReturn(Collections.singletonList(averageGradesDto));
        MvcResult mvcResult = mockMvc.perform(get("/grades/all/1")).andExpect(status().isOk()).andReturn();
        verify(gradeHandlerUseCase).getAllAverage(1L);
        assertEquals(asJsonString(Collections.singletonList(averageGradesDto)),
                mvcResult.getResponse().getContentAsString());
    }

    public String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}