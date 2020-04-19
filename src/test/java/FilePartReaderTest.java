import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * These tests follow this naming convention: Should_ExpectedBehavior_When_StateUnderTest
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilePartReaderTest {
    static FilePartReader filePartReader;

    @BeforeAll
    static void should_createNewTestEnv_beforeAllTests() throws FileNotFoundException {
        filePartReader = new FilePartReader();
        filePartReader.setup("/home/klaudia/IdeaProjects/filepartreader-testing-with-junit-PhoenixWings7/src/test/resources/testFile.txt",
                1, 5);
        assertNotNull(filePartReader.file, "File is null!");
    }

    @Test
    @Order(1)
    // read the file first, then execute tests
    void should_readWholeFile_when_readingFile() throws IOException {
        String expectedContent = "A child asked his father, \"How were people born?\" So his father said, " +
                "\"Adam and Eve made babies, then their babies became adults and made babies, and so on.\"\n" +
                "The child then went to his mother, asked her the same question and she told him, " +
                "\"We were monkeys then we evolved to become like we are now.\"\n" +
                "The child ran back to his father and said, \"You lied to me!\"\n" +
                "His father replied, \"No, your mom was talking about her side of the family.\"";
        String actualContent = filePartReader.read();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    void should_throwIOException_when_() {
        // TODO: figure out how to make read() throw exception
        assertThrows(IOException.class, () -> filePartReader.read());
    }

    @Test
    void should_readFirstLine_when_readingLines() {
        filePartReader.setFromLine(1);
        filePartReader.setToLine(1);
        String firstLine = "A child asked his father, \"How were people born?\" So his father said, " +
                "\"Adam and Eve made babies, then their babies became adults and made babies, and so on.\"";

        assertEquals(firstLine, filePartReader.readLines());
    }

    @Test
    void should_readCorrectLines_when_readingLines() {
        filePartReader.setFromLine(2);
        filePartReader.setToLine(3);

        String expextedText = "The child then went to his mother, asked her the same question and she told him, \"" +
                "We were monkeys then we evolved to become like we are now.\"\n" +
                "The child ran back to his father and said, \"You lied to me!\"";
        String actualText = filePartReader.readLines();

        assertEquals(expextedText, actualText);
    }

    @Test
    void should_throwException_when_fromLineLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("", -1, 9));
    }

    @Test
    void should_throwException_when_toLineLessThanFromLine() {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("", 6, 2));
    }
}