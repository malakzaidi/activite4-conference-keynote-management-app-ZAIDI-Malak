import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Keynote } from './keynote';

describe('Keynote', () => {
  let component: Keynote;
  let fixture: ComponentFixture<Keynote>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Keynote]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Keynote);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
