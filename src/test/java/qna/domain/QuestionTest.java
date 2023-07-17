package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.fixtures.QuestionFixture.Q1;
import static qna.fixtures.UserFixture.JAVAJIGI;

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
}
