import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileWordAnalyzerTest {
    static FilePartReader filePartReader;
    static FileWordAnalyzer wordAnalyzer;

    @BeforeAll
    static void setUpTestEnv() throws IOException {
        filePartReader = new FilePartReader();
        String currentDir = System.getProperty("user.dir");
        filePartReader.setup(currentDir + "/src/test/resources/wordsFile.txt",
                1, 2);
        filePartReader.read();
        wordAnalyzer = new FileWordAnalyzer(filePartReader);
    }

    @Test
    void should_returnAlphabeticallySortedWords_when_invoked() {
        filePartReader.setFromLine(1);
        filePartReader.setToLine(2);

        List<String> expectedResult = Arrays.asList("a", "article", "decided", "for", "go", "Having",
                "Ive", "reading", "the", "to", "trouble", "walk") ;
        List<String> actualResult = wordAnalyzer.getWordsOrderedAlphabetically();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void should_returnDistinctWordsContainingSubstring_when_invoked() {
        filePartReader.setFromLine(1);
        filePartReader.setToLine(7);

        List<String> expectedResult = Arrays.asList("walk", "Walking", "walkietalkie");
        List<String> actualResult = wordAnalyzer.getWordsContainingSubstring("walk");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void should_returnPalindromes_when_invoked() {
        filePartReader.setFromLine(1);
        filePartReader.setToLine(7);

        List<String> expectedResult = Arrays.asList("Wow", "Anna");
        List<String> actualResult = wordAnalyzer.getStringsWhichPalindromes();

        assertEquals(expectedResult, actualResult);
    }

    @AfterEach
    void tearDown() {
    }
}