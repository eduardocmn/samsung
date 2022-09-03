package br.com.samsung.samsungevaluationapi.model;

import java.util.List;
import java.util.stream.Collectors;

import br.com.samsung.samsungevaluationapi.controller.vo.MoedaVO;

public class MoedaMap {
	public static MoedaVO converter(Moeda moeda) {
		MoedaVO moedaVO = new MoedaVO();
		moedaVO.setMoedaCode(moeda.getCurrencyCode());
		moedaVO.setMoedaNome(moeda.getCurrencyDesc());
		moedaVO.setMoedaId(moeda.getCurrencyId());
		return moedaVO;
	}

	public static List<MoedaVO> converterAll(List<Moeda> lista) {
		return lista.stream().map(MoedaMap::converter).collect(Collectors.toList());
	}
}
