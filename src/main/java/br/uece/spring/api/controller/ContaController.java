package br.uece.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.uece.spring.api.application.model.*;
import br.uece.spring.api.application.service.ContaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RetornoContaDto>> listarContas() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoContaDto> buscarConta(@PathVariable int codigo) {
        return ResponseEntity.ok(contaService.buscarConta(codigo));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoContaDto> cadastrarConta(@Valid @RequestBody NovaContaDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(contaService.cadastrarConta(dto));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoContaDto> atualizarConta(@RequestBody ContaDto dto) {
        return ResponseEntity.ok(contaService.atualizarConta(dto));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/pagar/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoContaDto> pagarConta(@PathVariable int codigo) {
        return ResponseEntity.ok(contaService.pagarConta(codigo));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluirConta(@PathVariable int codigo) {
        contaService.excluirConta(codigo);
        return ResponseEntity.ok("Conta excluída com sucesso.");
    }
}