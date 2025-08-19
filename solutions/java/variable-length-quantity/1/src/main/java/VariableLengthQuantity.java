import java.lang.Long;
import java.lang.String;

import java.util.List;
import java.util.ArrayList;

import java.math.BigInteger;

class VariableLengthQuantity {

    private static int VLQ_NUMBER_MASK = 0b01111111;
    private static int VLQ_CONTINUE_MASK = 0b10000000;

    List<String> encode(List<Long> numbers) {
        List<String> encodedList = new ArrayList<String>();
        for (Long n: numbers) {
            int bitLength = BigInteger.valueOf(n >> 1).bitLength() / 7;
            for (int i = bitLength; i >= 0; i--) {
                long currentByte = (n >> (7 * i)) & VLQ_NUMBER_MASK;
                if (i > 0){
                    currentByte |= VLQ_CONTINUE_MASK;
                }
                encodedList.add("0x" + Long.toHexString(currentByte));
            }
        }
        return encodedList;
    }

    List<String> decode(List<Long> bytes) {
        long code = 0;
        if ((bytes.getLast() & VLQ_CONTINUE_MASK) > 0) {
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }
        ArrayList<String> decodedBytes = new ArrayList<String>();
        for (Long b: bytes) {
            code <<= 7;
            code |= (b & VLQ_NUMBER_MASK);
            if ((b & VLQ_CONTINUE_MASK) == 0){
                decodedBytes.add("0x" + Long.toHexString(code));
                code = 0;
            }
        }
        return decodedBytes;
    }
}
