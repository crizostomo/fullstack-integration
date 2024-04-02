package com.netdeal.Fullstack.controller;

import com.netdeal.Fullstack.dto.request.IndividualRequest;
import com.netdeal.Fullstack.dto.response.IndividualResponse;
import com.netdeal.Fullstack.mapper.request.IndividualRequestMapper;
import com.netdeal.Fullstack.mapper.response.IndividualResponseMapper;
import com.netdeal.Fullstack.model.Individual;
import com.netdeal.Fullstack.service.IndividualServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IndividualControllerTest {
    @InjectMocks
    private IndividualController individualController;

    @Mock
    private IndividualServiceImpl individualService;

    @Mock
    private IndividualRequestMapper individualRequestMapper;

    @Mock
    private IndividualResponseMapper individualResponseMapper;


    @Test
    void getAllIndividuals_returnListOfIndividualResponse() {
        List<Individual> individuals = new ArrayList<>();
        individuals.add(new Individual(1L, "John", "pass", 100, null, null));
        individuals.add(new Individual(2L, "Leon", "pass", 200, null, null));

        IndividualResponse individualResponse = new IndividualResponse(1L, "John", 100, null);
        IndividualResponse individualResponse1 = new IndividualResponse(2L, "Leon", 200, null);

        Mockito.when(individualService.getAllIndividuals()).thenReturn(individuals);
        Mockito.when(individualResponseMapper.individualToIndividualResponse(individuals.get(0))).thenReturn(individualResponse);
        Mockito.when(individualResponseMapper.individualToIndividualResponse(individuals.get(1))).thenReturn(individualResponse1);

        ResponseEntity<List<IndividualResponse>> response = individualController.getAllIndividuals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getName());
        assertEquals("Jane", response.getBody().get(1).getName());
    }

    @Test
    void getIndividualById_returnIndividualResponse() {
        Long id = 1L;
        Individual individual = new Individual(id, "John", "pass", 100, null, null);
        IndividualResponse individualResponse = new IndividualResponse(id, "John", 100, null);

        Mockito.when(individualService.getIndividualById(id)).thenReturn(individual);
        Mockito.when(individualResponseMapper.individualToIndividualResponse(individual)).thenReturn(individualResponse);

        ResponseEntity<IndividualResponse> response = individualController.getIndividualById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
    }

    @Test
    void createIndividual_returnIndividualResponse() {
        IndividualRequest individualRequest = new IndividualRequest("John", "pass", 1L);
        Individual individual = new Individual(1L, "John", "pass", 100, null, null);
        IndividualResponse individualResponse = new IndividualResponse(1L, "John", 100, null);

        Mockito.when(individualRequestMapper.individualToIndividualRequest(individualRequest)).thenReturn(individual);
        Mockito.when(individualService.createIndividual(individual)).thenReturn(individual);
        Mockito.when(individualResponseMapper.individualToIndividualResponse(individual)).thenReturn(individualResponse);

        ResponseEntity<IndividualResponse> response = individualController.createIndividual(individualRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
    }

    @Test
    void getAllIndividuals_returnEmptyList() {
        List<Individual> individuals = new ArrayList<>();
        Mockito.when(individualService.getAllIndividuals()).thenReturn(individuals);

        ResponseEntity<List<IndividualResponse>> response = individualController.getAllIndividuals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void getIndividualById_returnNotFound() {
        Long id = 1L;
        Mockito.when(individualService.getIndividualById(id)).thenReturn(null);
        Mockito.when(individualResponseMapper.individualToIndividualResponse(null)).thenReturn(null);

        ResponseEntity<IndividualResponse> response = individualController.getIndividualById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void update_IndividualNameOnly() {
        IndividualRequest individualRequest = new IndividualRequest();
        individualRequest.setName("John Updated");
        individualRequest.setPassword("VeryComplicated@345@12");

        IndividualResponse individualResponse = new IndividualResponse();
        individualResponse.setId(1L);
        individualResponse.setName("John Updated");
        individualResponse.setScore(100);

        Individual updatedIndividual = new Individual();
        updatedIndividual.setId(1L);
        updatedIndividual.setName("John Updated");
        updatedIndividual.setPassword("VeryComplicated@345@12");

        Mockito.when(individualRequestMapper.individualToIndividualRequest(individualRequest)).thenReturn(updatedIndividual);

        Mockito.when(individualService.updateIndividual(1L, updatedIndividual)).thenReturn(updatedIndividual);

        Mockito.when(individualResponseMapper.individualToIndividualResponse(updatedIndividual)).thenReturn(individualResponse);

        ResponseEntity<IndividualResponse> response = individualController.updateIndividual(1L, individualRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(individualResponse, response.getBody());
    }

    @Test
    void successfullyDeleteIndividualWithValidId() {
        ResponseEntity<Void> response = individualController.deleteIndividual(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}