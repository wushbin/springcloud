package springcloud.locationservice;


import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class GeoHashUtils {

    private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final static Map<Character, Integer> DECODE_MAP = new HashMap<>();


    private static final int[] BITS = {16, 8, 4, 2, 1};
    private static final int PRECISION = 12;

    public GeoHashUtils() {
        for (int i = 0; i < 32; i+=1) {
            DECODE_MAP.put(BASE_32[i], i);
        }
    }

    public static String encode(double latitude, double longitude) {
        final double[] latInter = {-90.0, 90.0};
        final double[] longInter = {-180.0, 180.0};

        int bit = 0;
        int code = 0;

        final StringBuilder geoCode = new StringBuilder();
        boolean isEven = true;
        // even for longitude
        // odd for latitude


        while(geoCode.length() < PRECISION) {
            if(isEven) {
                double mid = (longInter[0] + longInter[1])/2.0;
                if (mid < longitude) {
                    code = code | BITS[bit];
                    longInter[0] = mid;
                } else {
                    longInter[1] = mid;
                }
            } else {
                double mid = (latInter[0] + latInter[1])/2.0;
                if (mid < latitude) {
                    code = code | BITS[bit];
                    latInter[1] = mid;
                } else {
                    latInter[0] = mid;
                }
            }
            if (bit < 4) {
                bit += 1;
            } else {
                geoCode.append(BASE_32[code]);
                bit = 0;
                code = 0;
            }
            isEven = !isEven;
        }
        return geoCode.toString();
    }


    public static double[] decode(String geoCode) {
        final double[] latInter = {-90.0, 90.0};
        final double[] longInter = {-180.0, 180.0};
        boolean isEven = true;
        for (int i = 0; i < geoCode.length(); i+=1) {
            final int code = DECODE_MAP.get(geoCode.charAt(i));
            for (int mask : BITS) {
                if (isEven) {
                    if ((code & mask) != 0) {
                        longInter[0] = (longInter[0] + longInter[1])/2.0;
                    } else {
                        longInter[1] = (longInter[0] + longInter[1])/2.0;
                    }
                } else {
                    if ((code & mask) != 0) {
                        latInter[0] = (latInter[0] + latInter[1])/2.0;
                    } else {
                        latInter[1] = (latInter[0] + latInter[1])/2.0;
                    }
                }
                isEven = !isEven;
            }
        }
        double latitude = (latInter[0] + latInter[1])/2.0;
        double longitude = (longInter[0] + longInter[1])/2.0;
        return new double[]{latitude, longitude};
    }



}
