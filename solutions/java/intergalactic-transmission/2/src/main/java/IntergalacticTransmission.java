import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {
        
        List<Integer> byteItems = new ArrayList<>();
        int counter = 0;
        int messageSize = message.size();
        // boolean breaked = false;
        
        while (counter < messageSize) {
            int modCounter = counter % 7;
            int li = 7 - modCounter;
            if (modCounter == 0) {
                byteItems.add(message.get(counter) & 0xFE);
            }
            else {
                Integer byteLastItem = message.get(counter - 1);
                Integer byteItem = message.get(counter);
                Integer processedItem = (((byteItem >>> modCounter) | (byteLastItem << (li + 1))) & 0xFE);
                byteItems.add(processedItem);
            }
            counter++;            
            if (modCounter == 6 || counter == messageSize) {
                byteItems.add((message.get(counter - 1) << li) & 0xFF);
            }
        }

        return byteItems.stream()
                .map(x-> (Integer.bitCount(x) % 2 == 0) ? x : (x | 0x01))
                .toList();
    }

    public static List<Integer> decodeSequence(List<Integer> sequence) {
        if (sequence.stream().anyMatch(x -> Integer.bitCount(x) % 2 != 0)) {
            throw new IllegalArgumentException("Invalid sequence: contains odd parity byte.");
        }

        int sequenceSize = sequence.size();
        List<Integer> decodedMessage = new ArrayList<Integer>(sequenceSize);
        int counter = 0;

        while (counter < sequenceSize) {
            int modCounter = (counter) % 8;
            int li = 7 - modCounter;

            System.out.println("Counter: " + counter + ", modCounter: " + modCounter + ", li: " + li);
            if (counter == sequenceSize - 1) {
                int positionLast = decodedMessage.size() - 1;
                Integer byteItem = decodedMessage.get(positionLast);
                Integer nextItem = sequence.get(counter) & 0xFE;
                Integer processedItem = (byteItem | nextItem >>> (li + 1)) & 0xFF;
                decodedMessage.remove(positionLast);
                decodedMessage.add(processedItem);
            }
            else {
                Integer byteItem = sequence.get(counter) & 0xFE;
                Integer nextItem = sequence.get(counter + 1) & 0xFE;
                Integer processedItem = ((byteItem << modCounter) | nextItem >>> li) & 0xFF;
                decodedMessage.add(processedItem);
            }
            System.out.println("Decoded message: " + decodedMessage);
            counter += 1;
            if (modCounter == 6) counter += 1;
        }

        return decodedMessage;
    }

}
