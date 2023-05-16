import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class Frame {

    private final Roll first;
    private final Roll second;

    public Frame(@Nonnull Roll first, @Nullable Roll second) {
        this.first = Objects.requireNonNull(first);
        this.second = second;
    }

    @Nonnull
    public Roll getFirstRoll() {
        return first;
    }

    public Optional<Roll> getSecondRoll() {
        return Optional.ofNullable(second);
    }

    public boolean isStrike() {
        return first.getType() == Roll.RollType.STRIKE;
    }

    public boolean isSpare() {
        return second != null && second.getType() == Roll.RollType.SPARE;
    }

    @Override
    public String toString() {
        if (second == null) {
            return "[" + first + " -]";
        } else {
            return "[" + first + " " + second + "]";
        }
    }
}
