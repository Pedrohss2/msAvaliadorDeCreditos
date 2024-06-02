package io.github.pedrohss2.msAvaliadorDeCreditos.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosEmissaoCartao;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue emissaoCartoes;

    public void solicitarCartao(DadosEmissaoCartao dadosEmissaoCartao) throws JsonProcessingException {
        var json = converteParaJson(dadosEmissaoCartao);
        rabbitTemplate.convertAndSend(emissaoCartoes.getName(), json);
    }

    private String converteParaJson(DadosEmissaoCartao dadosEmissaoCartao) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dadosEmissaoCartao);
    }

}
