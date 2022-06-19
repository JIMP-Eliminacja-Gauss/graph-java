package tests;

import com.company.graphjava.Main;
import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Edge;
import com.company.graphjava.graph.Files;
import com.company.graphjava.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

public class GraphTest {
    @Test
    void getNeighboursIterator_GivenNull_ReturnEmptyIterator() {
        Graph graph = new Graph(2, 2, 1, 10, 0);
        Iterator<Edge> e = graph.getNeighboursIterator(0);
        Assertions.assertEquals(e, Collections.emptyIterator());
    }

    @Test
    void maxEdgeValue_GivenMaxValue10_Return10()
            throws IOException, MyExceptions.FileFormatException {
        String path = "src/main/resources/com/company/graphjava/textfiles/plikDoTestuMax10Min1.txt";
        Files.fileRead(path);
        Graph graph = Main.getGraph();
        Assertions.assertEquals(10, graph.maxEdgeValue());
    }

    @Test
    void minEdgeValue_GivenMinValue1_Return1()
            throws IOException, MyExceptions.FileFormatException {
        String path = "src/main/resources/com/company/graphjava/textfiles/plikDoTestuMax10Min1.txt";
        Files.fileRead(path);
        Graph graph = Main.getGraph();
        Assertions.assertEquals(1, graph.minEdgeValue());
    }
}
