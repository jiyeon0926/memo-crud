package crud.memo.repository;

import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;

import java.util.List;

public interface MemoRepository {

    Memo saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();
}
