package br.com.dio.barbershopapi.service.query.impl;

import br.com.dio.barbershopapi.entity.ClientEntity;
import br.com.dio.barbershopapi.exception.EmailInUseException;
import br.com.dio.barbershopapi.exception.NotFoundException;
import br.com.dio.barbershopapi.exception.PhoneInUseException;
import br.com.dio.barbershopapi.repository.IClientRepository;
import br.com.dio.barbershopapi.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado cliente de Id " + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if(repository.existsByPhone(phone)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyPhone(Long id, String phone) {
        Optional<ClientEntity> optional = repository.findByPhone(phone);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(repository.existsByEmail(email)) {
            throw new EmailInUseException("O email " + email + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(Long id, String email) {
        Optional<ClientEntity> optional = repository.findByEmail(email);
        if(optional.isPresent() && !Objects.equals(optional.get().getEmail(), email)) {
            throw new EmailInUseException("O email " + email + " já está em uso");
        }
    }
}
