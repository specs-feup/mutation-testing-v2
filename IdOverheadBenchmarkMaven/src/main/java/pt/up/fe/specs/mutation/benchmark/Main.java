package pt.up.fe.specs.mutation.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class Main {

    static {
        System.setProperty("MUID", "ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
    }

    private static final String MUID_STATIC = System.getProperty("MUID");

    private static final ThreadLocal<String> MUID_THREAD_LOCAL = ThreadLocal.withInitial(() -> System.getProperty("MUID"));

    @Param({ "1000" })
    public int iterations;

    /*
    @Setup(Level.Invocation)
    public void setUp() {
        System.setProperty("MUID", "ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
    }
     */

    /*
    @Benchmark
    public void init() {
        // Do nothing
    }
    */

    @Benchmark
    public void baseClean(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            count += i;
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidPath1(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa")) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidDefault(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy")) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidPath1V1(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(System.getProperty("MUID"))) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidDefaultV1(Blackhole blackhole) {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(System.getProperty("MUID"))) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidPath1V2(Blackhole blackhole) {
        var muid = System.getProperty("MUID");

        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(muid)) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidDefaultV2(Blackhole blackhole) {
        var muid = System.getProperty("MUID");

        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(muid)) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidPath1V3(Blackhole blackhole) {


        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(MUID_STATIC)) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidDefaultV3(Blackhole blackhole) {


        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(MUID_STATIC)) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidPath1V4(Blackhole blackhole) {


        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

    @Benchmark
    public void baseGetMuidDefaultV4(Blackhole blackhole) {


        long  count = 0;
        for(int i=0; i<iterations; i++) {
            if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

}