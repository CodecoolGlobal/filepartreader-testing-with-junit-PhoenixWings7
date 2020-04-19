import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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
        return null;
    }

    private void updateWords() {
        String fileContent = filePartReader.readLines();
        words = asList(fileContent.split("\\s+"));
    }

    private Comparator<String> alphaComperator = String::compareToIgnoreCase;
}
