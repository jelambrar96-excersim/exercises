import io.reactivex.Observable;
import java.util.*;
import java.util.stream.Collectors;


class Hangman {
    
    Observable<Output> play(Observable<String> words, Observable<String> letters) {
        
        return letters.publish(l -> words.switchMap(word -> {
            final int length = word.length();
            
            Set<String> guess = new HashSet<>(), misses = new HashSet<>();
            List<Part> parts = new LinkedList<>();

            return l.map(letter -> {
                if (guess.contains(letter) || misses.contains(letter)) 
                    throw new IllegalArgumentException(
                        "Letter " + letter + " was already played");
                
                if (word.contains(letter)) {
                    guess.add(letter);
                } else {
                    parts.add(Part.values()[misses.size()]);
                    misses.add(letter);
                }
                String discoveredString = word.chars()
                    .mapToObj(c -> guess.contains(
                        Character.toString((char)c)) ? Character.toString((char)c) : "_")
                    .collect(Collectors.joining());
                
                Status gameStatus = discoveredString.equals(word) ? Status.WIN :
                        misses.size() >= Part.values().length ? Status.LOSS : Status.PLAYING;
    
                return new Output(
                    word,
                    discoveredString,
                    guess,
                    misses,
                    parts,
                    gameStatus
                );
            })
            .takeUntil(output -> output.status != Status.PLAYING)
            .lastElement()
            .defaultIfEmpty(new Output(
                word,
                "_".repeat(length),
                Set.of(),
                Set.of(),
                List.of(),
                Status.PLAYING
            ))
            .toObservable();
        }));
    }

}
