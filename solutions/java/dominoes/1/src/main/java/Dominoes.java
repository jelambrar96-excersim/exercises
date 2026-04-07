import java.util.List;
import java.util.ArrayList;

class Dominoes {

    List<Domino> generateChain(List<Domino> inputDominoes, Domino match) {
        int sizeDominoes = inputDominoes.size();
        if (sizeDominoes == 0) { return new ArrayList<Domino>(); }
        if (sizeDominoes == 1) {
            Domino firstDomino = inputDominoes.get(0);
            if (match == null) {
                return firstDomino.getLeft() == firstDomino.getRight() ?
                        new ArrayList<Domino>(List.of(firstDomino)) : null;
            }
            if (firstDomino.getLeft() == match.getRight() &&
                firstDomino.getRight() == match.getLeft()) {
                return inputDominoes;
            }
            if (firstDomino.getRight() == match.getRight() &&
                firstDomino.getLeft() == match.getLeft()) {
                return new ArrayList<Domino>(
                    List.of(new Domino(firstDomino.getRight(), firstDomino.getLeft())));
            }
            return null;
        }
        for (int i = 0; i < sizeDominoes; i++) {
            Domino currentDomino = inputDominoes.get(i);
            List<Domino> remainingDominoes = new ArrayList<>(inputDominoes);
            remainingDominoes.remove(i);
            if (match == null) {
                List<Domino> chain = generateChain(remainingDominoes, currentDomino);
                if (chain != null) {
                    chain.add(0, currentDomino);
                    return chain;
                }
            }
            else if (currentDomino.getLeft() == match.getRight()) {
                List<Domino> chain = generateChain(remainingDominoes, 
                    new Domino(match.getLeft(), currentDomino.getRight()));
                if (chain != null) {
                    chain.add(0, currentDomino);
                    return chain;
                }
            }
            else if (currentDomino.getRight() == match.getRight()) {
                List<Domino> chain = generateChain(remainingDominoes,
                    new Domino(match.getLeft(), currentDomino.getLeft()));
                if (chain != null) {
                    chain.add(0, new Domino(currentDomino.getRight(), currentDomino.getLeft()));
                    return chain;
                }
            }
        }
        return null;
    }

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        List<Domino> anotherDominoChain = generateChain(inputDominoes, null);
        if (anotherDominoChain != null) {
            return anotherDominoChain;
        }
        throw new ChainNotFoundException("No domino chain found.");
    }

}