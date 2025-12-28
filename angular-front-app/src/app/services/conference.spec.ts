import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import {ConferenceComponent} from '../components/conference/conference';
import {ConferenceService} from './conference';


describe('ConferenceComponent', () => {
  let component: ConferenceComponent;
  let fixture: ComponentFixture<ConferenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConferenceComponent, HttpClientTestingModule, FormsModule],
      providers: [ConferenceService]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load conferences on init', () => {
    expect(component.conferences).toBeDefined();
  });
});
