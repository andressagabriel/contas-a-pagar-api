package br.uece.spring.api.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.spring.api.application.model.*;
import br.uece.spring.api.domain.model.exception.*;
import br.uece.spring.api.domain.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    //private ModelMapper modelMapper;
    
    // Método para listar contas
    public List<RetornoContaDto> listarContas() {
        return RetornoContaDto.from(contaRepository.findAll());
    }

    // Método para buscar conta
    public RetornoContaDto buscarConta(int codigo) {
        var conta = contaRepository.findByCodigo(codigo);
        if (conta == null) {
            throw new ContaNaoExistenteException();
        }

        return RetornoContaDto.from(conta, false);
    }

    // Método para cadastrar conta
    public RetornoContaDto cadastrarConta(NovaContaDto dto) {
        if (contaRepository.existsByNome(dto.getNome())) {
            throw new ContaJaExistenteException(dto.getNome());
        }

        var conta = contaRepository.save(dto.toEntity());
        return RetornoContaDto.from(conta, true);
    }

    // Método para atualizar conta
    public RetornoContaDto atualizarConta(ContaDto dto) {
        if (!contaRepository.existsByCodigo(dto.getCodigo())) {
            throw new ContaNaoExistenteException();
        }

        var conta = contaRepository.save(dto.toEntity());
        return RetornoContaDto.from(conta, true);
    }

    // Método para excluir conta
    public void excluirConta(int codigo) {
        var conta = contaRepository.findByCodigo(codigo);
        if (conta == null) {
            throw new ContaNaoExistenteException();
        }

        contaRepository.delete(conta);
    }

    // Método para pagar conta
    public RetornoContaDto pagarConta(int codigo) {
        var conta = contaRepository.findByCodigo(codigo);
        if (conta == null) {
            throw new ContaNaoExistenteException();
        }
        if (conta.isPaga()) {
            throw new ContaJaPagaException(conta.getNome());
        }

        var dto = RetornoContaDto.from(conta, false);
        dto.setPaga(true);
        
        contaRepository.save(dto.toEntity());
        return RetornoContaDto.from(conta, true);
    }
}
