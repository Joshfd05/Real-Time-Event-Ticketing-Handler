// import { Routes } from '@angular/router';

// export const routes: Routes = [
//     {
//         path: '', // Default route
//         pathMatch: 'full',
//         loadComponent: () => {
//             return import('./components/login/login.component').then((m) => m.LoginComponent);
//         },    
//     },
//     {
//         path: 'home', // Explicit path for home
//         loadComponent: () => {
//             return import('./home/home.component').then((m) => m.HomeComponent);
//         },
//     },
// ];







import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '', // Default route
    pathMatch: 'full',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'home', // Explicit path for home
    loadComponent: () => import('./home/home.component').then(m => m.HomeComponent),
  },
];
