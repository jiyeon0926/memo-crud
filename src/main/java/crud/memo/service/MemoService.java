package crud.memo.service;

import crud.memo.dto.MemoRequestDto;
import crud.memo.dto.MemoResponseDto;

public interface MemoService {

    MemoResponseDto saveMemo(MemoRequestDto requestDto);
}
