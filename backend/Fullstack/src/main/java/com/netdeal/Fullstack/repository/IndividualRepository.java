package com.netdeal.Fullstack.repository;

import com.netdeal.Fullstack.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    List<Individual> findByParentIndividualIsNull();

}
