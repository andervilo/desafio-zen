import { Component, OnInit } from '@angular/core';
import { Peca } from 'src/app/interfaces/peca';
import { PecaService } from 'src/app/services/peca-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pecas-list',
  templateUrl: './pecas-list.component.html',
  styleUrls: ['./pecas-list.component.css']
})
export class PecasListComponent implements OnInit {
  pecas: Peca[] = [];

  constructor(
    private pecaService: PecaService,
    private router: Router) { }

  ngOnInit() {
    this.findAllPecas();
  }

  findAllPecas(){
    this.pecaService.findAll().subscribe(
      response => {
        this.pecas = response;
      }
    );
  }

  removePeca(id: number){
    this.pecaService.delete(id).subscribe(response => {
      console.log(response);
      if(response['statusCode'] === 200){
        this.findAllPecas();
      }
    });
  }

  editarPeca(id: number){
    this.pecaService.delete(id).subscribe(response => {
      console.log(response);
      if(response['statusCode'] === 200){
        this.findAllPecas();
      }
    });
  }

}
