package com.fiap.greentracefood.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSender {

    @Value("${aws.sqs.queue.payment}")
    private String PROCESSED_QUEUE_NAME;

    private static final Logger logger = LoggerFactory.getLogger(PaymentSender.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SqsTemplate sqsTemplate;

    public void sendProcessedPaymentMessage(Pedido pedido) {
        try {
            PagamentoOrder pagamentoOrder = new PagamentoOrder(pedido.getCodigo(), pedido.getValorTotal());
            String jsonMessage = objectMapper.writeValueAsString(pagamentoOrder);
            sqsTemplate.send(PROCESSED_QUEUE_NAME, jsonMessage);
            logger.info("Message sent to {} successfully", PROCESSED_QUEUE_NAME);
            logger.info("Order Send Payment : {}", jsonMessage);

        } catch (Exception e) {
            logger.error("Error sending message to {}", PROCESSED_QUEUE_NAME, e);
        }
    }
}
