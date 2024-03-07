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
public class TestString {

    private final static String DEFAULT_MUID = "1";

    private final static boolean IS_ANDROID = false;

    private final static String MUID_FIRST = "ae7f9682-6bd8-45e8-a3da-4020ccc153fa";

    private final static String MUID_DEFAULT = "ae7f9682-6bd8-45e8-a3da-4020ccc153fa_dummy";

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
        for(int i=0; i<iterations; i++) {
            switch(MUID_FIRST) {
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa":
                    count += 1;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa1":
                    count += 2;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa2":
                    count += 3;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa3":
                    count += 4;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa4":
                    count += 5;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa5":
                    count += 6;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa6":
                    count += 7;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa7":
                    count += 8;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa8":
                    count += 9;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa9":
                    count += 10;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa10":
                    count += 11;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa11":
                    count += 12;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa12":
                    count += 13;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa13":
                    count += 14;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa14":
                    count += 15;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa15":
                    count += 16;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa16":
                    count += 17;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa17":
                    count += 18;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa18":
                    count += 19;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa19":
                    count += 20;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa20":
                    count += 21;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa21":
                    count += 22;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa22":
                    count += 23;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa23":
                    count += 24;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa24":
                    count += 25;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa25":
                    count += 26;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa26":
                    count += 27;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa27":
                    count += 28;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa28":
                    count += 29;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa29":
                    count += 30;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa30":
                    count += 31;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa31":
                    count += 32;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa32":
                    count += 33;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa33":
                    count += 34;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa34":
                    count += 35;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa35":
                    count += 36;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa36":
                    count += 37;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa37":
                    count += 38;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa38":
                    count += 39;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa39":
                    count += 40;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa40":
                    count += 41;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa41":
                    count += 42;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa42":
                    count += 43;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa43":
                    count += 44;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa44":
                    count += 45;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa45":
                    count += 46;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa46":
                    count += 47;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa47":
                    count += 48;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa48":
                    count += 49;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa49":
                    count += 50;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa50":
                    count += 51;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa51":
                    count += 52;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa52":
                    count += 53;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa53":
                    count += 54;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa54":
                    count += 55;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa55":
                    count += 56;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa56":
                    count += 57;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa57":
                    count += 58;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa58":
                    count += 59;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa59":
                    count += 60;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa60":
                    count += 61;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa61":
                    count += 62;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa62":
                    count += 63;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa63":
                    count += 64;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa64":
                    count += 65;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa65":
                    count += 66;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa66":
                    count += 67;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa67":
                    count += 68;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa68":
                    count += 69;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa69":
                    count += 70;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa70":
                    count += 71;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa71":
                    count += 72;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa72":
                    count += 73;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa73":
                    count += 74;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa74":
                    count += 75;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa75":
                    count += 76;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa76":
                    count += 77;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa77":
                    count += 78;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa78":
                    count += 79;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa79":
                    count += 80;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa80":
                    count += 81;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa81":
                    count += 82;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa82":
                    count += 83;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa83":
                    count += 84;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa84":
                    count += 85;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa85":
                    count += 86;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa86":
                    count += 87;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa87":
                    count += 88;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa88":
                    count += 89;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa89":
                    count += 90;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa90":
                    count += 91;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa91":
                    count += 92;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa92":
                    count += 93;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa93":
                    count += 94;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa94":
                    count += 95;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa95":
                    count += 96;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa96":
                    count += 97;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa97":
                    count += 98;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa98":
                    count += 99;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa99":
                    count += 100;
                    break;
                default:
                    count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void mutatedDefault(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            switch(MUID_DEFAULT) {
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa":
                    count += 1;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa1":
                    count += 2;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa2":
                    count += 3;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa3":
                    count += 4;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa4":
                    count += 5;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa5":
                    count += 6;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa6":
                    count += 7;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa7":
                    count += 8;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa8":
                    count += 9;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa9":
                    count += 10;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa10":
                    count += 11;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa11":
                    count += 12;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa12":
                    count += 13;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa13":
                    count += 14;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa14":
                    count += 15;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa15":
                    count += 16;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa16":
                    count += 17;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa17":
                    count += 18;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa18":
                    count += 19;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa19":
                    count += 20;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa20":
                    count += 21;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa21":
                    count += 22;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa22":
                    count += 23;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa23":
                    count += 24;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa24":
                    count += 25;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa25":
                    count += 26;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa26":
                    count += 27;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa27":
                    count += 28;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa28":
                    count += 29;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa29":
                    count += 30;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa30":
                    count += 31;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa31":
                    count += 32;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa32":
                    count += 33;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa33":
                    count += 34;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa34":
                    count += 35;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa35":
                    count += 36;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa36":
                    count += 37;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa37":
                    count += 38;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa38":
                    count += 39;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa39":
                    count += 40;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa40":
                    count += 41;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa41":
                    count += 42;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa42":
                    count += 43;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa43":
                    count += 44;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa44":
                    count += 45;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa45":
                    count += 46;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa46":
                    count += 47;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa47":
                    count += 48;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa48":
                    count += 49;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa49":
                    count += 50;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa50":
                    count += 51;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa51":
                    count += 52;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa52":
                    count += 53;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa53":
                    count += 54;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa54":
                    count += 55;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa55":
                    count += 56;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa56":
                    count += 57;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa57":
                    count += 58;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa58":
                    count += 59;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa59":
                    count += 60;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa60":
                    count += 61;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa61":
                    count += 62;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa62":
                    count += 63;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa63":
                    count += 64;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa64":
                    count += 65;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa65":
                    count += 66;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa66":
                    count += 67;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa67":
                    count += 68;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa68":
                    count += 69;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa69":
                    count += 70;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa70":
                    count += 71;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa71":
                    count += 72;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa72":
                    count += 73;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa73":
                    count += 74;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa74":
                    count += 75;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa75":
                    count += 76;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa76":
                    count += 77;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa77":
                    count += 78;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa78":
                    count += 79;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa79":
                    count += 80;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa80":
                    count += 81;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa81":
                    count += 82;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa82":
                    count += 83;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa83":
                    count += 84;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa84":
                    count += 85;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa85":
                    count += 86;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa86":
                    count += 87;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa87":
                    count += 88;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa88":
                    count += 89;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa89":
                    count += 90;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa90":
                    count += 91;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa91":
                    count += 92;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa92":
                    count += 93;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa93":
                    count += 94;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa94":
                    count += 95;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa95":
                    count += 96;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa96":
                    count += 97;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa97":
                    count += 98;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa98":
                    count += 99;
                    break;
                case "ae7f9682-6bd8-45e8-a3da-4020ccc153fa99":
                    count += 100;
                    break;
                default:
                    count += i;
            }

        }

        blackhole.consume(count);
    }



}