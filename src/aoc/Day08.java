package aoc;

import java.awt.*;
import java.util.ArrayList;


public class Day08 {

    public record Pair<Point, V>(Point key, boolean[] value){}

    public record ForestMap(ArrayList<ArrayList<Integer>> mapData){}

    public record TreeRange<Point, T>(Point key, int[] ranges){}
    
    public static ForestMap getForestMap(String input) {
        ForestMap map = new ForestMap(new ArrayList<>());
        String [] rows = input.split("\\n|\\r\\n");

        for (String s : rows) {
            ArrayList<Integer> row = new ArrayList<>();
            String[] convertedRow = s.split("");

            for (String string : convertedRow) {
                row.add(Integer.parseInt(string));
            }
            map.mapData().add(row);
        }

        return map;
    }

    public static ArrayList<Pair<Point,boolean[]>> calculateInnerTreeVisibility (String input){
        ForestMap forestMap = getForestMap(input);
        ArrayList<Pair<Point,boolean[]>> innerTreeVisibility = new ArrayList<>();

        for (int i = 1; i < forestMap.mapData.size() - 1; i++) {
            for (int j = 1; j < forestMap.mapData.get(i).size() - 1 ; j++) {

                //[UP, DOWN, LEFT, RIGHT]
                boolean [] canBeSeen = {true,true,true,true};

                //UP
                for (int k = i; k > 0; k--) {
                    if (forestMap.mapData.get(k - 1).get(j) >= forestMap.mapData.get(i).get(j)){
                        canBeSeen[0] = false;
                        break;
                    }
                }

                //DOWN
                for (int k = i + 1; k < forestMap.mapData.get(i).size(); k++) {
                    if (forestMap.mapData.get(k).get(j) >= forestMap.mapData.get(i).get(j)){
                        canBeSeen[1] = false;
                        break;
                    }
                }

                //LEFT
                for (int k = j; k > 0; k--) {
                    if (forestMap.mapData.get(i).get(k - 1) >= forestMap.mapData.get(i).get(j)){
                        canBeSeen[2] = false;
                        break;
                    }
                }

                //RIGHT
                for (int k = j + 1; k < forestMap.mapData.get(i).size(); k++) {
                    if (forestMap.mapData.get(i).get(k) >= forestMap.mapData.get(i).get(j)){
                        canBeSeen[3] = false;
                        break;
                    }
                }

                innerTreeVisibility.add(new Pair<>(new Point(i,j),canBeSeen));
            }
        }

        return innerTreeVisibility;
    }


    public static int calculateTotalTreeVisibility(String input){
        ForestMap forestMap = getForestMap(input);
        ArrayList<Pair<Point,boolean[]>> innerTreeVisibility = calculateInnerTreeVisibility(input);
        int total = (forestMap.mapData.size() * forestMap.mapData.size()) - innerTreeVisibility.size();

        for(Pair<Point,boolean[]> innerTree : innerTreeVisibility){
            for (int i = 0; i < innerTree.value.length; i++) {
                if(innerTree.value()[i]){
                    total++;
                    break;
                }
            }
        }

        return total;
    }

    public static ArrayList<TreeRange<Point,int[]>> calculateTreeRanges(String input) {
        ForestMap forestMap = getForestMap(input);
        ArrayList<TreeRange<Point,int[]>> innerTreeRange = new ArrayList<>();

        for (int i = 1; i < forestMap.mapData.size() - 1; i++) {
            for (int j = 1; j < forestMap.mapData.get(i).size() - 1 ; j++) {

                //[UP, DOWN, LEFT, RIGHT]
                int [] ranges = new int[4];

                //UP
                for (int k = i; k > 0; k--) {
                    ranges[0]++;
                    if (forestMap.mapData.get(k - 1).get(j) >= forestMap.mapData.get(i).get(j)){
                        break;
                    }

                }

                //DOWN
                for (int k = i + 1; k < forestMap.mapData.get(i).size(); k++) {
                    ranges[1]++;
                    if (forestMap.mapData.get(k).get(j) >= forestMap.mapData.get(i).get(j)){
                        break;
                    }

                }

                //LEFT
                for (int k = j; k > 0; k--) {
                    ranges[2]++;
                    if (forestMap.mapData.get(i).get(k - 1) >= forestMap.mapData.get(i).get(j)){
                        break;
                    }

                }

                //RIGHT
                for (int k = j + 1; k < forestMap.mapData.get(i).size(); k++) {
                    ranges[3]++;
                    if (forestMap.mapData.get(i).get(k) >= forestMap.mapData.get(i).get(j)){
                        break;
                    }

                }

                innerTreeRange.add(new TreeRange<>(new Point(i,j),ranges));
            }
        }

        return innerTreeRange;
    }

    public static int highestScenicScore(String input){
        ArrayList<TreeRange<Point,int[]>> innerTreeRange = calculateTreeRanges(input);
        int highestScore = 0;

        for (TreeRange tree : innerTreeRange){
            int treeScore = 1;
            for (int i = 0; i < tree.ranges().length; i++) {
                treeScore *= tree.ranges()[i];
            }
            if (treeScore > highestScore){
                highestScore = treeScore;
            }
        }

        return highestScore;
    }

}
