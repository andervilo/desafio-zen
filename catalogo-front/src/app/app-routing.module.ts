import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PecasListComponent } from './compenents/pecas-list/pecas-list.component';
import { PecaAddComponent } from './compenents/peca-add/peca-add.component';


const routes: Routes = [
  {
    path: "",
    component: PecasListComponent
  },
  {
    path: "add-peca",
    component: PecaAddComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
