package br.com.dio.barbershopapi.service.impl;

import br.com.dio.barbershopapi.entity.ClientEntity;
import br.com.dio.barbershopapi.repository.IClientRepository;
import br.com.dio.barbershopapi.service.IClientService;
import br.com.dio.barbershopapi.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository repository;
    private final IClientQueryService queryService;


    @Override
    public ClientEntity save(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        ClientEntity stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setPhone(entity.getPhone());
        stored.setEmail(entity.getEmail());

        return repository.save(stored);
    }

    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
