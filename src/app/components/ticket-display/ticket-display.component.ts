// import { Component, Input } from '@angular/core';

// @Component({
//   selector: 'app-ticket-display',
//   imports: [],
//   standalone: true,
//   templateUrl: './ticket-display.component.html',
//   styleUrl: './ticket-display.component.css'
// })
// export class TicketDisplayComponent {
//   @Input() availableTickets: number = 0;

//   getAvailabilityMessage(): string {
//     return this.availableTickets > 0 ? 'Tickets Available' : 'Sold Out';
//   }
// }



import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-ticket-display',
  standalone: true,
  templateUrl: './ticket-display.component.html',
  styleUrls: ['./ticket-display.component.css']
})
export class TicketDisplayComponent {
  @Input() availableTickets: number = 0;

  getAvailabilityMessage(): string {
    return this.availableTickets > 0 ? 'Tickets Available' : 'Sold Out';
  }
}

