package com.dicyanevidal.chatprojeto.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {

    private String texto;

    private String remetente;

    private TipoMensagem tipoMensagem;
}
