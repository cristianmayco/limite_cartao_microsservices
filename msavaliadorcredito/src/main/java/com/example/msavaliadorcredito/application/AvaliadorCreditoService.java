package com.example.msavaliadorcredito.application;

import com.example.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.example.msavaliadorcredito.application.ex.ErroComunicaoMicroservicesException;
import com.example.msavaliadorcredito.domain.model.CartaoCliente;
import com.example.msavaliadorcredito.domain.model.DadosCliente;
import com.example.msavaliadorcredito.domain.model.SituacaoCliente;
import com.example.msavaliadorcredito.infra.ClienteResourceClient;
import com.example.msavaliadorcredito.infra.clients.CartoesResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status =  e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicaoMicroservicesException(e.getMessage(), status);
        }
    }
}
