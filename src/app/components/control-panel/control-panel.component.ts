import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {
  @Output() startEvent = new EventEmitter<void>();
  @Output() stopEvent = new EventEmitter<void>();

  onStart() {
    this.startEvent.emit();
  }

  onStop() {
    this.stopEvent.emit();
  }
}