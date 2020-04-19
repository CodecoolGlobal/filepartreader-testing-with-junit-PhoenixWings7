import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class FilePartReader {
    File file;
    FileReader fileReader;
    String fileContent = "";
    Integer fromLine = 1;
    Integer toLine = 1;
    String[] lines;

    public void setup(String filePath, Integer fromLine, Integer toLine) throws FileNotFoundException, IllegalArgumentException {
        if ((toLine < fromLine) || (fromLine < 1)) {
            throw new IllegalArgumentException();
        }

        this.file = new File(filePath);
        this.fileReader = new FileReader(file);
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        if (file != null) {
            this.fileContent = Files.readString(file.toPath());
        }
        return fileContent;
    }

    public String readLines() {
        if (lines == null) {
            lines = fileContent.split("\n");
        }
        StringBuilder linesSB = new StringBuilder();
        int lineNum = fromLine;
        while (lineNum <= toLine && toLine <= lines.length) {
            linesSB.append(lines[lineNum-1]);
            if (lineNum != toLine) {
                linesSB.append("\n");
            }
            lineNum++;
        }

        return linesSB.toString();
    }

    public void setFromLine(Integer fromLine) {
        this.fromLine = fromLine;
    }

    public void setToLine(Integer toLine) {
        this.toLine = toLine;
    }
}
