import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;
  roundSource = new BehaviorSubject<boolean>(true);
  round$ = this.roundSource.asObservable();

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      from: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      to: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      departure: ['', [Validators.required]],
      return: [''],
      trip: ['', [Validators.required]]
    });
  }

  onClickOneway(): void {
    this.roundSource.next(false);
    this.myForm.patchValue({return: ''});
  }

  onClickRound(): void {
    this.roundSource.next(true);
  }

  onSubmit(): void {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.radioBtnError = true;
      return;
    }
    if (this.myForm.get('trip')?.value === 'round' && this.myForm.get('return')?.value === '') {
      return;
    }
    console.log('On Submit ->', this.myForm.value);
    
  }

}
