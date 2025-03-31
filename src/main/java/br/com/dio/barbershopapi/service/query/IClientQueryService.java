package br.com.dio.barbershopapi.service.query;

import br.com.dio.barbershopapi.entity.ClientEntity;

import java.util.List;

public interface IClientQueryService {

    ClientEntity findById(final Long id);
    List<ClientEntity> list();
    void verifyPhone(final String phone);
    void verifyPhone(final Long id, final String phone);
    void verifyEmail(final String email);
    void verifyEmail(final Long id, final String email);
}
