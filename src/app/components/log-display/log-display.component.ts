import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-log-display',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './log-display.component.html',
  styleUrls: ['./log-display.component.css'],
})
export class LogDisplayComponent {
  @Input() logs: string[] = [];

  trackLog(index: number, log: string): string {
    return log;
  }
}
