import { TestBed } from '@angular/core/testing';

import { SinisterService } from './sinister.service';

describe('SinisterService', () => {
  let service: SinisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SinisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
