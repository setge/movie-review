package com.setge.talkingtoday.repository;

import com.setge.talkingtoday.dto.MovieImageDTO;
import com.setge.talkingtoday.entity.Movie;
import com.setge.talkingtoday.entity.MovieImage;
import com.setge.talkingtoday.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, mi, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review  r on r.movie = m " +
            "group by m ")
    Page<Object[]> getListPage(Pageable pageable); // Movie, MovieImage, 평균평점, Review 갯수

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);

    @Query("select mi from MovieImage mi where mi.movie.mno =:mno")
    List<MovieImage> getMovieImageListByMno(Long mno);

}
