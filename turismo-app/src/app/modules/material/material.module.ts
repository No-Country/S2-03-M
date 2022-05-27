import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMomentDateModule } from '@angular/material-moment-adapter';

@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatButtonModule,
    MatDialogModule,
    MatMomentDateModule,
  ],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'es-AR' }],
})
export class MaterialModule {}
