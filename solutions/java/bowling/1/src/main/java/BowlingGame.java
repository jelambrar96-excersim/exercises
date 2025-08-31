import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class BowlingGame {

    private List<Frame> frames;
    private List<Integer> bonus;
    private int frameNum;

    public BowlingGame() {
        this.bonus = new ArrayList<>(10);
        this.frames = new ArrayList<>(10);
        for (int i = 0; i < 10; ++i) {
            this.frames.add(new Frame());
        }
        this.frameNum = 0;
    }

    private void handleBonus(int pins) {
        if (this.frames.get(9).isSpare()) {
            if (this.bonus.size() == 1) {
                throw new IllegalStateException("Cannot roll after game is over");
            }
            this.bonus.add(pins);
        }
        else if (this.frames.get(9).isStrike()) {
            if (this.bonus.size() == 2) {
                throw new IllegalStateException("Cannot roll after game is over");
            }
            if (this.bonus.stream().reduce(0, Integer::sum) + pins > 10) {
                if (!(this.bonus.size() == 1 && this.bonus.get(0) == 10)) {
                    throw new IllegalStateException("Pin count exceeds pins on the lane");
                }
            }
            this.bonus.add(pins);
        }
        else {
            throw new IllegalStateException("Cannot roll after game is over");
        }
    }

    void roll(int pins) {
        if (pins < 0) {
            throw new IllegalStateException("Negative roll is invalid");
        }
        if (pins > 10) {
            throw new IllegalStateException("Pin count exceeds pins on the lane");
        }
        if (this.frameNum == 10) {
            this.handleBonus(pins);
        }
        else {
            this.frames.get(this.frameNum).roll(pins);
            if (this.frames.get(this.frameNum).isDone()) {
                this.frameNum += 1;
            }
        }
    }

    List<Integer> nextThrows(int i) {
        List<Integer> output = new ArrayList<>();
        int nextFrameID = i + 1;
        while (nextFrameID < 10) {
            output.addAll(this.frames.get(nextFrameID).rolls);
            nextFrameID++;
        }
        output.addAll(this.bonus);
        return output;
    }
    
    int score() {
        if (this.frameNum < 10) {
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        }
        if (this.frames.get(9).isSpare()) {
            if (this.bonus.size() != 1) {
                throw new IllegalStateException("Score cannot be taken until the end of the game");
            }
        }
        else if (this.frames.get(9).isStrike()) {
            if (this.bonus.size() != 2) {
                throw new IllegalStateException("Score cannot be taken until the end of the game");
            }
        }
        return IntStream.range(0, this.frames.size()).map(
            i -> this.frames.get(i).score(nextThrows(i))
        ).sum();
    }

    private class Frame {

        List<Integer> rolls = new ArrayList<>(3);

        boolean isStrike() {
            return rolls.get(0) == 10;
        }

        boolean isDone() {
            return this.frameScore() == 10 || this.rolls.size() == 2;
        }
        
        boolean isSpare() {
            return !isStrike() && (rolls.get(0) + rolls.get(1) == 10);
        }
        
        int frameScore() {
            return this.rolls.stream().reduce(0, Integer::sum);
        }
        
        void roll(int pins) {
            if (pins < 0) {
                throw new IllegalStateException("Negative roll is invalid");
            } 
            if (pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
            if (frameScore() + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
            this.rolls.add(pins);
        }

        int score(List<Integer> next_throws) {
            int frameScore = this.frameScore();
            if (this.isStrike()) {
                frameScore += next_throws.subList(0, 2).stream().reduce(0, Integer::sum);
            }
            else if (this.isSpare()) {
                frameScore += next_throws.get(0);
            }
            return frameScore;
        }

    }

}