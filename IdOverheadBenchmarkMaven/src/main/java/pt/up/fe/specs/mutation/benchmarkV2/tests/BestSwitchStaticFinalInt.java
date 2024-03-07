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
public class BestSwitchStaticFinalInt {

    private final static String DEFAULT_MUID = "1";

    private final static boolean IS_ANDROID = false;

    private final static int MUID_FIRST = 1;

    private final static int MUID_DEFAULT = -1;

    private static String getProp() {
        if(IS_ANDROID) {
            String propertyValue = null;
            try {
                java.lang.Process process = Runtime.getRuntime().exec("getprop debug.MUID");
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
                case 1:
                    count += 1;
                    break;
                case 2:
                    count += 2;
                    break;
                case 3:
                    count += 3;
                    break;
                case 4:
                    count += 4;
                    break;
                case 5:
                    count += 5;
                    break;
                case 6:
                    count += 6;
                    break;
                case 7:
                    count += 7;
                    break;
                case 8:
                    count += 8;
                    break;
                case 9:
                    count += 9;
                    break;
                case 10:
                    count += 10;
                    break;
                case 11:
                    count += 11;
                    break;
                case 12:
                    count += 12;
                    break;
                case 13:
                    count += 13;
                    break;
                case 14:
                    count += 14;
                    break;
                case 15:
                    count += 15;
                    break;
                case 16:
                    count += 16;
                    break;
                case 17:
                    count += 17;
                    break;
                case 18:
                    count += 18;
                    break;
                case 19:
                    count += 19;
                    break;
                case 20:
                    count += 20;
                    break;
                case 21:
                    count += 21;
                    break;
                case 22:
                    count += 22;
                    break;
                case 23:
                    count += 23;
                    break;
                case 24:
                    count += 24;
                    break;
                case 25:
                    count += 25;
                    break;
                case 26:
                    count += 26;
                    break;
                case 27:
                    count += 27;
                    break;
                case 28:
                    count += 28;
                    break;
                case 29:
                    count += 29;
                    break;
                case 30:
                    count += 30;
                    break;
                case 31:
                    count += 31;
                    break;
                case 32:
                    count += 32;
                    break;
                case 33:
                    count += 33;
                    break;
                case 34:
                    count += 34;
                    break;
                case 35:
                    count += 35;
                    break;
                case 36:
                    count += 36;
                    break;
                case 37:
                    count += 37;
                    break;
                case 38:
                    count += 38;
                    break;
                case 39:
                    count += 39;
                    break;
                case 40:
                    count += 40;
                    break;
                case 41:
                    count += 41;
                    break;
                case 42:
                    count += 42;
                    break;
                case 43:
                    count += 43;
                    break;
                case 44:
                    count += 44;
                    break;
                case 45:
                    count += 45;
                    break;
                case 46:
                    count += 46;
                    break;
                case 47:
                    count += 47;
                    break;
                case 48:
                    count += 48;
                    break;
                case 49:
                    count += 49;
                    break;
                case 50:
                    count += 50;
                    break;
                case 51:
                    count += 51;
                    break;
                case 52:
                    count += 52;
                    break;
                case 53:
                    count += 53;
                    break;
                case 54:
                    count += 54;
                    break;
                case 55:
                    count += 55;
                    break;
                case 56:
                    count += 56;
                    break;
                case 57:
                    count += 57;
                    break;
                case 58:
                    count += 58;
                    break;
                case 59:
                    count += 59;
                    break;
                case 60:
                    count += 60;
                    break;
                case 61:
                    count += 61;
                    break;
                case 62:
                    count += 62;
                    break;
                case 63:
                    count += 63;
                    break;
                case 64:
                    count += 64;
                    break;
                case 65:
                    count += 65;
                    break;
                case 66:
                    count += 66;
                    break;
                case 67:
                    count += 67;
                    break;
                case 68:
                    count += 68;
                    break;
                case 69:
                    count += 69;
                    break;
                case 70:
                    count += 70;
                    break;
                case 71:
                    count += 71;
                    break;
                case 72:
                    count += 72;
                    break;
                case 73:
                    count += 73;
                    break;
                case 74:
                    count += 74;
                    break;
                case 75:
                    count += 75;
                    break;
                case 76:
                    count += 76;
                    break;
                case 77:
                    count += 77;
                    break;
                case 78:
                    count += 78;
                    break;
                case 79:
                    count += 79;
                    break;
                case 80:
                    count += 80;
                    break;
                case 81:
                    count += 81;
                    break;
                case 82:
                    count += 82;
                    break;
                case 83:
                    count += 83;
                    break;
                case 84:
                    count += 84;
                    break;
                case 85:
                    count += 85;
                    break;
                case 86:
                    count += 86;
                    break;
                case 87:
                    count += 87;
                    break;
                case 88:
                    count += 88;
                    break;
                case 89:
                    count += 89;
                    break;
                case 90:
                    count += 90;
                    break;
                case 91:
                    count += 91;
                    break;
                case 92:
                    count += 92;
                    break;
                case 93:
                    count += 93;
                    break;
                case 94:
                    count += 94;
                    break;
                case 95:
                    count += 95;
                    break;
                case 96:
                    count += 96;
                    break;
                case 97:
                    count += 97;
                    break;
                case 98:
                    count += 98;
                    break;
                case 99:
                    count += 99;
                    break;
                case 100:
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
                case 1:
                    count += 1;
                    break;
                case 2:
                    count += 2;
                    break;
                case 3:
                    count += 3;
                    break;
                case 4:
                    count += 4;
                    break;
                case 5:
                    count += 5;
                    break;
                case 6:
                    count += 6;
                    break;
                case 7:
                    count += 7;
                    break;
                case 8:
                    count += 8;
                    break;
                case 9:
                    count += 9;
                    break;
                case 10:
                    count += 10;
                    break;
                case 11:
                    count += 11;
                    break;
                case 12:
                    count += 12;
                    break;
                case 13:
                    count += 13;
                    break;
                case 14:
                    count += 14;
                    break;
                case 15:
                    count += 15;
                    break;
                case 16:
                    count += 16;
                    break;
                case 17:
                    count += 17;
                    break;
                case 18:
                    count += 18;
                    break;
                case 19:
                    count += 19;
                    break;
                case 20:
                    count += 20;
                    break;
                case 21:
                    count += 21;
                    break;
                case 22:
                    count += 22;
                    break;
                case 23:
                    count += 23;
                    break;
                case 24:
                    count += 24;
                    break;
                case 25:
                    count += 25;
                    break;
                case 26:
                    count += 26;
                    break;
                case 27:
                    count += 27;
                    break;
                case 28:
                    count += 28;
                    break;
                case 29:
                    count += 29;
                    break;
                case 30:
                    count += 30;
                    break;
                case 31:
                    count += 31;
                    break;
                case 32:
                    count += 32;
                    break;
                case 33:
                    count += 33;
                    break;
                case 34:
                    count += 34;
                    break;
                case 35:
                    count += 35;
                    break;
                case 36:
                    count += 36;
                    break;
                case 37:
                    count += 37;
                    break;
                case 38:
                    count += 38;
                    break;
                case 39:
                    count += 39;
                    break;
                case 40:
                    count += 40;
                    break;
                case 41:
                    count += 41;
                    break;
                case 42:
                    count += 42;
                    break;
                case 43:
                    count += 43;
                    break;
                case 44:
                    count += 44;
                    break;
                case 45:
                    count += 45;
                    break;
                case 46:
                    count += 46;
                    break;
                case 47:
                    count += 47;
                    break;
                case 48:
                    count += 48;
                    break;
                case 49:
                    count += 49;
                    break;
                case 50:
                    count += 50;
                    break;
                case 51:
                    count += 51;
                    break;
                case 52:
                    count += 52;
                    break;
                case 53:
                    count += 53;
                    break;
                case 54:
                    count += 54;
                    break;
                case 55:
                    count += 55;
                    break;
                case 56:
                    count += 56;
                    break;
                case 57:
                    count += 57;
                    break;
                case 58:
                    count += 58;
                    break;
                case 59:
                    count += 59;
                    break;
                case 60:
                    count += 60;
                    break;
                case 61:
                    count += 61;
                    break;
                case 62:
                    count += 62;
                    break;
                case 63:
                    count += 63;
                    break;
                case 64:
                    count += 64;
                    break;
                case 65:
                    count += 65;
                    break;
                case 66:
                    count += 66;
                    break;
                case 67:
                    count += 67;
                    break;
                case 68:
                    count += 68;
                    break;
                case 69:
                    count += 69;
                    break;
                case 70:
                    count += 70;
                    break;
                case 71:
                    count += 71;
                    break;
                case 72:
                    count += 72;
                    break;
                case 73:
                    count += 73;
                    break;
                case 74:
                    count += 74;
                    break;
                case 75:
                    count += 75;
                    break;
                case 76:
                    count += 76;
                    break;
                case 77:
                    count += 77;
                    break;
                case 78:
                    count += 78;
                    break;
                case 79:
                    count += 79;
                    break;
                case 80:
                    count += 80;
                    break;
                case 81:
                    count += 81;
                    break;
                case 82:
                    count += 82;
                    break;
                case 83:
                    count += 83;
                    break;
                case 84:
                    count += 84;
                    break;
                case 85:
                    count += 85;
                    break;
                case 86:
                    count += 86;
                    break;
                case 87:
                    count += 87;
                    break;
                case 88:
                    count += 88;
                    break;
                case 89:
                    count += 89;
                    break;
                case 90:
                    count += 90;
                    break;
                case 91:
                    count += 91;
                    break;
                case 92:
                    count += 92;
                    break;
                case 93:
                    count += 93;
                    break;
                case 94:
                    count += 94;
                    break;
                case 95:
                    count += 95;
                    break;
                case 96:
                    count += 96;
                    break;
                case 97:
                    count += 97;
                    break;
                case 98:
                    count += 98;
                    break;
                case 99:
                    count += 99;
                    break;
                case 100:
                    count += 100;
                    break;
                default:
                    count += i;
            }

        }

        blackhole.consume(count);
    }



}