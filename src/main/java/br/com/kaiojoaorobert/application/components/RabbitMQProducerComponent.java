package br.com.kaiojoaorobert.application.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kaiojoaorobert.domain.dtos.CotacaoMessage;

@Component
public class RabbitMQProducerComponent {

	@Autowired ObjectMapper mapper;
	@Autowired RabbitTemplate rabbitTemplate;
	@Autowired Queue queue;

	public void send(CotacaoMessage message) {
		
		try {
			String json = mapper.writeValueAsString(message);
			rabbitTemplate.convertAndSend("cotacoes_encerradas",json);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendConvite(CotacaoMessage message) {
		try {
			
			String json = mapper.writeValueAsString(message);
			rabbitTemplate.convertAndSend("convites_fornecedores",json);
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
