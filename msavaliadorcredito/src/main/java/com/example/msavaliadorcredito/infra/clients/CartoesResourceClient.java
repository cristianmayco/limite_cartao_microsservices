package com.example.msavaliadorcredito.infra.clients;

import com.example.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);
}
