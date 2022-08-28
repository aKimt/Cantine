import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guards/auth.guard';
import { AccueilComponent } from './components/accueil/accueil.component';
import { ConfirmOrderComponent } from './components/confirm-order/confirm-order.component';
import { MenuComponent } from './components/menu/menu.component';

const routes: Routes = [
    { path: "", children: [
        { path: "", redirectTo: "accueil", pathMatch: "full"},
        { path: "accueil", component: AccueilComponent},
        { path: "menu", component: MenuComponent },
        { path: "confirm-order", component: ConfirmOrderComponent, canActivate: [AuthGuard] },
    ] }
]


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule { }
