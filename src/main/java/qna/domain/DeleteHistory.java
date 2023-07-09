package qna.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "delete_history")
public class DeleteHistory {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private ContentType contentType;
    @Column(name = "content_id")
    private Long contentId;
    @Column(name = "deleted_by_id")
    private Long deletedById;
    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    public DeleteHistory(final ContentType contentType, final Long contentId, final Long deletedById, final LocalDateTime createDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedById = deletedById;
        this.createDate = createDate;
    }

    protected DeleteHistory() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(this.id, that.id) &&
                this.contentType == that.contentType &&
                Objects.equals(this.contentId, that.contentId) &&
                Objects.equals(this.deletedById, that.deletedById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.contentType, this.contentId, this.deletedById);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
                "id=" + this.id +
                ", contentType=" + this.contentType +
                ", contentId=" + this.contentId +
                ", deletedById=" + this.deletedById +
                ", createDate=" + this.createDate +
                '}';
    }
}
