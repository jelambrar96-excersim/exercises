import java.util.Objects;

class QueenAttackCalculator {

    private boolean canAttrack;

    QueenAttackCalculator(Queen q1, Queen q2) {

        if (Objects.isNull(q1) || Objects.isNull(q2))
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        if (q1.row == q2.row && q1.column == q2.column)
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        
        if (q1.row == q2.row) this.canAttrack =  true;
        if (q1.column == q2.column) this.canAttrack =  true;
        if ((q1.column - q1.row) == (q2.column - q2.row)) this.canAttrack =  true;
        if ((q1.column + q1.row) == (q2.column + q2.row)) this.canAttrack =  true;
    }

    boolean canQueensAttackOneAnother() {
        return canAttrack;
    }

}