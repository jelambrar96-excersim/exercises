import java.lang.Character;


class SqueakyClean {
    public static String clean(String identifier) {
        String task1 = cleanTask1(identifier);
        String task2 = cleanTask2(task1);
        String task3 = cleanTask3(task2);
        return cleanTask4(task3);
    }
    
    private static String cleanTask1(String identifier) {
        return identifier.replace(" ", "_");
    }

    private static String cleanTask2(String identifier) {
        char[] identifierArray = identifier.toCharArray();
        int sizeOriginalArray = identifierArray.length;
        int counter = 0;
        boolean upperCaseFlag = false;
        char [] outputCharArray = new char[sizeOriginalArray];
        for (int i=0; i<sizeOriginalArray; ++i) {
            char c = identifierArray[i];
            if (c == '-') {
                upperCaseFlag = true;
                continue;
            }
            outputCharArray[counter] = upperCaseFlag ? Character.toUpperCase(c) : c;
            upperCaseFlag = false;
            counter++;
        }
        char [] output = new char[counter];
        for (int i = 0; i < counter; ++i) {
            output[i] = outputCharArray[i];
        }
        return String.copyValueOf(output);
    }

    private static String cleanTask3(String indentifier) {
        String task3 = indentifier;
        task3 = task3.replace("4", "a");
        task3 = task3.replace("3", "e");
        task3 = task3.replace("0", "o");
        task3 = task3.replace("1", "l");
        task3 = task3.replace("7", "t");
        return task3;
    }

    private static String cleanTask4(String identifier) {
        return identifier.replaceAll("[^a-zA-Z_]", "");
    }

}
