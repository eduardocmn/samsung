package br.com.samsung.samsungevaluationapi.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVO {
	private String codigoMoeda;
	private LocalDate data;
    private Long identificacao;
    private BigDecimal valor;
    private String numero;	
    private String notaFiscal;	
 }
