import java.util.HashSet;
import java.util.LinkedList;



class TwoBucket {

    private Result result = null;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {

        if (!startBucket.equals("one") && !startBucket.equals("two")) {
            throw new IllegalArgumentException();
        }

        HashSet<Long> visited = new HashSet<>();
        LinkedList<TwoBucketState> dequeStates = new LinkedList<TwoBucketState>();

        int invalidBucketOne = startBucket == "one" ? 0 : bucketOneCap;
        int invalidBucketTwo = startBucket == "one" ? bucketTwoCap : 0;

        dequeStates.add(new TwoBucketState(
            startBucket == "one" ? bucketOneCap : 0,
            startBucket == "one" ? 0: bucketTwoCap, 1));

        while (dequeStates.size() > 0) {
            TwoBucketState state = dequeStates.removeFirst();
            if (state.b1 == desiredLiters) { 
                result = new Result(state.step, "one", state.b2);
                return;
            }
            if (state.b2 == desiredLiters) { 
                result = new Result(state.step, "two", state.b1);
                return;
            }

            Long v = Math.round(Math.pow(2, state.b1) * Math.pow(3, state.b2));
            if ((invalidBucketOne == state.b1 && invalidBucketTwo == state.b2) || visited.contains(v)) {
                continue;
            }
            visited.add(v);

            dequeStates.add(new TwoBucketState(Math.min(state.b1 + state.b2, bucketOneCap),
                state.b2 - (Math.min(state.b1 + state.b2, bucketOneCap) - state.b1), state.step + 1));
            dequeStates.add(new TwoBucketState(state.b1 - (Math.min(state.b1 + state.b2, bucketTwoCap) - state.b2),
                Math.min(state.b1 + state.b2, bucketTwoCap), state.step + 1));

            dequeStates.add(new TwoBucketState(state.b1, 0, state.step + 1));
            dequeStates.add(new TwoBucketState(0, state.b2, state.step + 1));

            dequeStates.add(new TwoBucketState(bucketOneCap, state.b2, state.step + 1));
            dequeStates.add(new TwoBucketState(state.b1, bucketTwoCap, state.step + 1));
        }

        throw new UnreachableGoalException();

    }

    Result getResult() {
        return this.result;
    }
    

    class TwoBucketState {

        public final int b1;
        public final int b2;
        public final int step;

        public TwoBucketState(int b1, int b2, int step) {
            this.b1 = b1;
            this.b2 = b2;
            this.step = step;
        }
    }


}
