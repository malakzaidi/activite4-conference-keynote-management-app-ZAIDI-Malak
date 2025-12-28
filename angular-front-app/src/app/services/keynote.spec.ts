import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import {KeynoteComponent} from '../components/keynote/keynote';
import {KeynoteService} from './keynote';
import {ConferenceService} from './conference';


describe('KeynoteComponent', () => {
  let component: KeynoteComponent;
  let fixture: ComponentFixture<KeynoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KeynoteComponent, HttpClientTestingModule, FormsModule],
      providers: [KeynoteService, ConferenceService]
    })
      .compileComponents();

    fixture = TestBed.createComponent(KeynoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load keynotes on init', () => {
    expect(component.keynotes).toBeDefined();
  });
});
