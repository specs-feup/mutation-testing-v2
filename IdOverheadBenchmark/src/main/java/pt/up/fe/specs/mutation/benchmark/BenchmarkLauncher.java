package pt.up.fe.specs.mutation.benchmark;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

/*

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1)
@Warmup(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class BenchmarkLauncher {

/*
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(".*" + BenchmarkLauncher.class.getSimpleName() + ".*")

                .forks(1)

                .build();


        new Runner(opt).run();

    }
*/
    @Param({ "1000" })
    public int iterations;


    @Setup(Level.Invocation)
    public void setUp() {
        System.setProperty("MUID", "ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
    }

    @Benchmark
    public void baseClean() {
        long  count = 0;
        for(int i=0; i<iterations; i++) {
            count += i;
        }

    }

    @Benchmark
    public void baseGetterPath1() {
        if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("ae7f9682-6bd8-45e8-a3da-4020ccc153fa")) {
            long  count = 0;
            for(int i=0; i<iterations; i++) {
                count += i;
            }

        } else {
            long  count = 0;
            for(int i=0; i<iterations; i++) {
                count += i;
            }

        }
    }





}