package pagenation.ways.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pagenation.ways.entiry.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, String> {
    Stream<Story> findAllBy(Pageable pageable);
}
