package io.github.samleticias.rest.controller;

import io.github.samleticias.domain.entity.Cliente;
import io.github.samleticias.domain.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    // transforma objeto em tipo json, espera q retorne uma string
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok( cliente.get() );
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/api/clientes")
    @ResponseBody
    // responsebody: retornar e requestbody: receber
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);

    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if ( cliente.isPresent() ) {
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
