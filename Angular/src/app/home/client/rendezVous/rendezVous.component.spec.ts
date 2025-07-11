import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RendezVousComponent } from './rendezVous.component';

describe('RendezVousComponent', () => {
  let component: RendezVousComponent;
  let fixture: ComponentFixture<RendezVousComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(RendezVousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
