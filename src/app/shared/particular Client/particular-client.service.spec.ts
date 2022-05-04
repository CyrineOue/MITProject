import { TestBed } from '@angular/core/testing';

import { ParticularClientService } from './particular-client.service';

describe('ParticularClientService', () => {
  let service: ParticularClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticularClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
