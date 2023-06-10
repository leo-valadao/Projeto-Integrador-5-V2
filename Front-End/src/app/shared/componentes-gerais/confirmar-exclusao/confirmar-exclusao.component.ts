import { Component, Input } from '@angular/core';

@Component({
	selector: 'app-confirmar-exclusao',
	templateUrl: './confirmar-exclusao.component.html',
	styleUrls: [],
})
export class ConfirmarExclusaoComponent {
	@Input() nomeDoItemSendoExcluido!: string;
}
