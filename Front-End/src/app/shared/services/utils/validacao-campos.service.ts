import { Injectable } from '@angular/core';
import { NgModel } from '@angular/forms';

@Injectable({
	providedIn: 'root',
})
export class ValidacaoCamposService {
	constructor() {}

	campoValido(campo: NgModel): boolean {
		if (campo.touched) {
			if (campo.valid && campo.model) {
				return true;
			} else {
				campo.control.markAsDirty();
				return false;
			}
		}
		return true;
	}
}
