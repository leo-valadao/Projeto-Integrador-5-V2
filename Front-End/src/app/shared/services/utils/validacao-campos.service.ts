import { Injectable } from '@angular/core';
import { NgModel } from '@angular/forms';

@Injectable({
	providedIn: 'root',
})
export class ValidacaoCamposService {
	constructor() {}

	campoInvalido(campo: NgModel): boolean {
		if (campo.touched) {
			if (campo.valid && campo.model) {
				return false;
			} else {
				campo.control.markAsDirty();
				return true;
			}
		}
		return false;
	}
}
