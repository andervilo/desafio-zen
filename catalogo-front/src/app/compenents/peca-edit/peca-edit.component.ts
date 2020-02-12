import { Component, OnInit } from '@angular/core';
import { PecaService } from 'src/app/services/peca-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Peca } from 'src/app/interfaces/peca';

@Component({
  selector: 'app-peca-edit',
  templateUrl: './peca-edit.component.html',
  styleUrls: ['./peca-edit.component.css']
})
export class PecaEditComponent implements OnInit {
  peca: Peca = {} as Peca;

  id: number;

  constructor(
    private pecaService: PecaService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.id = null;

    this.route.paramMap.subscribe(p => {
      this.id = Number(p.get('id'));
    });
    console.log(this.id);

    this.pecaService.findById(this.id).subscribe(response => {
      console.log(this.id);
      console.log(response);
      this.peca = response;
    },
    error => {
      alert(' Erro: ' + error.error.statusCode +'\n' + error.error.errorMessage);
      console.log(error.error);
    });
  }

  updatePeca() {
    this.pecaService.edit(this.peca.id, this.peca).subscribe(response => {
      
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
