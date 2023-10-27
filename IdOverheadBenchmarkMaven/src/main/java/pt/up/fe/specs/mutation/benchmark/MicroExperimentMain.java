package pt.up.fe.specs.mutation.benchmark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MicroExperimentMain {

    private final static boolean IS_ANDROID = false;

    private static int REPETITIONS = 10_000_000;

    private static final String STATIC_FINAL_STATIC_INIT = "ae7f9682-6bd8-45e8-a3da-4020ccc153fa";

    private static String STATIC_NON_FINAL_STATIC_INIT = "ae7f9682-6bd8-45e8-a3da-4020ccc153fa";

    private static final String STATIC_FINAL_WITH_PROP = getProp();

    private static String STATIC_NON_FINAL_WITH_PROP = getProp();

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
                Process process = Runtime.getRuntime().exec("setprop debug.MUID ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Setup property
            System.setProperty("debug.MUID", "ae7f9682-6bd8-45e8-a3da-4020ccc153fa");
        }
    }

    public static void main(String[] args) {

        setProp();

        MicroExperimentMain.testHarness("static final, static init, first path", () -> staticFinalWithStaticInitFirstPath());
        MicroExperimentMain.testHarness("static final, static init, else path", () -> staticFinalWithStaticInitElsePath());
        MicroExperimentMain.testHarness("static non-final, static init, first path", () -> staticNonFinalWithStaticInitFirstPath());
        MicroExperimentMain.testHarness("static non-final, static init, else path", () -> staticNonFinalWithStaticInitElsePath());
        MicroExperimentMain.testHarness("static final, property, first path", () -> staticFinalWithPropFirstPath());
        MicroExperimentMain.testHarness("static final, property, else path", () -> staticFinalWithPropElsePath());
        MicroExperimentMain.testHarness("static non-final, property, first path", () -> staticNonFinalWithPropFirstPath());
        MicroExperimentMain.testHarness("static non-final, property, else path", () -> staticNonFinalWithPropElsePath());
    }

    public static long testHarness(String name, Runnable experiment) {
        var start = System.nanoTime();
        for(int i=0; i<REPETITIONS; i++) {
            experiment.run();
        }
        var stop = System.nanoTime();
        var duration = stop-start;
        System.out.println("Experiment '"+name+"' took " + (duration)/1_000_000 + "ms");

        return duration;
    }

    public static long staticFinalWithStaticInitFirstPath() {

        long  count = 0;
        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticFinalWithStaticInitElsePath() {
        var count = 0l;

        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_FINAL_STATIC_INIT)) {
            count -= 1;
        } else {
            count += 1;
        }
        
        return count;
    }

    public static long staticNonFinalWithStaticInitFirstPath() {

        long  count = 0;
        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticNonFinalWithStaticInitElsePath() {
        var count = 0l;

        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_NON_FINAL_STATIC_INIT)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticFinalWithPropFirstPath() {

        long  count = 0;
        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticFinalWithPropElsePath() {
        var count = 0l;

        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_FINAL_WITH_PROP)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticNonFinalWithPropFirstPath() {

        long  count = 0;
        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }

    public static long staticNonFinalWithPropElsePath() {
        var count = 0l;

        if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-dummy".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        } else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-0".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-1".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-2".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-3".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-4".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-5".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-6".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-7".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-8".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-9".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-10".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-11".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-12".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-13".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-14".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-15".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-16".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-17".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-18".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-19".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-20".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-21".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-22".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-23".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-24".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-25".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-26".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-27".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-28".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-29".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-30".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-31".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-32".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-33".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-34".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-35".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-36".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-37".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-38".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-39".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-40".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-41".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-42".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-43".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-44".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-45".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-46".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-47".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-48".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-49".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-50".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-51".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-52".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-53".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-54".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-55".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-56".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-57".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-58".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-59".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-60".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-61".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-62".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-63".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-64".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-65".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-66".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-67".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-68".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-69".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-70".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-71".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-72".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-73".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-74".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-75".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-76".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-77".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-78".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-79".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-80".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-81".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-82".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-83".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-84".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-85".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-86".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-87".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-88".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-89".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-90".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-91".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-92".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-93".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-94".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-95".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-96".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-97".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-98".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        }
        else if("ae7f9682-6bd8-45e8-a3da-4020ccc153fa-99".equals(STATIC_NON_FINAL_WITH_PROP)) {
            count -= 1;
        } else {
            count += 1;
        }

        return count;
    }
}
