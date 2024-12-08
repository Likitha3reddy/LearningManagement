import { TestBed } from '@angular/core/testing';

import { EnrollmentserviceService } from './enrollmentservice.service';

describe('EnrollmentserviceService', () => {
  let service: EnrollmentserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnrollmentserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
