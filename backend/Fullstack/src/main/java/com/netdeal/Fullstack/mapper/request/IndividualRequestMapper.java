package com.netdeal.Fullstack.mapper.request;

import com.netdeal.Fullstack.dto.request.IndividualRequest;
import com.netdeal.Fullstack.model.Individual;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualRequestMapper {

    Individual individualToIndividualRequest(IndividualRequest source);

    Individual idToIndividual(Long id);

}
