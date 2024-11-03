package crud.memo.controller;

import crud.memo.dto.MemoRequestDto;
import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos")
public class MemoController {

    // DB 역할 수행
    private final Map<Long, Memo> memoMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {

        // 식별자가 1씩 증가
        Long memoId = memoMap.isEmpty() ? 1 : Collections.max(memoMap.keySet()) + 1;

        // 요청 받은 데이터로 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 저장
        memoMap.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {
        List<MemoResponseDto> responseList = new ArrayList<>();

        // HashMap<Memo> -> List<MemoResponseDto> 형태로 변환
        responseList = memoMap.values().stream().map(MemoResponseDto::new).toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {
        Memo memo = memoMap.get(id);

        return new MemoResponseDto(memo);
    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoMap.get(id);
        memo.update(requestDto);

        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoMap.remove(id);
    }
}