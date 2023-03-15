import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddGarageComponent } from './components/add-garage/add-garage.component';
import { AddVoitureComponent } from './components/add-voiture/add-voiture.component';
import { GarageDetailsComponent } from './components/garage-details/garage-details.component';
import { GaragesListComponent } from './components/garages-list/garages-list.component';
import { VoitureDetailsComponent } from './components/voiture-details/voiture-details.component';
import { VoituresListComponent } from './components/voitures-list/voitures-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'garages', pathMatch: 'full' },
  { path: 'garages', component: GaragesListComponent },
  { path: 'garages/add', component: AddGarageComponent },
  { path: 'garages/:id', component: GarageDetailsComponent },
  { path: 'voitures', component: VoituresListComponent },
  { path: 'voitures/add', component: AddVoitureComponent },
  { path: 'voitures/:id', component: VoitureDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
