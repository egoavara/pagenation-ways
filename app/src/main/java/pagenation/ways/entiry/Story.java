package pagenation.ways.entiry;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "hmb", name = "story")
public class Story {
    @Id
    private String storyId;
    private String title;
    private LocalDateTime postAt;

    public Story() {
    }

    public Story(String storyId, String title, LocalDateTime postAt) {
        this.storyId = storyId;
        this.title = title;
        this.postAt = postAt;
    }

    public String getStoryId() {
        return storyId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getPostAt() {
        return postAt;
    }

}
