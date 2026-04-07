import java.util.List;

class BinarySearch {

    private List<Integer> listIntegers;

    BinarySearch(List<Integer> items) {
        this.listIntegers = items;
    }

    int indexOf(int item) throws ValueNotFoundException {
        
        int listSize = this.listIntegers.size();
        if (listSize == 0) throw new ValueNotFoundException("Value not in array");

        int leftIndex = 0;
        int rightIndex = listSize - 1;
        
        while (leftIndex <= rightIndex) {
            int indMiddle = leftIndex + (rightIndex - leftIndex) / 2;
            Integer eMiddle = this.listIntegers.get(indMiddle);
            if (eMiddle > item) {
                rightIndex = indMiddle - 1;
            }
            else if (eMiddle < item) {
                leftIndex = indMiddle + 1;
            }
            else {
                return indMiddle;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }
}
