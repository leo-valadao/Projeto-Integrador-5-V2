import { Component } from '@angular/core';

@Component({
	selector: 'app-sobre-nos',
	templateUrl: './sobre-nos.component.html',
	styleUrls: ['./sobre-nos.component.less'],
})
export class SobreNosComponent {
	redirecionar(link: string) {
		window.location.href = link;
	}
}
