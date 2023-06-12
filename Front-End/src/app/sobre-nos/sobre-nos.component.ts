import { Component } from '@angular/core';

@Component({
	selector: 'app-sobre-nos',
	templateUrl: './sobre-nos.component.html',
	styles: [],
})
export class SobreNosComponent {
	redirecionar(link: string) {
		window.location.href = link;
	}
}
