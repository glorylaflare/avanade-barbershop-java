package br.com.dio.barbershopapi.controller;

import br.com.dio.barbershopapi.controller.request.SaveScheduleRequest;
import br.com.dio.barbershopapi.controller.response.SaveScheduleResponse;
import br.com.dio.barbershopapi.entity.ScheduleEntity;
import br.com.dio.barbershopapi.mapper.IScheduleMapper;
import br.com.dio.barbershopapi.service.IScheduleService;
import br.com.dio.barbershopapi.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse save(@Valid @RequestBody final SaveScheduleRequest request) {
        ScheduleEntity entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id) {
        service.delete(id);
    }
}
