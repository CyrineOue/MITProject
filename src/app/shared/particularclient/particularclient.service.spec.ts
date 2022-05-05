import { TestBed } from '@angular/core/testing';

import { ParticularclientService } from './particularclient.service';

describe('ParticularclientService', () => {
  let service: ParticularclientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticularclientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
