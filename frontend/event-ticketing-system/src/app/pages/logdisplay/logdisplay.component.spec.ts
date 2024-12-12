import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogdisplayComponent } from './logdisplay.component';

describe('LogdisplayComponent', () => {
  let component: LogdisplayComponent;
  let fixture: ComponentFixture<LogdisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LogdisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogdisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
