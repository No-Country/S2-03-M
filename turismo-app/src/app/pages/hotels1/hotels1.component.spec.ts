import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Hotels1Component } from './hotels1.component';

describe('Hotels1Component', () => {
  let component: Hotels1Component;
  let fixture: ComponentFixture<Hotels1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Hotels1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Hotels1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
