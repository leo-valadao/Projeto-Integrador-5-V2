export class Pagina<T> {
	public content!: T[];
	public pageable!: {
		sort: {
			empty: boolean;
			sorted: boolean;
			unsorted: boolean;
		};
		offset: number;
		pageSize: number;
		pagenumber: number;
		paged: boolean;
		unpaged: boolean;
	};
	public totalPages!: number;
	public totalElements!: number;
	public last!: boolean;
	public size!: number;
	public number!: number;
	public sort!: {
		empty: boolean;
		sorted: boolean;
		unsorted: boolean;
	};
	public numberOfElements!: number;
	public first!: boolean;
	public empty!: boolean;

	constructor() {}
}
