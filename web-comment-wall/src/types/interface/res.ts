export interface pageRes<T> {
  total: number;
  page: number;
  records: T[];
  pageSize: number;
}

export interface Res<T> {
  code: number;
  msg: string;
  data: T;
}
