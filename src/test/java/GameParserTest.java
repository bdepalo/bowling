import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class GameParserTest {

    private final GameParser parser = new GameParser();

    @Test
    void parseGame_300_correctGame() {

        // given
        String gameString = "6678,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,-,X,X,X";
        String expectedGameString = "[[X -], [X -], [X -], [X -], [X -], [X -], [X -], [X -], [X -], [X X X]]";

        // when
        Game game = parser.parseGame(gameString);

        // then
        assertThat(game.toString()).isEqualTo(expectedGameString);
    }

    @Test
    void parseGame_case1_correctGame() {

        // given
        String gameString = "12,3,3,X,-,5,/,X,-,X,-,X,-,4,-,X,-,X,-,1,/,X";
        String expectedGameString = "[[3 3], [X -], [5 /], [X -], [X -], [X -], [4 -], [X -], [X -], [1 / X]]";

        // when
        Game game = parser.parseGame(gameString);

        // then
        assertThat(game.toString()).isEqualTo(expectedGameString);
    }

    @Test
    void parseGame_missingFrame_exception() {

        // given
        String gameString = "12,3,3,X,-,5,/,X,-,X,-,4,-,X,-,X,-,1,/,X";

        // when
        Throwable thrown = catchThrowable(() -> parser.parseGame(gameString));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
