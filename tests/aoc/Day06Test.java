package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day06Test {

    public List<String> sampleInputs = Arrays.asList(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
    );


    @Test
    public void canGetStarterPacketMarker(){
        Assertions.assertEquals(7,Day06.getStarterPacketMarker(sampleInputs.get(0),1));
        Assertions.assertEquals(5,Day06.getStarterPacketMarker(sampleInputs.get(1),1));
        Assertions.assertEquals(6,Day06.getStarterPacketMarker(sampleInputs.get(2),1));
        Assertions.assertEquals(10,Day06.getStarterPacketMarker(sampleInputs.get(3), 1));
        Assertions.assertEquals(11,Day06.getStarterPacketMarker(sampleInputs.get(4), 1));
    }

    @Test
    public void getStarterPacketMarkerFromInput() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day06.txt"));
        int expected = 1702;
        Assertions.assertEquals(expected,Day06.getStarterPacketMarker(actualInput,1));

    }

    @Test
    public void canGetStarterPacketMarkerPartTwo(){
        Assertions.assertEquals(19,Day06.getStarterPacketMarker(sampleInputs.get(0),2));
        Assertions.assertEquals(23,Day06.getStarterPacketMarker(sampleInputs.get(1),2));
        Assertions.assertEquals(23,Day06.getStarterPacketMarker(sampleInputs.get(2),2));
        Assertions.assertEquals(29,Day06.getStarterPacketMarker(sampleInputs.get(3),2));
        Assertions.assertEquals(26,Day06.getStarterPacketMarker(sampleInputs.get(4),2));
    }

    @Test
    public void getStarterPacketMarkerFromInputPartTwo() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day06.txt"));
        int expected = 3559;
        Assertions.assertEquals(expected,Day06.getStarterPacketMarker(actualInput,2));

    }
}
