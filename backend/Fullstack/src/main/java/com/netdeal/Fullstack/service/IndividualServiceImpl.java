package com.netdeal.Fullstack.service;

import com.netdeal.Fullstack.model.Individual;
import com.netdeal.Fullstack.repository.IndividualRepository;
import com.netdeal.Fullstack.utils.PasswordEncryption;
import com.netdeal.Fullstack.utils.PasswordHandler;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService {
    private final IndividualRepository individualRepository;

    public List<Individual> getAllIndividuals() {
        List<Individual> individualList = individualRepository.findByParentIndividualIsNull();
        return individualList;
    }

    public Individual getIndividualById(Long id) {
        Individual individual = individualRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Individual not found with id: " + id));
        return individual;
    }

    public Individual createIndividual(Individual individual) {
        individual.setScore(calculatePasswordScore(individual.getPassword()));
        individual.setPassword(PasswordEncryption.encode(individual.getPassword()));
        Individual saved = individualRepository.save(individual);
        return saved;
    }

    public Individual updateIndividual(Long id, Individual newIndividual) {
        Individual databaseIndividual = getIndividualById(id);
        databaseIndividual.setScore(calculatePasswordScore(newIndividual.getPassword()));
        databaseIndividual.setPassword(PasswordEncryption.encode(newIndividual.getPassword()));
        databaseIndividual.setIndividualParent(newIndividual.getIndividualParent());
        Individual result = individualRepository.save(newIndividual);
        return result;
    }

    public void deleteIndividual(Long id) {
        Individual databaseIndividual = getIndividualById(id);
        individualRepository.delete(databaseIndividual);
    }

    private int calculatePasswordScore(String password) {
        PasswordHandler passwordHandler = new PasswordHandler();
        return passwordHandler.getScore(password);
    }

}
