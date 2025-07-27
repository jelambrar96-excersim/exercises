
import java.util.ArrayList;
import java.util.List;


class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        
        final Signal[] signalLookTable = new Signal[] { Signal.WINK, Signal.DOUBLE_BLINK, Signal.CLOSE_YOUR_EYES, Signal.JUMP};
        ArrayList<Signal> signalArray = new ArrayList<>(signalLookTable.length);
        for (Signal s: signalLookTable) {
            if (number == 0) break;
            if (number % 2 == 1) signalArray.add(s);
            number /= 2;
        }
        return number % 2 == 0 ? signalArray : signalArray.reversed();
    }

}
