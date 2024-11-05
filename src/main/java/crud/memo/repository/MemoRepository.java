package crud.memo.repository;

import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;

import java.util.List;

public interface MemoRepository {

    MemoResponseDto saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);
}
