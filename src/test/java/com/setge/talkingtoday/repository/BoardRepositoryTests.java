package com.setge.talkingtoday.repository;

import com.setge.talkingtoday.entity.Board;
import com.setge.talkingtoday.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepo;

    @Test
    public void 게시글_등록한다() {
        IntStream.rangeClosed(1, 10).forEach(i ->{
            Member member = Member.builder().email("user"+i+"@aaa.com").build();
            Board board = Board.builder()
                    .title("통통"+i)
                    .content("팅팅볼"+i)
                    .member(member)
                    .build();
            boardRepo.save(board);
        });
    }

    @Test
    @Transactional
    public void 로딩_테스트한다() {
        Optional<Board> result = boardRepo.findById(20L);

        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getMember());
    }

    @Test
    public void 조인_테스트한다() {
        List<Object[]> result = boardRepo.getBoardWithReply(10L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void 리플_카운트센다() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void 게시글_가져온다() {
        Object result = boardRepo.getBoardByBno(1L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void 서치_테스트() {
        boardRepo.search1();
    }

    @Test
    public void 서치페이지_테스트() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepo.searchPage("twc", "1", pageable);

    }

    @Test
    public void 제목검색_테스트한다() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
                .and(Sort.by("title").ascending()));

        Page<Object[]> result = boardRepo.searchPage("t", "1", pageable);
    }

}
