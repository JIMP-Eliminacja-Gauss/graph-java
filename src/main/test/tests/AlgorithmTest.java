package tests;

import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Algorithm;
import com.company.graphjava.graph.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AlgorithmTest {


    @Test
    void dijkstra() throws IOException, MyExceptions.FileFormatException {
        Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestu.txt");
        Algorithm.dijkstra(0);
        Assertions.assertEquals(3, Algorithm.getShortestPath(5));
        Assertions.assertEquals(4, Algorithm.getPreviousVertex(5));
    }

    @Test
    void dijkstra_NotConnectivityGraph() throws IOException, MyExceptions.FileFormatException {
        Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestuNiespojny.txt");
        Algorithm.dijkstra(0);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, Algorithm.getShortestPath(2));
        Assertions.assertEquals(0, Algorithm.getPreviousVertex(2));
    }

    @Test
    void dijkstra_1x1Graph_ReturnNull() throws IOException, MyExceptions.FileFormatException {
        Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestuGraf1x1.txt");
        Integer result = Algorithm.dijkstra(0);
        Assertions.assertEquals(null, result);
    }

    @Test
    void dijkstra_EdgeWeightLessThan0_Return1() throws IOException, MyExceptions.FileFormatException {
        Files.fileRead("src/main/resources/com/company/graphjava/textfiles/plikDoTestuUjemneWagiKrawedzi.txt");
        int result = Algorithm.dijkstra(0);
        Assertions.assertEquals(1, result);
    }

}

