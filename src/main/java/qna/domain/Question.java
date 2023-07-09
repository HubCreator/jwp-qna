package qna.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Lob
    @Column(name = "contents")
    private String contents;
    @Column(name = "writer_id")
    private Long writerId;
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Question(final String title, final String contents) {
        this(null, title, contents);
    }

    public Question(final Long id, final String title, final String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    protected Question() {

    }

    public Question writeBy(final User writer) {
        this.writerId = writer.getId();
        return this;
    }

    public boolean isOwner(final User writer) {
        return this.writerId.equals(writer.getId());
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public Long getWriterId() {
        return this.writerId;
    }

    public void setWriterId(final Long writerId) {
        this.writerId = writerId;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", contents='" + this.contents + '\'' +
                ", writerId=" + this.writerId +
                ", deleted=" + this.deleted +
                '}';
    }
}
