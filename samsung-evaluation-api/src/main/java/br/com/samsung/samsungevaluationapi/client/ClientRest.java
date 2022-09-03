package br.com.samsung.samsungevaluationapi.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import br.com.samsung.samsungevaluationapi.model.Moeda;
import br.com.samsung.samsungevaluationapi.model.Documento;
import br.com.samsung.samsungevaluationapi.model.Quotacao;

@org.springframework.cloud.openfeign.FeignClient(name = "evaluation", url = "${quotacao.url.api}")
public interface ClientRest {
    @GetMapping("/currency")
    List<Moeda> buscarMoedas();
    @GetMapping("/docs")
    List<Documento> buscarDocumentos();    
    @GetMapping("/quotation")
    List<Quotacao> buscarQuotacao();
}
