import javax.annotation.Nonnull;

public class Roll {

    private final RollType rollType;
    private final int value;

    public enum RollType {
        STRIKE, SPARE, REGULAR
    }

    private Roll(@Nonnull RollType rollType, int value) {
        this.rollType = rollType;
        this.value = value;
    }

    @Nonnull
    public static Roll regular(int value) {
        return new Roll(RollType.REGULAR, value);
    }

    @Nonnull
    public static Roll strike() {
        return new Roll(RollType.STRIKE, 10);
    }

    @Nonnull
    public static Roll spare() {
        return new Roll(RollType.SPARE, 0);
    }

    public int getValue() {
        return value;
    }

    public RollType getType() {
        return rollType;
    }

    @Override
    public String toString() {

        if (rollType == RollType.STRIKE) {
            return "X";
        }

        if (rollType == RollType.SPARE) {
            return "/";
        }

        if (value == 0) {
            return "-";
        }

        return String.valueOf(value);
    }
}
