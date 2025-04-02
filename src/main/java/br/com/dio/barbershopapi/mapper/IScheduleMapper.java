package br.com.dio.barbershopapi.mapper;

import br.com.dio.barbershopapi.controller.request.SaveScheduleRequest;
import br.com.dio.barbershopapi.controller.response.SaveScheduleResponse;
import br.com.dio.barbershopapi.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);
}
