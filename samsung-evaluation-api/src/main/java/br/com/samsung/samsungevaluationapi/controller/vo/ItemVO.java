package br.com.samsung.samsungevaluationapi.controller.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO {	
	private BigDecimal valor;
	private BigDecimal cotacao;	
    private String codigoMoeda;	
	private String nome;	
    private String numero;		
    private String dataDocumento;	
}
