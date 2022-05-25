import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { BehaviorSubject } from 'rxjs';
import { DialogComponent } from 'src/app/components/dialog/dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;
  roundSource = new BehaviorSubject<boolean>(true);
  round$ = this.roundSource.asObservable();

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
      return: [''],
      trip: ['', [Validators.required]],
    });
  }

  onClickOneway(): void {
    this.roundSource.next(false);
    this.myForm.patchValue({ return: '' });
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
