package crud.memo.service;

import crud.memo.dto.MemoRequestDto;
import crud.memo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {

    MemoResponseDto saveMemo(MemoRequestDto requestDto);

    List<MemoResponseDto> findAllMemos();

    MemoResponseDto findMemoById(Long id);

    MemoResponseDto updateMemo(Long id, String title, String contents);
}
