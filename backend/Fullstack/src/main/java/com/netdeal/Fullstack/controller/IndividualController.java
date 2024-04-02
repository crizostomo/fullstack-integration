package com.netdeal.Fullstack.controller;

import com.netdeal.Fullstack.dto.request.IndividualRequest;
import com.netdeal.Fullstack.dto.response.IndividualResponse;
import com.netdeal.Fullstack.model.Individual;
import com.netdeal.Fullstack.mapper.request.IndividualRequestMapper;
import com.netdeal.Fullstack.mapper.response.IndividualResponseMapper;
import com.netdeal.Fullstack.service.IndividualService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualService;

    private final IndividualRequestMapper individualRequestMapper;

    private final IndividualResponseMapper individualResponseMapper;

    @GetMapping("/")
    public ResponseEntity<List<IndividualResponse>> getAllIndividuals() {
        List<Individual> individuals = individualService.getAllIndividuals();
        List<IndividualResponse> responseList = individuals.stream().map(individualResponseMapper::individualToIndividualResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualResponse> getIndividualById(@PathVariable Long id) {
        IndividualResponse response = individualResponseMapper.individualToIndividualResponse(individualService.getIndividualById(id));
        if (isNull(response)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<IndividualResponse> createIndividual(@RequestBody @Valid IndividualRequest individualRequest) {
        Individual individual = individualRequestMapper.individualToIndividualRequest(individualRequest);
        IndividualResponse response = individualResponseMapper.individualToIndividualResponse(individualService.createIndividual(individual));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndividualResponse> updateIndividual(@PathVariable @NotNull Long id, @RequestBody @Valid IndividualRequest individualRequest) {
        Individual individual = individualRequestMapper.individualToIndividualRequest(individualRequest);
        IndividualResponse response = individualResponseMapper.individualToIndividualResponse(individualService.updateIndividual(id, individual));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndividual(@PathVariable @NotNull Long id) {
        individualService.deleteIndividual(id);
        return ResponseEntity.ok().build();
    }
}
