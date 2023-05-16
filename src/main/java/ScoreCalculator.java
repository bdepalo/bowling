import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class ScoreCalculator {

    public int getScore(@Nonnull Game game) {
        List<Frame> frames = game.getFrames();
        int score = 0;

        for (int i = 0; i < 8; i++) {
            Frame currentFrame = frames.get(i);
            Frame nextFrame = frames.get(i + 1);
            Frame thirdFrame = frames.get(i + 2);

            score += scoreRegularFrame(currentFrame, nextFrame, thirdFrame);
        }

        Frame ninth = frames.get(8);
        Frame10 tenth = (Frame10) frames.get(9);

        score += scoreNinthFrame(ninth,tenth);
        return score + scoreTenthFrame(tenth);
    }

    private int scoreRegularFrame(@Nonnull Frame currentFrame, @Nonnull Frame nextFrame, @Nullable Frame thirdFrame) {
        if (currentFrame.isSpare()) {
            return 10 + nextFrame.getFirstRoll().getValue();
        } else if (currentFrame.isStrike()) {
            return scoreStrike(nextFrame, thirdFrame);
        } else {
            return currentFrame.getFirstRoll().getValue() + currentFrame.getSecondRoll().map(Roll::getValue).orElse(0);
        }
    }

    private int scoreNinthFrame(@Nonnull Frame frame9, @Nonnull Frame10 frame10) {

        if (frame9.isStrike() && frame10.isStrike()) {
            return 20 + frame10.getSecondRoll().map(Roll::getValue).orElse(0);
        }

        return scoreRegularFrame(frame9, frame10, null);
    }

    private int scoreTenthFrame(@Nonnull Frame10 frame) {

        Roll roll1 = frame.getFirstRoll();
        Roll roll2 = frame.getSecondRoll().orElseThrow();
        Optional<Roll> roll3 = frame.getThirdRoll();

        int roll1Val = roll1.getValue();
        int roll2Val = roll2.getValue();

        if(roll2.getType() == Roll.RollType.SPARE){
            roll2Val = 10;
            roll1Val = 0;
        }

        int roll3Val = frame.getThirdRoll().map(Roll::getValue).orElse(0);

        if(roll3.map(Roll::getType).orElse(null) == Roll.RollType.SPARE){
            roll3Val = 10;
            roll2Val = 0;
        }

        return roll1Val + roll2Val + roll3Val;
    }

    private int scoreStrike(@Nonnull Frame nextFrame, @Nullable Frame thirdFrame) {

        if (nextFrame.isStrike()) {
            if (thirdFrame != null) {
                return 20 + thirdFrame.getFirstRoll().getValue();
            } else {
                return 20;
            }
        } else if (nextFrame.isSpare()) {
            return 20;
        } else {
            Roll next1 = nextFrame.getFirstRoll();
            Optional<Roll> next2 = nextFrame.getSecondRoll();

            return 10 + next1.getValue() + next2.map(Roll::getValue).orElse(0);
        }
    }
}
