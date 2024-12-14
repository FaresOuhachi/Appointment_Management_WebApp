import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MesrendezvousComponent } from './mesRdvs.component';

describe('MesrendezvousComponent', () => {
  let component: MesrendezvousComponent;
  let fixture: ComponentFixture<MesrendezvousComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesrendezvousComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MesrendezvousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
