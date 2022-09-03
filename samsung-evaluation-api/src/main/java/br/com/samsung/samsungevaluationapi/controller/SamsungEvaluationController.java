package br.com.samsung.samsungevaluationapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.samsung.samsungevaluationapi.controller.vo.ItemVO;
import br.com.samsung.samsungevaluationapi.controller.vo.MoedaVO;
import br.com.samsung.samsungevaluationapi.model.Moeda;
import br.com.samsung.samsungevaluationapi.model.MoedaMap;
import br.com.samsung.samsungevaluationapi.service.EvaluationService;

@RestController
@RequestMapping(value = "cellolatam",produces = MediaType.APPLICATION_JSON_VALUE)
public class SamsungEvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	@CrossOrigin
	@GetMapping(value = "/moedas")
    public ResponseEntity<List<MoedaVO>> listCurrency() {
	 List<Moeda> listCurrency = this.evaluationService.listAllMoedas();
	 List<MoedaVO> listCurrencyDTO = MoedaMap.converterAll(listCurrency);
     return new ResponseEntity<>(listCurrencyDTO, HttpStatus.OK);

    }
	@CrossOrigin
    @GetMapping(value = "/documentos") 
    public ResponseEntity<List<ItemVO>> getConversion(
    		     @RequestParam(value="numero",required = false) String numero,
				 @RequestParam(value="codigoMoeda",required = false) String codigoMoeda,
				 @RequestParam(value="dataInicio",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataInicio,
				 @RequestParam(value="dataFim",required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFim) {

        return new ResponseEntity<>(evaluationService.findItemByFilter(numero, codigoMoeda,dataInicio, dataFim), HttpStatus.OK);
	}	 
}
