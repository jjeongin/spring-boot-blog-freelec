package com.springboot.book.freelec.service.posts;
import com.springboot.book.freelec.domain.posts.Posts;
import com.springboot.book.freelec.domain.posts.PostsRepository;
import com.springboot.book.freelec.web.dto.PostsResponseDto;
import com.springboot.book.freelec.web.dto.PostsSaveRequestDto;
import com.springboot.book.freelec.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post exists. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post exists. id=" + id));
        return new PostsResponseDto(entity);
    }
}
