package com.fiap.greentracefood.application.exceptions;

import com.fiap.greentracefood.application.enums.StatusPedido;

public class StatusPedidoException extends RuntimeException {

    public StatusPedidoException(StatusPedido statusPedido,boolean isPago) {
        super(criarMensagem(statusPedido,isPago));
    }

    private static String criarMensagem(StatusPedido statusPedido, boolean isPago) {
        if (!isPago && statusPedido.equals(StatusPedido.PREPARANDO)||
                !isPago && statusPedido.equals(StatusPedido.PRONTO)||
                !isPago && statusPedido.equals(StatusPedido.FINALIZADO)) {
            return "Por favor, verifique o status do pagamento antes de atualizar o status do pedido";
        } else {
            return "Atualização de status não permitido para " + statusPedido;
        }
    }
}
