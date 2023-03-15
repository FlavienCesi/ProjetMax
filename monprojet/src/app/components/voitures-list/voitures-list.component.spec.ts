import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoituresListComponent } from './voitures-list.component';

describe('VoituresListComponent', () => {
  let component: VoituresListComponent;
  let fixture: ComponentFixture<VoituresListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VoituresListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoituresListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
