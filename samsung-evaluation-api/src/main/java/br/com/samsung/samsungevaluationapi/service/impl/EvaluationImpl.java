package br.com.samsung.samsungevaluationapi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.samsung.samsungevaluationapi.client.ClientRest;
import br.com.samsung.samsungevaluationapi.controller.vo.ItemVO;
import br.com.samsung.samsungevaluationapi.model.Documento;
import br.com.samsung.samsungevaluationapi.model.Moeda;
import br.com.samsung.samsungevaluationapi.model.Quotacao;
import br.com.samsung.samsungevaluationapi.service.EvaluationService;

@Service
public class EvaluationImpl implements EvaluationService{
	DateTimeFormatter dateFromat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Autowired
	ClientRest feignClientService;
	
	@Override
	public List<ItemVO> findItemByFilter(String numero, String codigo, LocalDate dataInicio,
			LocalDate dataFim) {
		List<ItemVO> listItem = new ArrayList<>();
		
		List<Documento> listaDocs = this.findDocumentByFilter(numero, codigo,	dataInicio, dataFim);
		
		for(Documento documentos :listaDocs) {
			Moeda moeda = this.getMoeda(documentos.getCurrencyCode());
			List<Quotacao> listQuotation = this.buscarQuotacao(moeda, documentos.getDocumentDate());
			
			listItem.addAll(converterItem(listQuotation, documentos));
			
		}
		return listItem;
	}
	
	@Override
	public Moeda getMoeda(String moeda) {
		return listAllMoedas().
				stream().
				filter(p -> p.getCurrencyCode().equals(moeda))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Documento> findDocumentByFilter(String numero, String codigo, LocalDate dataInicio,
			LocalDate dataFim) {
		List<Documento> listaDoc = feignClientService.buscarDocumentos();
		List<Documento> list = new ArrayList<Documento>();

		if (!ObjectUtils.isEmpty(listaDoc)) {
			for (Documento document : listaDoc) {
				if (!ObjectUtils.isEmpty(numero) && !document.getDocumentNumber().equals(numero)) {
					continue;
				}

				if (!ObjectUtils.isEmpty(codigo)!=document.getCurrencyCode().equals(codigo)) {
					continue;
				}
				if (!ObjectUtils.isEmpty(dataInicio)&& dataInicio.isAfter(document.getDocumentDate())) {
					continue;
				}

				if (!ObjectUtils.isEmpty(dataFim)&& dataFim.isBefore(document.getDocumentDate())) {
					continue;
				}
				list.add(document);
			}
		}
		return list;
	}

	@Override
	public List<Quotacao> listAllQuotacao() {
		return feignClientService.buscarQuotacao();
	}

	@Override
	public List<Quotacao> buscarQuotacao(Moeda currency, LocalDate data) {
		List<Quotacao> listaValores = new ArrayList<>(); 
    	List<Moeda> listaMoedas = this.listAllMoedas();    	
    	List<Quotacao> lista = listAllQuotacao().stream().filter(q -> q.getFromCurrencyCode().equals(currency.getCurrencyCode())).collect(Collectors.toList());
    	
    	listaMoedas.stream().forEach(moeda ->{
    		if(!moeda.getCurrencyDesc().endsWith(currency.getCurrencyDesc())) {
    			List<Quotacao> listQuotationTo = lista.stream().filter(q -> q.getToCurrencyCode().equals(moeda.getCurrencyCode())).collect(Collectors.toList());
        		
    			listaValores.add(getQuotacao(listQuotationTo, data));
    		} 
    	});    	
    	return listaValores;	
	}
	
	private  Quotacao getQuotacao(List<Quotacao> lista,LocalDate data) {
    	Quotacao quotacao = null;
    	Long menor = null;
    	for(Quotacao item : lista) {
    		long dias = ChronoUnit.DAYS.between(item.getDataHoraCotacao(), data);
    		if(dias == 0) {
    			quotacao = item;
    			break;
    		}else {
    			if(menor == null || menor > dias) {
    				menor = dias;
    				quotacao = item;
    				continue;
    			}
    		}
    	}    	
    	return quotacao;
    }
	
	public List<ItemVO> converterItem(List<Quotacao> listaQuotacao,Documento documento) {
		HashMap<String, Moeda> mapCurrency = buscarMoedaMap();		
		List<ItemVO> listConversionList =  new ArrayList<>();
		
		ItemVO conversionItemDoc = new ItemVO();
		conversionItemDoc.setCodigoMoeda(documento.getCurrencyCode());

		conversionItemDoc.setValor(documento.getDocumentValue());
		conversionItemDoc.setCotacao(BigDecimal.ONE);
		conversionItemDoc.setNumero(documento.getDocumentNumber());
		conversionItemDoc.setDataDocumento(dateFromat.format(documento.getDocumentDate()));
		conversionItemDoc.setNome(mapCurrency.get(documento.getCurrencyCode()).getCurrencyDesc());
		listConversionList.add(conversionItemDoc);
		listaQuotacao.stream().forEach(q -> {
			ItemVO item = new ItemVO();
			item.setCodigoMoeda(q.getToCurrencyCode());
			item.setValor(documento.getDocumentValue().multiply(new BigDecimal(q.getCotacao())).setScale(2, RoundingMode.HALF_EVEN));
			item.setCotacao(new BigDecimal(q.getCotacao()));
			item.setNumero(documento.getDocumentNumber());
			item.setDataDocumento(dateFromat.format(documento.getDocumentDate()));
			item.setNome(mapCurrency.get(q.getToCurrencyCode()).getCurrencyDesc());
			listConversionList.add(item);
		});
		
		return listConversionList;
	}
	
	private HashMap<String, Moeda> buscarMoedaMap() {
		HashMap<String, Moeda> map = new HashMap<>();
		this.listAllMoedas().stream().forEach(moeda ->{
			map.put(moeda.getCurrencyCode(), moeda);
		});;
		return map;
	}
	@Override
	public List<Moeda> listAllMoedas() {
		return feignClientService.buscarMoedas();
	}
}
