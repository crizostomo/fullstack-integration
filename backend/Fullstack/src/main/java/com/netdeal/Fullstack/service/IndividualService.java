package com.netdeal.Fullstack.service;

import com.netdeal.Fullstack.model.Individual;

import java.util.List;

public interface IndividualService {

    List<Individual> getAllIndividuals();

    Individual getIndividualById(Long id);

    Individual createIndividual(Individual individual);

    Individual updateIndividual(Long id, Individual newIndividual);

    void deleteIndividual(Long id);

}
