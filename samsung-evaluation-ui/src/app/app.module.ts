import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatCardModule } from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
import { AppComponent } from './app.component';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormComponent } from './components/form.component';
import { CotacaoService } from './service/cotacao.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input'; 
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
@NgModule({
  declarations: [
    AppComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,AppRoutingModule,    
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatTableModule,MatCardModule,
    MatFormFieldModule,MatSelectModule,
    MatInputModule,MatDatepickerModule,
    MatNativeDateModule,FormsModule, BrowserModule,    
    ReactiveFormsModule,
    MatButtonModule,
    MatNativeDateModule,
    BrowserAnimationsModule,MatDatepickerModule    
  ],
 
  providers: [CotacaoService,
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
