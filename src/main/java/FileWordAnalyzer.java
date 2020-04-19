import java.util.*;
import java.util.Collections.*;

import static java.util.Arrays.*;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically() {
        String fileContent = filePartReader.readLines();

        List<String> words = asList(fileContent.split("\\s+"));
        words.sort(alphaComperator);

        return words;
    }

    public List<String> getWordsContainingSubstring(String subString) {
        return null;
    }

    public List<String> getStringsWhichPalindromes() {
        return null;
    }

    private Comparator<String> alphaComperator = String::compareToIgnoreCase;
}
