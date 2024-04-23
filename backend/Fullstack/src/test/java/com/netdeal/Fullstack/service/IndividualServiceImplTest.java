package com.netdeal.Fullstack.service;

import com.netdeal.Fullstack.model.Individual;
import com.netdeal.Fullstack.repository.IndividualRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IndividualServiceImplTest {

    @InjectMocks
    private IndividualServiceImpl individualService;

    @Mock
    private IndividualRepository individualRepository;

    @Test
    void getAllPeople_returnListOfIndividualsWithIndividualParent_null() {
        List<Individual> individuals = new ArrayList<>();
        Individual individual = new Individual();
        individual.setId(1L);
        individual.setName("John");
        individual.setPassword("pass");
        individual.setScore(100);
        individual.setParentIndividual(null);
        individuals.add(individual);

        Individual individual1 = new Individual();
        individual1.setId(2L);
        individual1.setName("Jane");
        individual1.setPassword("password");
        individual1.setScore(90);
        individual1.setParentIndividual(null);
        individuals.add(individual1);

        Mockito.when(individualRepository.findByParentIndividualIsNull()).thenReturn(individuals);

        List<Individual> currentIndividuals = individualService.getAllIndividuals();

        assertEquals(individuals, currentIndividuals);
    }

    @Test
    void getIndividualById_returnIndividualWithGivenId() {
        Long id = 1L;
        Individual expectedIndividual = new Individual();
        expectedIndividual.setId(id);
        expectedIndividual.setName("John");
        expectedIndividual.setPassword("pass");
        expectedIndividual.setScore(100);
        expectedIndividual.setParentIndividual(null);

        Mockito.when(individualRepository.findById(id)).thenReturn(Optional.of(expectedIndividual));

        Individual currentIndividual = individualService.getIndividualById(id);

        assertEquals(expectedIndividual, currentIndividual);
    }

    @Test
    void createIndividual_saveNewIndividualToDb() {
        Individual individual = new Individual();
        individual.setName("John");
        individual.setPassword("pass");
        individual.setScore(100);
        individual.setParentIndividual(null);

        Individual savedIndividual = new Individual();
        savedIndividual.setId(1L);
        savedIndividual.setName("John");
        savedIndividual.setPassword("encoded_pass");
        savedIndividual.setScore(100);
        savedIndividual.setParentIndividual(null);

        Mockito.when(individualRepository.save(individual)).thenReturn(savedIndividual);

        Individual currentIndividual = individualService.createIndividual(individual);

        assertEquals(savedIndividual, currentIndividual);
    }

    @Test
    void getIndividualById_throwEntityNotFoundException_when_IndividualWithGivenIdDoesNotExist() {
        Long id = 1L;

        Mockito.when(individualRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> individualService.getIndividualById(id));
    }

    @Test
    void updateIndividual_updateExistingIndividual() {
        Individual individual = new Individual();
        individual.setId(1L);
        individual.setName("John");
        individual.setPassword("pass");
        individual.setScore(0);

        Individual updatedIndividual = new Individual();
        updatedIndividual.setId(1L);
        updatedIndividual.setName("John");
        updatedIndividual.setPassword("newpass");
        updatedIndividual.setScore(0);

        Mockito.when(individualRepository.findById(1L)).thenReturn(Optional.of(individual));
        Mockito.when(individualRepository.save(individual)).thenReturn(updatedIndividual);

        Individual result = individualService.updateIndividual(1L, updatedIndividual);

        Mockito.verify(individualRepository).save(updatedIndividual);

        assertEquals(updatedIndividual, result);
    }

    @Test
    void deleteIndividual_deleteExistingIndividual() {
        Long id = 1L;
        Individual individual = new Individual();
        individual.setId(id);
        Mockito.when(individualRepository.findById(id)).thenReturn(Optional.of(individual));

        individualService.deleteIndividual(id);

        Mockito.verify(individualRepository).delete(individual);
    }

}