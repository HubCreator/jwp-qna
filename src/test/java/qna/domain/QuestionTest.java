package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.fixtures.QuestionFixture.Q1;
import static qna.fixtures.UserFixture.JAVAJIGI;
import static qna.fixtures.UserFixture.SANJIGI;

@DataJpaTest
public class QuestionTest {

    @Test
    @DisplayName("어떤 사용자의 질문인지 확인할 수 있다.")
    void isOwner() {
        // given
        final User user = JAVAJIGI;

        // when, then
        assertThat(Q1.isOwner(user)).isTrue();
    }

    @Test
    @DisplayName("질문과 답변을 모두 지울 수 있다.")
    void deleteWithAnswers() throws CannotDeleteException {
        //given
        Question question = new Question("title", "contents").writeBy(JAVAJIGI);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L, JAVAJIGI, question, "answer1"));
        answers.add(new Answer(2L, JAVAJIGI, question, "answer2"));

        //when
        List<DeleteHistory> deletedHistories = question.delete(JAVAJIGI, answers);

        //then
        assertThat(deletedHistories).hasSize(3);
    }

    @Test
    @DisplayName("delete 실패 - 질문 삭제 권한")
    void delete_fail_validationQuestion() {
        //given
        Question question = new Question("title", "contents").writeBy(JAVAJIGI);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L, JAVAJIGI, question, "answer1"));
        answers.add(new Answer(2L, JAVAJIGI, question, "answer2"));

        //when, then
        assertThatThrownBy(() -> question.delete(SANJIGI, answers))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("delete 실패 - 다른 사람이 쓴 답변 삭제")
    void delete_fail_validationAnswers() {
        //given
        Question question = new Question("title", "contents").writeBy(JAVAJIGI);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L, JAVAJIGI, question, "answer1"));
        answers.add(new Answer(2L, SANJIGI, question, "answer2"));

        //when, then
        assertThatThrownBy(() -> question.delete(JAVAJIGI, answers))
                .isInstanceOf(CannotDeleteException.class);
    }
}
