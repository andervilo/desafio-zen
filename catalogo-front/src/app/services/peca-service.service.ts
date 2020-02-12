import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Peca } from '../interfaces/peca';

@Injectable({
  providedIn: 'root'
})
export class PecaService {

  constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/api/v1/pecas';

  findAll(){
    return this.http.get<Peca[]>(this.url);
  }

  findById(id: number){
    return this.http.get<Peca>(this.url + '/' + id);
  }

  save(peca: Peca){
    console.log(peca);
    return this.http.post(this.url, peca);
  }

  delete(id: number) {
    return this.http.delete(this.url + '/' + id);
  }

  edit(id: number, peca: Peca) {
    return this.http.put(this.url + '/' + id, peca);
  }
}
