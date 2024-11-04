package crud.memo.repository;

import crud.memo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}