import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BehaviorSubject, tap } from 'rxjs';
import { ILocation } from 'src/app/interfaces/location.interface';
import { FlightService } from '../../services/flight.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.scss'],
})
export class HotelsComponent implements OnInit {
  myForm!: FormGroup;
  locationsDestinySource = new BehaviorSubject<ILocation[]>([]);
  locationsDestiny$ = this.locationsDestinySource.asObservable();
  constructor(
    private formBuilder: FormBuilder,
    private flightSvc: FlightService
  ) {}

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      destiny: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
        ],
      ],
    });
  }

  onTypingDestiny() {
    if (this.myForm.get('destiny')?.value.length >= 3) {
      this.flightSvc
        .searchCityAndAirport(this.myForm.get('destiny')?.value, true)
        .pipe(
          tap((res: any) => {
            // console.log(res.data);
            this.locationsDestinySource.next(res.data);
          })
        )
        .subscribe();
    }
  }

  onSubmit() {
    // ...TODO...
  }
}
