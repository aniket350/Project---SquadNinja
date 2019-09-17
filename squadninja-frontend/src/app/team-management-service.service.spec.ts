import { TestBed } from '@angular/core/testing';

import { TeamManagementServiceService } from './team-management-service.service';

describe('TeamManagementServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeamManagementServiceService = TestBed.get(TeamManagementServiceService);
    expect(service).toBeTruthy();
  });
});

