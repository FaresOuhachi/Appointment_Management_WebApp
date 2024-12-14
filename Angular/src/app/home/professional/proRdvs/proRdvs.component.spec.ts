import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProRdvsComponent } from './proRdvs.component';

describe('ProRdvsComponent', () => {
  let component: ProRdvsComponent;
  let fixture: ComponentFixture<ProRdvsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProRdvsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProRdvsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
