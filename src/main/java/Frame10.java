import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class Frame10 extends Frame {

    private final Roll third;

    public Frame10(@Nonnull Roll first, @Nonnull Roll second, @Nullable Roll third) {
        super(first, second);
        this.third = third;
    }

    Optional<Roll> getThirdRoll() {
        return Optional.ofNullable(third);
    }

    @Override
    public String toString() {
        String superString = super.toString();

        if (third != null) {
            return superString.substring(0, superString.length() - 1) + " " + third + "]";
        } else {
            return superString;
        }
    }
}
