package com.dicyanevidal.chatprojeto.resource;

import com.dicyanevidal.chatprojeto.domain.Mensagem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatResource {

    @MessageMapping("/chat.enviarMensagem")
    @SendTo("/topic/public")
    public Mensagem enviarMensagem(@Payload Mensagem mensagem) {
        final String horario = new SimpleDateFormat("HH:mm").format(new Date());
        mensagem.setTexto(horario.concat(" ").concat(mensagem.getTexto()));
        return mensagem;
    }

    @MessageMapping("/chat.registrarUsuario")
    @SendTo("/topic/public")
    public Mensagem registrarUsuario(@Payload Mensagem mensagem, SimpMessageHeaderAccessor headerAcessor) {
        headerAcessor.getSessionAttributes().put("username", mensagem.getRemetente());
        return mensagem;
    }
}
