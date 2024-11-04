package crud.memo.repository;

import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoRepositoryImpl implements MemoRepository {

    private final Map<Long, Memo> memoMap = new HashMap<>();

    @Override
    public Memo saveMemo(Memo memo) {

        // 식별자 자동 생성
        Long memoId = memoMap.isEmpty() ? 1 : Collections.max(memoMap.keySet()) + 1;
        memo.setId(memoId);

        memoMap.put(memoId, memo);

        return memo;
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        List<MemoResponseDto> allMemos = new ArrayList<>();
        allMemos = memoMap.values().stream().map(MemoResponseDto::new).toList();

        return allMemos;
    }

    @Override
    public Memo findMemoById(Long id) {
        return memoMap.get(id);
    }
}