package crud.memo.controller;

import crud.memo.dto.MemoRequestDto;
import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos")
public class MemoController {

    // DB 역할 수행
    private final Map<Long, Memo> memoMap = new HashMap<>();

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {

        // 식별자가 1씩 증가
        Long memoId = memoMap.isEmpty() ? 1 : Collections.max(memoMap.keySet()) + 1;

        // 요청 받은 데이터로 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 저장
        memoMap.put(memoId, memo);

        return new MemoResponseDto(memo);
    }
}