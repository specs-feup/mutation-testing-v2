package pt.up.fe.specs.mutation.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class MainRep100 {

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
            } else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99")) {
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
            } else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98")) {
                count -= i;
            }
            else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99")) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(System.getProperty("MUID"))) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(System.getProperty("MUID"))) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(System.getProperty("MUID"))) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(muid)) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(muid)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(muid)) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(MUID_STATIC)) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(MUID_STATIC)) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(MUID_STATIC)) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(MUID_THREAD_LOCAL.get())) {
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
            } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            }
            else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(MUID_THREAD_LOCAL.get())) {
                count -= i;
            } else {
                count += i;
            }
        }

        blackhole.consume(count);
    }

}