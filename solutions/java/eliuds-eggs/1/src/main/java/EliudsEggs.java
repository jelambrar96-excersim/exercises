public class EliudsEggs {
    public int eggCount(int number) {
        int counts = 0;
        while (number != 0) {
            if (number % 2 != 0) counts += 1;
            number /= 2;
        }
        return counts;
    }
}
