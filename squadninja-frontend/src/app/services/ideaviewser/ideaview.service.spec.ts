import { TestBed } from '@angular/core/testing';
import { IdeaviewService } from './ideaview.service';

describe('IdeaviewService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IdeaviewService = TestBed.get(IdeaviewService);
    expect(service).toBeTruthy();
  });
});
