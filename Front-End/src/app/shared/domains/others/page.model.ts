export class Page<T> {
  public content!: T[];
  public pageable!: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: Number;
    pageSize: Number;
    pageNumber: Number;
    paged: boolean;
    unpaged: boolean;
  };
  public totalPages!: Number;
  public totalElements!: Number;
  public last!: boolean;
  public size!: Number;
  public Number!: Number;
  public sort!: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  public numberOfElements!: Number;
  public first!: boolean;
  public empty!: boolean;

  constructor() {}
}
