import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.myForm = this.initForm();
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

  onSubmit(): void {
    if (this.myForm.invalid) {
      return;
    }
    console.log('On Submit ->', this.myForm.value);
    
  }

}
