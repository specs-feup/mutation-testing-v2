package pt.up.fe.specs.mutation.benchmarkV2.tests;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Best alterative: switch + static + final + int
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class TestIfElse {

    private final static String DEFAULT_MUID = "1";

    private final static boolean IS_ANDROID = false;

    private final static int MUID_FIRST = 1;

    private final static int MUID_DEFAULT = -1;

    private static String getProp() {
        if(IS_ANDROID) {
            String propertyValue = null;
            try {
                Process process = Runtime.getRuntime().exec("getprop debug.MUID");
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                propertyValue = reader.readLine();
                reader.close();
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return propertyValue;
        } else {
            return System.getProperty("debug.MUID");
        }
    }

    private static void setProp() {
        if(IS_ANDROID) {
            try {
                Process process = Runtime.getRuntime().exec("setprop debug.MUID " + DEFAULT_MUID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Setup property
            System.setProperty("debug.MUID", DEFAULT_MUID);
        }
    }


//    static {
//        System.setProperty("MUID", "ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
//    }


    @Param({ "1000" })
    public int iterations;

//    @Setup
//    public void setup() {
//        // Initialization code that should run before the benchmark
//        setProp();
//    }

    @Benchmark
    public void noMutations(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            count += i;
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void mutatedFirst(Blackhole blackhole) {
        long  count = 0;
        int MUID = MUID_FIRST;
        for(int i=0; i<iterations; i++) {
            if(MUID == 1) {
                count += 1;
            }
            else if(MUID == 2) {
                count +=2;
            }
            else if(MUID == 3) {
                count +=3;
            }
            else if(MUID == 4) {
                count +=4;
            }
            else if(MUID == 5) {
                count +=5;
            }
            else if(MUID == 6) {
                count +=6;
            }
            else if(MUID == 7) {
                count +=7;
            }
            else if(MUID == 8) {
                count +=8;
            }
            else if(MUID == 9) {
                count +=9;
            }
            else if(MUID == 10) {
                count +=10;
            }
            else if(MUID == 11) {
                count +=11;
            }
            else if(MUID == 12) {
                count +=12;
            }
            else if(MUID == 13) {
                count +=13;
            }
            else if(MUID == 14) {
                count +=14;
            }
            else if(MUID == 15) {
                count +=15;
            }
            else if(MUID == 16) {
                count +=16;
            }
            else if(MUID == 17) {
                count +=17;
            }
            else if(MUID == 18) {
                count +=18;
            }
            else if(MUID == 19) {
                count +=19;
            }
            else if(MUID == 20) {
                count +=20;
            }
            else if(MUID == 21) {
                count +=21;
            }
            else if(MUID == 22) {
                count +=22;
            }
            else if(MUID == 23) {
                count +=23;
            }
            else if(MUID == 24) {
                count +=24;
            }
            else if(MUID == 25) {
                count +=25;
            }
            else if(MUID == 26) {
                count +=26;
            }
            else if(MUID == 27) {
                count +=27;
            }
            else if(MUID == 28) {
                count +=28;
            }
            else if(MUID == 29) {
                count +=29;
            }
            else if(MUID == 30) {
                count +=30;
            }
            else if(MUID == 31) {
                count +=31;
            }
            else if(MUID == 32) {
                count +=32;
            }
            else if(MUID == 33) {
                count +=33;
            }
            else if(MUID == 34) {
                count +=34;
            }
            else if(MUID == 35) {
                count +=35;
            }
            else if(MUID == 36) {
                count +=36;
            }
            else if(MUID == 37) {
                count +=37;
            }
            else if(MUID == 38) {
                count +=38;
            }
            else if(MUID == 39) {
                count +=39;
            }
            else if(MUID == 40) {
                count +=40;
            }
            else if(MUID == 41) {
                count +=41;
            }
            else if(MUID == 42) {
                count +=42;
            }
            else if(MUID == 43) {
                count +=43;
            }
            else if(MUID == 44) {
                count +=44;
            }
            else if(MUID == 45) {
                count +=45;
            }
            else if(MUID == 46) {
                count +=46;
            }
            else if(MUID == 47) {
                count +=47;
            }
            else if(MUID == 48) {
                count +=48;
            }
            else if(MUID == 49) {
                count +=49;
            }
            else if(MUID == 50) {
                count +=50;
            }
            else if(MUID == 51) {
                count +=51;
            }
            else if(MUID == 52) {
                count +=52;
            }
            else if(MUID == 53) {
                count +=53;
            }
            else if(MUID == 54) {
                count +=54;
            }
            else if(MUID == 55) {
                count +=55;
            }
            else if(MUID == 56) {
                count +=56;
            }
            else if(MUID == 57) {
                count +=57;
            }
            else if(MUID == 58) {
                count +=58;
            }
            else if(MUID == 59) {
                count +=59;
            }
            else if(MUID == 60) {
                count +=60;
            }
            else if(MUID == 61) {
                count +=61;
            }
            else if(MUID == 62) {
                count +=62;
            }
            else if(MUID == 63) {
                count +=63;
            }
            else if(MUID == 64) {
                count +=64;
            }
            else if(MUID == 65) {
                count +=65;
            }
            else if(MUID == 66) {
                count +=66;
            }
            else if(MUID == 67) {
                count +=67;
            }
            else if(MUID == 68) {
                count +=68;
            }
            else if(MUID == 69) {
                count +=69;
            }
            else if(MUID == 70) {
                count +=70;
            }
            else if(MUID == 71) {
                count +=71;
            }
            else if(MUID == 72) {
                count +=72;
            }
            else if(MUID == 73) {
                count +=73;
            }
            else if(MUID == 74) {
                count +=74;
            }
            else if(MUID == 75) {
                count +=75;
            }
            else if(MUID == 76) {
                count +=76;
            }
            else if(MUID == 77) {
                count +=77;
            }
            else if(MUID == 78) {
                count +=78;
            }
            else if(MUID == 79) {
                count +=79;
            }
            else if(MUID == 80) {
                count +=80;
            }
            else if(MUID == 81) {
                count +=81;
            }
            else if(MUID == 82) {
                count +=82;
            }
            else if(MUID == 83) {
                count +=83;
            }
            else if(MUID == 84) {
                count +=84;
            }
            else if(MUID == 85) {
                count +=85;
            }
            else if(MUID == 86) {
                count +=86;
            }
            else if(MUID == 87) {
                count +=87;
            }
            else if(MUID == 88) {
                count +=88;
            }
            else if(MUID == 89) {
                count +=89;
            }
            else if(MUID == 90) {
                count +=90;
            }
            else if(MUID == 91) {
                count +=91;
            }
            else if(MUID == 92) {
                count +=92;
            }
            else if(MUID == 93) {
                count +=93;
            }
            else if(MUID == 94) {
                count +=94;
            }
            else if(MUID == 95) {
                count +=95;
            }
            else if(MUID == 96) {
                count +=96;
            }
            else if(MUID == 97) {
                count +=97;
            }
            else if(MUID == 98) {
                count +=98;
            }
            else if(MUID == 99) {
                count +=99;
            }
            else if(MUID == 100) {
                count +=100;
            }
            else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void mutatedDefault(Blackhole blackhole) {
        long  count = 0;
        int MUID = MUID_DEFAULT;
        for(int i=0; i<iterations; i++) {
            if(MUID == 1) {
                count += 1;
            }
            else if(MUID == 2) {
                count +=2;
            }
            else if(MUID == 3) {
                count +=3;
            }
            else if(MUID == 4) {
                count +=4;
            }
            else if(MUID == 5) {
                count +=5;
            }
            else if(MUID == 6) {
                count +=6;
            }
            else if(MUID == 7) {
                count +=7;
            }
            else if(MUID == 8) {
                count +=8;
            }
            else if(MUID == 9) {
                count +=9;
            }
            else if(MUID == 10) {
                count +=10;
            }
            else if(MUID == 11) {
                count +=11;
            }
            else if(MUID == 12) {
                count +=12;
            }
            else if(MUID == 13) {
                count +=13;
            }
            else if(MUID == 14) {
                count +=14;
            }
            else if(MUID == 15) {
                count +=15;
            }
            else if(MUID == 16) {
                count +=16;
            }
            else if(MUID == 17) {
                count +=17;
            }
            else if(MUID == 18) {
                count +=18;
            }
            else if(MUID == 19) {
                count +=19;
            }
            else if(MUID == 20) {
                count +=20;
            }
            else if(MUID == 21) {
                count +=21;
            }
            else if(MUID == 22) {
                count +=22;
            }
            else if(MUID == 23) {
                count +=23;
            }
            else if(MUID == 24) {
                count +=24;
            }
            else if(MUID == 25) {
                count +=25;
            }
            else if(MUID == 26) {
                count +=26;
            }
            else if(MUID == 27) {
                count +=27;
            }
            else if(MUID == 28) {
                count +=28;
            }
            else if(MUID == 29) {
                count +=29;
            }
            else if(MUID == 30) {
                count +=30;
            }
            else if(MUID == 31) {
                count +=31;
            }
            else if(MUID == 32) {
                count +=32;
            }
            else if(MUID == 33) {
                count +=33;
            }
            else if(MUID == 34) {
                count +=34;
            }
            else if(MUID == 35) {
                count +=35;
            }
            else if(MUID == 36) {
                count +=36;
            }
            else if(MUID == 37) {
                count +=37;
            }
            else if(MUID == 38) {
                count +=38;
            }
            else if(MUID == 39) {
                count +=39;
            }
            else if(MUID == 40) {
                count +=40;
            }
            else if(MUID == 41) {
                count +=41;
            }
            else if(MUID == 42) {
                count +=42;
            }
            else if(MUID == 43) {
                count +=43;
            }
            else if(MUID == 44) {
                count +=44;
            }
            else if(MUID == 45) {
                count +=45;
            }
            else if(MUID == 46) {
                count +=46;
            }
            else if(MUID == 47) {
                count +=47;
            }
            else if(MUID == 48) {
                count +=48;
            }
            else if(MUID == 49) {
                count +=49;
            }
            else if(MUID == 50) {
                count +=50;
            }
            else if(MUID == 51) {
                count +=51;
            }
            else if(MUID == 52) {
                count +=52;
            }
            else if(MUID == 53) {
                count +=53;
            }
            else if(MUID == 54) {
                count +=54;
            }
            else if(MUID == 55) {
                count +=55;
            }
            else if(MUID == 56) {
                count +=56;
            }
            else if(MUID == 57) {
                count +=57;
            }
            else if(MUID == 58) {
                count +=58;
            }
            else if(MUID == 59) {
                count +=59;
            }
            else if(MUID == 60) {
                count +=60;
            }
            else if(MUID == 61) {
                count +=61;
            }
            else if(MUID == 62) {
                count +=62;
            }
            else if(MUID == 63) {
                count +=63;
            }
            else if(MUID == 64) {
                count +=64;
            }
            else if(MUID == 65) {
                count +=65;
            }
            else if(MUID == 66) {
                count +=66;
            }
            else if(MUID == 67) {
                count +=67;
            }
            else if(MUID == 68) {
                count +=68;
            }
            else if(MUID == 69) {
                count +=69;
            }
            else if(MUID == 70) {
                count +=70;
            }
            else if(MUID == 71) {
                count +=71;
            }
            else if(MUID == 72) {
                count +=72;
            }
            else if(MUID == 73) {
                count +=73;
            }
            else if(MUID == 74) {
                count +=74;
            }
            else if(MUID == 75) {
                count +=75;
            }
            else if(MUID == 76) {
                count +=76;
            }
            else if(MUID == 77) {
                count +=77;
            }
            else if(MUID == 78) {
                count +=78;
            }
            else if(MUID == 79) {
                count +=79;
            }
            else if(MUID == 80) {
                count +=80;
            }
            else if(MUID == 81) {
                count +=81;
            }
            else if(MUID == 82) {
                count +=82;
            }
            else if(MUID == 83) {
                count +=83;
            }
            else if(MUID == 84) {
                count +=84;
            }
            else if(MUID == 85) {
                count +=85;
            }
            else if(MUID == 86) {
                count +=86;
            }
            else if(MUID == 87) {
                count +=87;
            }
            else if(MUID == 88) {
                count +=88;
            }
            else if(MUID == 89) {
                count +=89;
            }
            else if(MUID == 90) {
                count +=90;
            }
            else if(MUID == 91) {
                count +=91;
            }
            else if(MUID == 92) {
                count +=92;
            }
            else if(MUID == 93) {
                count +=93;
            }
            else if(MUID == 94) {
                count +=94;
            }
            else if(MUID == 95) {
                count +=95;
            }
            else if(MUID == 96) {
                count +=96;
            }
            else if(MUID == 97) {
                count +=97;
            }
            else if(MUID == 98) {
                count +=98;
            }
            else if(MUID == 99) {
                count +=99;
            }
            else if(MUID == 100) {
                count +=100;
            }
            else {
                count += i;
            }

        }

        blackhole.consume(count);
    }



}