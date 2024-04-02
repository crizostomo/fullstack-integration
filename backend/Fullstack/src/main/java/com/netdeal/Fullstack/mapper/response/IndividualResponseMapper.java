package com.netdeal.Fullstack.mapper.response;

import com.netdeal.Fullstack.dto.response.IndividualResponse;
import com.netdeal.Fullstack.model.Individual;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualResponseMapper {

    IndividualResponse individualToIndividualResponse(Individual source);

}
