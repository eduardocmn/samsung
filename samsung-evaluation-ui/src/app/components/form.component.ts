import { Component, OnInit } from '@angular/core';
import { Item } from '../model/Item';
import { Moeda } from '../model/Moeda';
import { Filtro } from '../model/Filtro';
import { CotacaoService } from '../service/cotacao.service';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-document',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  cabecalhoTabela = ['Documento','Data Documento','Moeda','Valor'];
  listaDataTable:Item[];
  listaMoedasComboBox:Moeda[];
  formFiltro:Filtro;
  dataInicio = new FormControl(); 
  dataFim = new FormControl();

  constructor(private cotacaoService:CotacaoService) {
    this.formFiltro = new Filtro('','','','');
    this.listaDataTable=[];
    this.listaMoedasComboBox=[];    
  }

  buscarMoedas() {   
    this.cotacaoService.buscarMoedas().subscribe(data => 
        {this.listaMoedasComboBox = data}
      );  
  }
  converter(formControl:FormControl) {
    if(formControl == null || formControl.value == null){
      return '';
    }
    var dataDia = formControl.value.toLocaleString().split(" ")[0];
    var dataPadraoArray :String[] = dataDia.split("/");
    var dataPadrao= dataPadraoArray[2]+'-'+dataPadraoArray[1]+'-'+dataPadraoArray[0];
    return dataPadrao;
  }
  pesquisar() {
  
    this.converter(this.dataInicio);
    this.formFiltro.dataFim = this.converter(this.dataFim);
    this.formFiltro.dataInicio = this.converter(this.dataInicio);
    this.cotacaoService.buscarCotacao(this.formFiltro).subscribe(data => {      
      this.listaDataTable = data;       
    });  
  }
  ngOnInit(): void {
    this.buscarMoedas();
  }
  
  limparCampos(){
    this.formFiltro=new Filtro('','','','');
    this.dataInicio = new FormControl();     
    this.listaDataTable=[];
    this.dataFim = new FormControl();
  }

}
