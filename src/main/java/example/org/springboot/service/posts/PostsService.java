/*
    dto 를 사용하여 Controller와 Repository의 중간에서
 */

package example.org.springboot.service.posts;

import example.org.springboot.domain.posts.Posts;
import example.org.springboot.domain.posts.PostsRepository;
import example.org.springboot.web.dto.PostsResponseDto;
import example.org.springboot.web.dto.PostsSaveRequestDto;
import example.org.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {     // update인데 DB로 쿼리를 보내는게 없는이유? 영속성Entity라서
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
