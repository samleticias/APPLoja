package io.github.samleticias.rest.controller;

import io.github.samleticias.exception.PedidoNaoEncontradoException;
import io.github.samleticias.exception.RegraNegocioException;
import io.github.samleticias.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) // tratar exceptions/erros
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);

    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex ){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        // erro.getDefaultMessage: retorna mensagem escrita na validação: "Campo nome é obrigatório."
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage() )
                .collect(Collectors.toList());
        return new ApiErrors(errors);
        // recebe array contendo todos os erros, depois de inserir construtor passando lista de erros em ApiErros.java
        // agora ApiErrors.java tem construtor de lista de erros e cosntrutor de mensagem
    }
}
