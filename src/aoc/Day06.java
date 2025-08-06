package aoc;

public class Day06 {

    public static int getStarterPacketMarker(String sampleInput, int mode){

        int packetSize;

        if (mode == 1) packetSize = 4;
        else packetSize = 14;

        for (int i = 0; i < sampleInput.length() - (packetSize-1); i++) {
            String packet = sampleInput.substring(i,i+ packetSize);
            boolean duplicate = false;

            for (int j = 0; j < packet.length(); j++){
                String letter = String.valueOf(packet.charAt(j));

                if(packet.indexOf(letter, packet.indexOf(letter) + 1) > -1){
                    duplicate = true;
                    break;
                }
            }

            if(!duplicate){
                return i + packetSize;
            }

        }
        return -1;
    }
}
