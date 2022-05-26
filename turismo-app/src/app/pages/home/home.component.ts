import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from 'src/app/components/dialog/dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;

  constructor(private formBuilder: FormBuilder, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      from: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(15),
        ],
      ],
      to: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(15),
        ],
      ],
      departure: ['', [Validators.required]],
      return: ['', [Validators.required]],
      trip: ['', [Validators.required]],
    });
  }

  onClickOneway(): void {
    this.myForm.controls['return'].disable();
  }

  onClickRound(): void {
    this.myForm.controls['return'].enable();
  }

  onSubmit(): void {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.radioBtnError = true;
      return;
    }
    if (
      this.myForm.get('trip')?.value === 'round' &&
      (this.myForm.get('return')?.value === '' ||
        this.myForm.get('return')?.value === null)
    ) {
      this.openDialog();
      return;
    }
    console.log('On Submit ->', this.myForm.value);
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      // console.log(`Dialog result: ${result}`);
    });
  }
}
