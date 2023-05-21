package pagenation.ways.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import pagenation.ways.entiry.Story;
import pagenation.ways.repository.StoryRepository;

@Service
public class DataService {
    @Autowired
    private StoryRepository repository;
    @Autowired
    protected JdbcTemplate jdbc;
    @Autowired
    private EntityManager em;

    public List<Story> page(int page, int size) {
        return repository.findAll(PageRequest.of(page - 1, size)).get().collect(Collectors.toList());
    }

    public Stream<Story> pageStream(int page, int size) {
        return repository.findAll(PageRequest.of(page - 1, size)).stream();
    }

    public Stream<Story> rawPageStream(int page, int size) {
        return jdbc.queryForStream("select story_id, title, post_at from hmb.Story limit ? OFFSET ?",
                new RowMapper<Story>() {

                    @Override
                    public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
                        // TODO Auto-generated method stub
                        return new Story(
                                rs.getString("story_id"),
                                rs.getString("title"),
                                rs.getTimestamp("post_at").toLocalDateTime());
                    }
                }, size, size * (page - 1));
    }

    public Stream<Story> emPageStream(int page, int size) {
        var cq = em.getCriteriaBuilder().createQuery(Story.class);
        var root = cq.from(Story.class);
        cq.select(root);
        var tq = em.createQuery(cq);
        tq.setFirstResult(size * (page - 1));
        tq.setMaxResults(size);
        return tq.getResultStream();
    }

    @Transactional(readOnly = true)
    public Stream<Story> repoPageStream(int page, int size) {
        return repository.findAllBy(PageRequest.of(page - 1, size));
    }
}
