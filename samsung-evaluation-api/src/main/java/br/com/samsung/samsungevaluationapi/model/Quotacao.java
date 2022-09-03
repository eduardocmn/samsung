package br.com.samsung.samsungevaluationapi.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Quotacao {

    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String cotacao;
    private LocalDate dataHoraCotacao;
 
}
