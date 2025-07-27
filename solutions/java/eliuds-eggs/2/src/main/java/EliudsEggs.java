public class EliudsEggs {
    public int eggCount(int number) {
        int count = 0;
        while (number > 0) {
            // Use bitwise AND to check the last bit
            count += (number & 1);
            // Right-shift the number by one place to process the next bit
            number >>= 1; // This is a shorthand for number = number >> 1;
        }
        return count;
    }
}
