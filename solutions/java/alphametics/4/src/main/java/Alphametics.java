import java.lang.Character;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Alphametics {

    private String pluzze; 

    Alphametics(String userInput) {
        this.pluzze = userInput.toUpperCase().replace(" ", "");
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        
        // calculo el numero de letras, no debe ser mayor al numero de digitos
        int numberLetters = (int)this.pluzze.chars().filter(Character::isAlphabetic)
                .distinct().count();
        if (numberLetters > 10) throw new UnsolvablePuzzleException(); 

        // obtengo las letras que no pueden ser iguales a cero
        List<Character> fisrtLettersList = Arrays.asList(this.pluzze.replace("==", "+").split("\\+"))
                .stream().map(s -> s.charAt(0)).distinct().collect(Collectors.toList());

        String [] splittedPluzze = this.pluzze.split("==");
        int maxLength = splittedPluzze[1].length();

        // estos son los valores debajo de la linea de suma
        List<Character> results = new StringBuilder(splittedPluzze[1]).reverse().toString().chars()
                .mapToObj(i -> (char)i).toList();

        // estos son los valores encima de la linea de suma
        List<String> words = Arrays.asList(splittedPluzze[0].split("\\+")).stream()
                .map(s -> new StringBuilder(s).reverse().toString()).toList();
        List<List<Character>> factorList = IntStream.range(0, maxLength).mapToObj(
            i -> words.stream().filter(s -> s.length() > i)
                    .map(s -> s.charAt(i)).collect(Collectors.toList())
        ).collect(Collectors.toList());

        // ejecucion del metodo recursivo
        Map<Character, Integer> solution = solve(
            new HashMap<Character, Integer>(),
            factorList,
            results,
            fisrtLettersList,
            0,
            0
        );

        if (solution == null ) {
            throw new UnsolvablePuzzleException();
        }
        return solution;
    }


    private Map<Character, Integer> solve(
        Map<Character, Integer> currentSolution,
        final List<List<Character>> factors,
        final List<Character> results, 
        final List<Character> noZero,
        Integer carry, int ind) 
    {
        if (ind >= results.size()) {
            return carry == 0 ? currentSolution : null;
        }

        // tomo los enteros entre 0 y 9 que no han sido usados para contruir la permutacion
        List<Integer> noUsedDigits = IntStream.range(0, 10)
                .filter(i -> !currentSolution.values().contains(i)).boxed().toList();

        // si no hay valores no-usados puedo verificar direactamente la ecuacion con los valores
        // que ya se tienem
        if (noUsedDigits.size() == 0) {
            Integer ncarry = newCarry(currentSolution, factors.get(ind), results.get(ind), carry);
            if (ncarry == null) { return null; }
            return solve(currentSolution, factors, results, noZero, ncarry, ind + 1);
        }
        
        // tomo el numero de elementos distintos en la ecuacion int que no estan dentro de
        // la solicion actual, para hallar el r. de la permutacion
        List<Character> newKeys = factors.get(ind).stream().distinct()
                .filter(i -> !currentSolution.keySet().contains(i)).toList();
        if (!currentSolution.keySet().contains(results.get(ind)) && !newKeys.contains(results.get(ind))) {
            newKeys = new ArrayList<Character>(newKeys);
            newKeys.add(results.get(ind));
        }
                
        // si todos los valores de la ecuacion acutal han sido usados, se procede a evaluar
        int rperm = newKeys.size();
        if (rperm == 0) {
            Integer ncarry = newCarry(currentSolution, factors.get(ind), results.get(ind), carry);
            if (ncarry == null) { return null; }
            return solve(currentSolution, factors, results, noZero, ncarry, ind + 1);
        }

        // se ejecutan las perutaciones
        Permutations<Integer> digitPerm = new Permutations<Integer>(noUsedDigits, rperm);
        for (List<Integer> perm: digitPerm) {

            // se crea una nueva solucion con la permutacion encontradas
            Map<Character, Integer> copySolution = new HashMap<Character,Integer>(currentSolution);
            boolean breakFlag = false;
            for (int i = 0; i < rperm; ++i) {
                if (perm.get(i) == 0 && noZero.contains(newKeys.get(i))) {
                    breakFlag = true;
                    break;
                }
                copySolution.put(newKeys.get(i), perm.get(i));
            }
            if (breakFlag) { continue; }

            Integer ncarry = newCarry(copySolution, factors.get(ind), results.get(ind), carry);
            if (ncarry == null) { continue; }

            Map<Character, Integer> newSolution = solve(
                copySolution, factors, results, noZero, ncarry, ind + 1);
            
            if (newSolution != null) { return newSolution; }
        }
        return null;
    }
    
    
    Integer newCarry(
        Map<Character, Integer> currentSolution, 
        List<Character> indFactors,
        Character result,
        Integer carry) 
    {
        Integer suma = indFactors.stream().mapToInt(i -> currentSolution.get(i)).sum();
        suma += carry;
        if ((suma % 10) != currentSolution.get(result)) { return null; }
        return suma / 10;
    }

}