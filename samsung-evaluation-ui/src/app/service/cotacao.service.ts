import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Item } from '../model/Item';
import { Moeda } from '../model/Moeda';
import { Filtro } from '../model/Filtro';


@Injectable({
  providedIn: 'root'
})
export class CotacaoService {

  private readonly rest = environment.api+'/buscarCotacao/cellolatam/';

  constructor(private httpClient: HttpClient) { }

  buscarCotacao(cotacaoParams:Filtro) {
    const params = new HttpParams()
    .set('numero', cotacaoParams.numero.toString()).set('dataInicio', cotacaoParams.dataFim.toString())
    .set('dataFim', cotacaoParams.dataInicio.toString()).set('codigoMoeda', cotacaoParams.codigoMoeda.toString());
    return this.httpClient.get<Item[]>(this.rest+'documentos',{params});
  }
  buscarMoedas() {
    return this.httpClient.get<Moeda[]>(this.rest+'moedas');
 }
}
