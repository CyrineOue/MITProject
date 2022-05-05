import { TestBed } from '@angular/core/testing';

import { CompanyclientService } from './companyclient.service';

describe('CompanyclientService', () => {
  let service: CompanyclientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanyclientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
