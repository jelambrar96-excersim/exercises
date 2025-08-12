import java.util.Arrays;
import java.util.stream.Collectors;


public class Transpose {
    public String transpose(String toTranspose) {
        
        String [] lines = toTranspose.split("\n");
        int maxLength = Arrays.stream(lines)
            .mapToInt(String::length)
            .max()
            .orElse(0);
        
        Character [][] transposed = new Character[maxLength][lines.length];
        for (int i = 0; i < lines.length; i++) {
            char [] chars = lines[i].toCharArray();
            int charlen = chars.length;
            for (int j = 0; j < charlen; j++) {
                transposed[j][i] = chars[j]; 
            }
            for (int j = charlen; j < maxLength; j++) {
                transposed[j][i] = Character.valueOf((char)0);
            }
        }

        for (int i = 0; i < maxLength; i++) {
            boolean allNull = false;
            for (int j = lines.length - 1; j >= 0; j--) {
                if (transposed[i][j].charValue() != 0) {
                    allNull = true;
                }
                transposed[i][j] = (transposed[i][j].charValue() == 0 && allNull) ? 
                                        ' ' : transposed[i][j];
            }
        }

        return Arrays.stream(transposed)
            .map(row -> Arrays.stream(row)
                .filter(c -> c.charValue() != 0)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                )
            .collect(Collectors.joining("\n"));
    }
}
