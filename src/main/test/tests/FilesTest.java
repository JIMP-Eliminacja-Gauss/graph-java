package tests;

import com.company.graphjava.Main;
import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Edge;
import com.company.graphjava.graph.Files;
import com.company.graphjava.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

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

    @Test
    void fileRead_TestFile() throws IOException, MyExceptions.FileFormatException {
        Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestu.txt");
        Graph graph = Main.getGraph();
        assertEquals(3, graph.getColumns());
        assertEquals(3, graph.getRows());

        Iterator<Edge> iterator;
        iterator = graph.getNeighboursIterator(0);

        Edge edge = iterator.next();
        assertEquals(5, edge.getWeight());
        assertEquals(1, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(3, edge.getIndex());

        iterator = graph.getNeighboursIterator(1);
        edge = iterator.next();
        assertEquals(5, edge.getWeight());
        assertEquals(0, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(2, edge.getIndex());
        edge = iterator.next();
        assertEquals(2, edge.getWeight());
        assertEquals(4, edge.getIndex());

        iterator = graph.getNeighboursIterator(2);
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(1, edge.getIndex());
        edge = iterator.next();
        assertEquals(6, edge.getWeight());
        assertEquals(5, edge.getIndex());

        iterator = graph.getNeighboursIterator(3);
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(0, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(4, edge.getIndex());
        edge = iterator.next();
        assertEquals(3, edge.getWeight());
        assertEquals(6, edge.getIndex());

        iterator = graph.getNeighboursIterator(4);
        edge = iterator.next();
        assertEquals(2, edge.getWeight());
        assertEquals(1, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(3, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(5, edge.getIndex());
        edge = iterator.next();
        assertEquals(10, edge.getWeight());
        assertEquals(7, edge.getIndex());

        iterator = graph.getNeighboursIterator(5);
        edge = iterator.next();
        assertEquals(6, edge.getWeight());
        assertEquals(2, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(4, edge.getIndex());
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(8, edge.getIndex());

        iterator = graph.getNeighboursIterator(6);
        edge = iterator.next();
        assertEquals(3, edge.getWeight());
        assertEquals(3, edge.getIndex());
        edge = iterator.next();
        assertEquals(2, edge.getWeight());
        assertEquals(7, edge.getIndex());

        iterator = graph.getNeighboursIterator(7);
        edge = iterator.next();
        assertEquals(2, edge.getWeight());
        assertEquals(6, edge.getIndex());
        edge = iterator.next();
        assertEquals(10, edge.getWeight());
        assertEquals(4, edge.getIndex());
        edge = iterator.next();
        assertEquals(8, edge.getWeight());
        assertEquals(8, edge.getIndex());

        iterator = graph.getNeighboursIterator(8);
        edge = iterator.next();
        assertEquals(1, edge.getWeight());
        assertEquals(5, edge.getIndex());
        edge = iterator.next();
        assertEquals(8, edge.getWeight());
        assertEquals(7, edge.getIndex());

    }
}