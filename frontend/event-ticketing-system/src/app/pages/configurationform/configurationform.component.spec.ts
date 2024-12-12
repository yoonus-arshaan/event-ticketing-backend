import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigurationformComponent } from './configurationform.component';

describe('ConfigurationformComponent', () => {
  let component: ConfigurationformComponent;
  let fixture: ComponentFixture<ConfigurationformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfigurationformComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigurationformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
