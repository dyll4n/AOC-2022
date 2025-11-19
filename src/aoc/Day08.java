package aoc;

import java.awt.*;
import java.util.ArrayList;


public class Day08 {

    public record Pair<Point, V>(Point key, boolean[] value){}

    public record ForestMap(ArrayList<ArrayList<Integer>> mapData){}
    
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
}
