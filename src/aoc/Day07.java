package aoc;

import java.util.*;

public class Day07 {

    public static class Node {

        private Node parent;
        private String name;
        private int size;
        private List <Node> files;
        private List <Node> directories;

        public Node(String name, Node parent){
            this.parent = parent;
            files = new ArrayList<>();
            directories = new ArrayList<>();

            if(name.contains("/")){
                this.name = "/";
                size = 0;

            } else if(name.startsWith("dir ")){
                this.name = name.split(" ")[1];
                size = 0;

            } else {
                this.name = name.split(" ")[1];
                this.size = Integer.parseInt(name.split(" ")[0]);
            }
        }

        public Node getParent() {
            return parent;
        }

        public String getName() {
            return name;
        }

        public void addFile(String name){
            files.add(new Node(name, this));
        }

        public void addDirectory(String name){
            directories.add(new Node(name, this));
        }

        public int getSize() {
            return size;
        }

        public int getTotalFileSize(){
            int totalSize = 0;
            for(Node file : files){
                totalSize += file.getSize();
            }

            if(!directories.isEmpty()){
                for (Node dir : directories) {
                    totalSize += dir.getTotalFileSize();
                }
            }

            return totalSize;
        }

        public Node traverseDirectory(String command){
            String instruction = command.split(" ")[1];

            if(instruction.equals("..")){
                return getParent();

            } else if (instruction.equals("/")) {

                if(parent == null){
                    return this;
                }

                return getParent().traverseDirectory(command);
            }

            for(Node director : directories){
                if(director.getName().equals(instruction)){
                    return director;
                }
            }

            return null;
        }

    }

    public static Node populateDirectory(String input){
        String [] commands = input.split("\\n|\\r\\n");
        Node root = new Node (commands[0].replace("$ ", ""),null);
        Node currentDirectory = root;

        for (int i = 1; i < commands.length; i++) {
            String instruction = commands[i];

            if(instruction.startsWith("$")){
                instruction =  instruction.replace("$ ", "");
            }

            if(instruction.startsWith("ls")) continue;

            if(instruction.startsWith("dir ")){
                currentDirectory.addDirectory(instruction);
                continue;
            }

            if(instruction.startsWith("cd")){
                currentDirectory = currentDirectory.traverseDirectory(instruction);
                continue;
            }

            currentDirectory.addFile(instruction);
        }

        return root;
    }

    public static int getTotalSize(String input) {
        return getSum(populateDirectory(input));
    }

    public static int getSum (Node root){
        int total = 0;
        int fileSize = root.getTotalFileSize();

        if(fileSize < 100000){
            total += fileSize;
        }

        if(root.directories != null){
            for (Node directory : root.directories){
                total += getSum(directory);
            }
        }

        return total;
    }

    public static int getDirectoryDeletionCandidate(String input){

        Node root = populateDirectory(input);
        int totalFileSpace = 70000000;
        int requiredSpace = 30000000;
        int candidate = 0;
        List<Integer> directorySizes = new ArrayList<>();

        directorySizes  = getSumOfDirectories(root, directorySizes);
        Collections.sort(directorySizes);
        int usedSpace = root.getTotalFileSize();;
        int currentFree = totalFileSpace - usedSpace;

        for(Integer size : directorySizes){
            if(currentFree + size >= requiredSpace){
                candidate = size;
                break;
            }
        }

        return candidate;
    }

    public static List<Integer> getSumOfDirectories(Node root, List<Integer> directorySizes){
        int fileSize = root.getTotalFileSize();
        directorySizes.add(fileSize);

        if(!root.directories.isEmpty()){
            for(Node directory: root.directories){
                getSumOfDirectories(directory,directorySizes);
            }

        }

        return directorySizes;
    }
}
