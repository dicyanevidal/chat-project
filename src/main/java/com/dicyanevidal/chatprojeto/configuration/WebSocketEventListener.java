package com.dicyanevidal.chatprojeto.configuration;

import com.dicyanevidal.chatprojeto.domain.Mensagem;
import com.dicyanevidal.chatprojeto.domain.TipoMensagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebDocketDisconnectListiner(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            log.info("Usuario desconectado: {}", username);
            var mensagem = Mensagem.builder()
                    .tipoMensagem(TipoMensagem.LEAVE)
                    .remetente(username)
                    .build();

            messageSendingOperations.convertAndSend("/topic/pubic", mensagem);
        }
    }
}
