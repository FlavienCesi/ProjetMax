import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AddGarageComponent } from './components/add-garage/add-garage.component';
import { GarageDetailsComponent } from './components/garage-details/garage-details.component';
import { GaragesListComponent } from './components/garages-list/garages-list.component';
import { AddVoitureComponent } from './components/add-voiture/add-voiture.component';
import { VoitureDetailsComponent } from './components/voiture-details/voiture-details.component';
import { VoituresListComponent } from './components/voitures-list/voitures-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddGarageComponent,
    GarageDetailsComponent,
    GaragesListComponent,
    AddVoitureComponent,
    VoitureDetailsComponent,
    VoituresListComponent,
  ],
  imports: [BrowserModule, FormsModule, HttpClientModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
