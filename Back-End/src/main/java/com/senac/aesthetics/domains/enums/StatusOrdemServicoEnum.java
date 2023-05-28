package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrdemServicoEnum {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    EM_EXECUCAO("Em Execução"),
    CONCLUIDO("Concluído");

    private String status;

}
