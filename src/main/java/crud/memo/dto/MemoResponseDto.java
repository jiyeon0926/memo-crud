package crud.memo.dto;

import crud.memo.entity.Memo;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemoResponseDto {

    private Long id;
    private String title;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
    }
}
