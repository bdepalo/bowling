import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

public class GameParser {

    @Nonnull
    public Game parseGame(@Nonnull String input) {
        validateInput(input);

        int gameId = getGameId(input); // not used?

        String game = removeGameId(input);

        List<Optional<Roll>> rolls = parseRolls(game);

        return createGame(gameId, rolls);
    }

    @Nonnull
    private List<Optional<Roll>> parseRolls(@Nonnull String game) {
        return Arrays.stream(game.split(","))
                .map(this::toRoll)
                .collect(Collectors.toList());
    }

    @Nonnull
    private String removeGameId(@Nonnull String input) {
        return input.substring(input.indexOf(",") + 1);
    }

    private int getGameId(@Nonnull String input) {
        return Integer.parseInt(input.split(",")[0]);
    }

    private Optional<Roll> toRoll(@Nonnull String roll) {

        if (roll.equalsIgnoreCase("x")) {
            return Optional.of(Roll.strike());
        }

        if (roll.equalsIgnoreCase("/")) {
            return Optional.of(Roll.spare());
        }

        if (roll.equalsIgnoreCase("-")) {
            return Optional.empty();
        }

        return Optional.of(Roll.regular(Integer.parseInt(roll)));
    }

    @Nonnull
    private Game createGame(int gameId, @Nonnull List<Optional<Roll>> rolls) {

        if (rolls.size() != 21) {
            throw new IllegalArgumentException("Expected 21 rolls, but received " + rolls.size());
        }

        List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < 18; i += 2) {
            Optional<Roll> roll1 = rolls.get(i);
            Optional<Roll> roll2 = rolls.get(i + 1);

            if (roll1.isEmpty()) {
                frames.add(new Frame(Roll.regular(0), roll2.orElse(null)));
            } else {
                frames.add(new Frame(roll1.get(), roll2.orElse(null)));
            }
        }

        frames.add(getLastFrame(rolls));

        return new Game(frames);
    }

    @Nonnull
    private Frame getLastFrame(@Nonnull List<Optional<Roll>> rolls) {
        Optional<Roll> roll1 = rolls.get(18);
        Optional<Roll> roll2 = rolls.get(19);
        Optional<Roll> roll3 = rolls.get(20);


        return new Frame10(roll1.orElse(Roll.regular(0)), roll2.orElse(Roll.regular(0)), roll3.orElse(null));
    }

    private void validateInput(@Nonnull String input) throws IllegalArgumentException {
        Objects.requireNonNull(input);

        String[] lengths = input.split(",");
        int length = lengths.length;

        if (length != 22) {
            System.out.println(input);
            throw new IllegalArgumentException("Expected game to have an ID and 21 rolls, got an ID and "
                    + (length - 1) + " rolls");
        }

        // other validation here
    }
}
