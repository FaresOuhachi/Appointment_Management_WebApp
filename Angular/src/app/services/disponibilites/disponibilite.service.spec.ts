import { TestBed } from '@angular/core/testing';

import { DisponibilitesService } from './disponibilite.service';

describe('DisponibiliteService', () => {
  let service: DisponibilitesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisponibilitesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
