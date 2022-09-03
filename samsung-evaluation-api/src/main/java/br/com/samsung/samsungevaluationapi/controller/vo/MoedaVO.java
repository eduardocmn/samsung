package br.com.samsung.samsungevaluationapi.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoedaVO {	
	private Long moedaId;	
    private String moedaCode;	
    private String moedaNome;
    
    
}
