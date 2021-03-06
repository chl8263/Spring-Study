package me.ewan.springdata.repositrory;

import me.ewan.springdata.domain.Comment;
import me.ewan.springdata.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//public interface CommentRepository {
//
//    Comment save (Comment comment);
//
//    List<Comment> findAll();
//}

@NoRepositoryBean
public interface CommentRepository extends MyRepository<Comment, Long>{

    List<Comment> findByCommentStringContainsIgnoreCaseAndLikeCountGreaterThan(String key, int likeCount);

    List<Comment> findByCommentStringContainsIgnoreCaseOrderByLikeCountDesc(String key);

    @Async
    List<Comment> findByLikeCountIsGreaterThan(int count);

    Page<Comment> findByCommentStringContainsIgnoreCase(String key, Pageable pageable);

//    @Query(value = "SELECT c FROM Comment AS c", nativeQuery = true)
//    List<Comment> findByTitle(String keyword);
//
//    //List<Comment> findByTitleIgnore(String title);
//
//    List<Comment> findByTitleContains(String title);
//
//    Page<Comment> findByLikeCountGreaterThanAndPostOrderByIdDesc(int likeCount, Post post, Sort sort); //Pageable pageable);
}