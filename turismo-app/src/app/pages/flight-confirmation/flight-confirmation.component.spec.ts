import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightConfirmationComponent } from './flight-confirmation.component';

describe('FlightConfirmationComponent', () => {
  let component: FlightConfirmationComponent;
  let fixture: ComponentFixture<FlightConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightConfirmationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
