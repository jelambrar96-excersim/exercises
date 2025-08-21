import java.lang.Runtime;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class ParallelLetterFrequency {

    private final String[] texts;
    private static final int numThreads = Runtime.getRuntime().availableProcessors();

    ParallelLetterFrequency(String[] texts) {
        this.texts = texts;
    }

    Map<Character, Integer> countLetters() {
        Map<Character, AtomicInteger> counts = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Callable<Void>> tasks = new ArrayList<>(this.texts.length);

        for (String text : texts) {
            tasks.add(new LetterCountTask(text, counts));
        }

        try {
            executor.invokeAll(tasks);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return counts.entrySet().stream().collect(
                    Collectors.toMap(Map.Entry::getKey, (e)-> e.getValue().get()));
    }

    private class LetterCountTask implements Callable<Void> {
        private final String text;
        private final Map<Character, AtomicInteger> letterCount;

        LetterCountTask(String text, Map<Character, AtomicInteger> letterCount) {
            this.text = text;
            this.letterCount = letterCount;
        }

        @Override
        public Void call() {
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    letterCount.computeIfAbsent(
                                    Character.toLowerCase(c), k -> new AtomicInteger()
                                )
                               .incrementAndGet();
                }
            }
            return null;
        }
    };

}
