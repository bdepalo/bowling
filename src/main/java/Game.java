import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class Game {

    private final List<Frame> frames;

    public Game(@Nonnull List<Frame> frames) {

        if (Objects.requireNonNull(frames).size() != 10) {
            throw new IllegalArgumentException("Expected ten frames, but received " + frames.size());
        }

        this.frames = frames;
    }

    public List<Frame> getFrames(){
        return frames;
    }

    @Override
    public String toString() {
        return frames.toString();
    }
}
