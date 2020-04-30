import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FileWordAnalyzer {
    private FilePartReader filePartReader;
    private List<String> words;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
        this.updateWords();
    }

    public List<String> getWordsOrderedAlphabetically() {
        this.updateWords();
        words.sort(alphaComperator);

        return words;
    }

    public List<String> getWordsContainingSubstring(String subString) {
        this.updateWords();
        List<String> filtered = words.stream().distinct()
                .filter((word) -> word.toLowerCase().contains(subString.toLowerCase()))
                .collect(Collectors.toList());

        return filtered;
    }

    public List<String> getStringsWhichPalindromes() {
        this.updateWords();
        List<String> filtered = words.stream().distinct()
                .filter(word -> word.length()>2)
                .filter((word) -> {
                    String thisWord = word.toLowerCase();
                    for (int i = 0; i < thisWord.length()/2; i++) {
                        if (thisWord.charAt(i) != thisWord.charAt(thisWord.length()-i-1)) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
        return filtered;
    }

    private void updateWords() {
        String fileContent = filePartReader.readLines();
        // TODO: get words without punctuation signs
        words = Arrays.stream(fileContent.split("\\s+"))
                .map(PunctuationReplacer::deletePunctuation)
                .collect(Collectors.toList());

    }

    private final Comparator<String> alphaComperator = String::compareToIgnoreCase;
}
