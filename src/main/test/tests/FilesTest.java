package tests;

import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilesTest {

    @Test
    void fileRead_GivenNullPath_ThrowNullPointerException_True() throws IOException, MyExceptions.FileFormatException {
        Assertions.assertThrows(NullPointerException.class, () -> Files.fileRead(null));
    }
    @Test
    void fileRead_GivenBadFormatFile_ThrowFileFormatException_True() throws IOException, MyExceptions.FileFormatException {
        Assertions.assertThrows(MyExceptions.FileFormatException.class, () -> Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestuNieKratka.txt"));
    }
    @Test
    void fileRead_GivenBadFormatFile_NumberFormatException_True() throws IOException, MyExceptions.FileFormatException {
        Assertions.assertThrows(NumberFormatException.class, () -> Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestuZlyFormat.txt"));
    }


    @Test
    void fileCreate_GivenNullPath_ThrowNullPointerException_True() throws IOException{
        Assertions.assertThrows(NullPointerException.class, () -> Files.fileCreate(null));
    }
}