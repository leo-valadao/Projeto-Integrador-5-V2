package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadosBrasileirosEnum {

    // São 27 Estados (Contando com o Distrito Federal)
    ACRE("Acre (AC)"),
    ALAGOAS("Alagoas (AL)"),
    AMAZONAS("Amazonas (AM)"),
    AMAPA("Amapá (AP)"),
    BAHIA("Bahia (BA)"),
    CEARA("Ceará (CE)"),
    DISTRITOFEDERAL("Distrito Federal (DF)"),
    ESPIRITOSANTO("Espírito Santo (ES)"),
    GOIAS("Goiás (GO)"),
    MARANHAO("Maranhão (MA)"),
    MINASGERAIS("Minas Gerais (MG)"),
    MATOGROSSOSUL("Mato Grosso do Sul (MS)"),
    MATOGROSSO("Mato Grosso (MT)"),
    PARA("Pará (PA)"),
    PARAIBA("Paraíba (PB)"),
    PERNAMBUCO("Pernambuco (PE)"),
    PIAUI("Piauí (PI)"),
    PARANA("Paraná (PR)"),
    RIODEJANEIRO("Rio de Janeiro (RJ)"),
    RIOGRANDENORTE("Rio Grande do Norte (RN)"),
    RONDONIA("Rondônia (RO)"),
    RORAIMA("Roraima (RR)"),
    RIOGRANDESUL("Rio Grande do Sul (RS)"),
    SANTACATARINA("Santa Catarina (SC)"),
    SERGIPE("Sergipe (SE)"),
    SAOPAULO("São Paulo (SP)"),
    TOCANTINS("Tocantins (TO)");

    private String nomeCompleto;

}