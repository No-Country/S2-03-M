import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, tap } from 'rxjs';

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
    this.handleChanges();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      from: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      to: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      departure: ['', [Validators.required]],
      return: ['', [Validators.required]],
      trip: ['', [Validators.required]]
    });
  }

  handleChanges(): void {
    this.myForm.get('trip')?.valueChanges.pipe(
      tap((res: any) => {
        // console.log(res);
        if (res === 'one-way') {
          this.roundSource.next(false);
        } else {
          this.roundSource.next(true);
        }
      }),
    ).subscribe();
  }

  onSubmit(): void {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.radioBtnError = true;
      return;
    }
    console.log('On Submit ->', this.myForm.value);
    
  }

}
