import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { MatFormFieldModule } from "@angular/material/form-field";
import {MatInputModule} from '@angular/material/input';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule, MAT_DATE_LOCALE } from "@angular/material/core";

@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [MatDatepickerModule, MatNativeDateModule, MatFormFieldModule, MatInputModule],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'es-AR'},
  ],
})
export class MaterialModule {}
