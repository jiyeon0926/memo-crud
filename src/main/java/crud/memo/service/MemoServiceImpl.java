package crud.memo.service;

import crud.memo.dto.MemoRequestDto;
import crud.memo.dto.MemoResponseDto;
import crud.memo.entity.Memo;
import crud.memo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto.getTitle(), requestDto.getContents());

        return memoRepository.saveMemo(memo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        List<MemoResponseDto> allMemos = memoRepository.findAllMemos();

        return allMemos;
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {
        Optional<Memo> optionalMemo = memoRepository.findMemoById(id);

        // 예외 처리
        if (optionalMemo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(optionalMemo.get());
    }

    @Transactional
    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {
        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updatedRow = memoRepository.updateMemo(id, title, contents);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Optional<Memo> optionalMemo = memoRepository.findMemoById(id);

        return new MemoResponseDto(optionalMemo.get());
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {
        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updatedRow = memoRepository.updateTitle(id, title);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Optional<Memo> optionalMemo = memoRepository.findMemoById(id);

        return new MemoResponseDto(optionalMemo.get());
    }

    @Override
    public void deleteMemo(Long id) {
        int deletedRow = memoRepository.deleteMemo(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
