package tests;

import com.company.graphjava.Main;
import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Algorithm;
import com.company.graphjava.graph.Files;
import com.company.graphjava.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AlgorithmTest {
    @Test
    void bfs_GivenNotConnectedGraph_ReturnFalse()
            throws IOException, MyExceptions.FileFormatException {
        String path = "src/main/resources/com/company/graphjava/textfiles/plikDoTestuNiespojny.txt";
        Files.fileRead(path);
        Graph graph = Main.getGraph();
        Assertions.assertFalse(Algorithm.bfs(graph));
    }

    @Test
    void bfs_GivenConnectedGraph_ReturnTrue()
            throws IOException, MyExceptions.FileFormatException {
        String path = "src/main/resources/com/company/graphjava/textfiles/plikDoTestu.txt";
        Files.fileRead(path);
        Graph graph = Main.getGraph();
        Assertions.assertTrue(Algorithm.bfs(graph));
    }


}
