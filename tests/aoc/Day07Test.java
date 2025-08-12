package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day07Test {

    public String  sampleInput = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """;

    @Test
    public void canGetTotalSize(){
        Assertions.assertEquals(95437, Day07.getTotalSize(sampleInput));
    }

    @Test
    public void canGetTotalSizePartOne() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day07.txt"));
        Assertions.assertEquals(1449447, Day07.getTotalSize(actualInput));
    }

    @Test
    public void canGetSmallestDirSizeToDelete(){
        Assertions.assertEquals(24933642, Day07.getDirectoryDeletionCandidate(sampleInput));
    }

    @Test
    public void canGetSmallestDirSizeToDeletePartTwo() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day07.txt"));
        Assertions.assertEquals(8679207, Day07.getDirectoryDeletionCandidate(actualInput));
    }
}
