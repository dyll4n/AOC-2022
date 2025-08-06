package aoc;

public class Day06 {

    public static int getStarterPacketMarker(String sampleInput){

        for (int i = 0; i < sampleInput.length() - 3; i++) {
            String packet = sampleInput.substring(i,i+4);
            boolean duplicate = false;

            for (int j = 0; j < packet.length(); j++){
                String letter = String.valueOf(packet.charAt(j));

                if(packet.indexOf(letter, packet.indexOf(letter) + 1) > -1){
                    duplicate = true;
                    break;
                }
            }

            if(!duplicate){
                return i + 4;
            }

        }
        return -1;
    }
}
