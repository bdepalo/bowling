import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ScoreCalculatorTest {

    private final GameParser parser = new GameParser();
    private final ScoreCalculator calculator = new ScoreCalculator();

    @Test
    void parseGame_300_correctScore() {

        // given
        String gameString = "6678,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,X,X";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(300);
    }

    @Test
    void parseGame_case1_correctGame() {

        // given
        String gameString = "12,3,3,X,-,5,/,X,-,X,-,X,-,4,-,X,-,X,-,1,/,X";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(179);
    }

    @Test
    void parseGame_case2_correctGame() {

        // given
        String gameString = "11322,3,3,X,-,5,/,X,-,X,-,X,-,4,-,X,-,X,-,1,/,-";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(169);
    }

    @Test
    void parseGame_case3_correctGame() {

        // given
        String gameString = "12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X,1,5";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(34);
    }

    @Test
    void parseGame_case4_correctGame() {

        // given
        String gameString = "12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X,X,4";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(42);
    }

    @Test
    void parseGame_case5_correctGame() {

        // given
        String gameString = "12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,/,4";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(32);
    }

    @Test
    void parseGame_case6_correctGame() {

        // given
        String gameString = "12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X,-,X,4,-";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(54);
    }

    @Test
    void parseGame_case7_correctGame() {

        // given
        String gameString = "12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X,-,X,4,/";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(60);
    }

    @Test
    void parseGame_case8_correctGame() {

        // given
        String gameString = "12,X,-,9,/,6,2,7,/,X,-,4,4,9,/,X,-,X,-,-,5,-";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(150);
    }

    @Test
    void parseGame_case9_correctGame() {

        // given
        String gameString = "12,X,-,-,/,6,2,7,/,X,-,4,4,9,/,X,-,X,-,-,5,-";

        // when
        int score = calculator.getScore(parser.parseGame(gameString));

        // then
        assertThat(score).isEqualTo(150);
    }
}
