export type BoardPaging = {
  totalCount: number;
  page: number;
  boardList: Board[];
};

export type Board = {
  brdIdx?: number;
  brdType?: string;
  brdContent: string;
  brdTitle: string;
  useYn: string;
  boardType: BoardType;
};

export type BoardType = {
  brdType: string;
  brdNm: string;
};
