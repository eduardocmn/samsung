package br.com.samsung.samsungevaluationapi.service;

import java.time.LocalDate;
import java.util.List;

import br.com.samsung.samsungevaluationapi.controller.vo.ItemVO;
import br.com.samsung.samsungevaluationapi.model.Documento;
import br.com.samsung.samsungevaluationapi.model.Moeda;
import br.com.samsung.samsungevaluationapi.model.Quotacao;

public interface EvaluationService {
	
	public List<ItemVO> findItemByFilter(String documentNumber, String currencyCode, LocalDate initDate, LocalDate endDate);
	public List<Moeda> listAllMoedas();
    public Moeda getMoeda(String currencyCode);
    public List<Documento> findDocumentByFilter(String documentNumber, String currencyCode, LocalDate initDate, LocalDate endDate);
    public List<Quotacao> listAllQuotacao();    
    public List<Quotacao> buscarQuotacao(Moeda currency,LocalDate date);
}
