import { Component, OnInit } from '@angular/core';
import { Peca } from 'src/app/interfaces/peca';
import { PecaService } from 'src/app/services/peca-service.service';
import {Router} from "@angular/router"

@Component({
  selector: 'app-peca-add',
  templateUrl: './peca-add.component.html',
  styleUrls: ['./peca-add.component.css']
})

export class PecaAddComponent implements OnInit {
  peca: Peca = {} as Peca;
  constructor(
    private pecaService: PecaService,
    private router: Router ) { }

  ngOnInit() {
    
  }

  addPeca(){
    console.log(this.peca);
    this.pecaService.save(this.peca).subscribe(response => {
      console.log(response);

      if(response['statusCode'] === 200){
        alert(response['message']);
        this.router.navigate(['/']);
      }
    },
    error => {
      console.log(error.error);
      alert(' Erro: ' + error.error.statusCode +'\n' + error.error.errorMessage);
    });
  }

}
