import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Custom JUnit tests written to compute all .l files provided, to test the grammar (TP1)
 */
class CompilerTest {
    ArrayList<String> listOfFiles;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Stream<Path> walk = null;
        try {
            walk = Files.walk(Paths.get("C:\\Users\\Simon\\Documents\\L3 Informatique (Luminy - Växjö)" +
                    "\\Semestre 6" +
                    " - Luminy\\Compilation\\TP\\TP1\\compilationl3-public\\test\\input"));
        } catch (IOException e) {
            System.out.println("Please update tests path if you want to proceed to the automated tests");
        }
        listOfFiles = (ArrayList<String>) walk.filter(Files::isRegularFile)
                .map(Path::toString).collect(Collectors.toList());
    }

    @org.junit.jupiter.api.Test
    void TestLFiles() {
        for (String file : listOfFiles) {
            if (file.contains(".l")) {
                String[] args = new String[1];
                args[0] = file;
                Compiler.main(args);
            }
        }
    }
}