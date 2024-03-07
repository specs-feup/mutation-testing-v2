package pt.up.fe.specs.mutation.benchmarkV2;

import java.util.HashMap;
import java.util.Map;

public class GeneratorLauncher {
/*
    private static final String NUMBER_KEY = "<NUMBER>";
    private static final Map<BenchmarkVersion, String> TEMPLATES;

    static {
        TEMPLATES = new HashMap<>();

        TEMPLATES.put(BenchmarkVersion.BASE, "else if(System.getProperty(\"MUID\") != null && System.getProperty(\"MUID\").equals(\"ae7f9682-6bd8-45e8-a3da-4020ccc153fa-"+NUMBER_KEY+"\")) {\n" +
                "                count -= i;\n" +
                "            }\n");

        TEMPLATES.put(BenchmarkVersion.V1, "else if(\"ae7f9682-6bd8-45e8-a3da-4020ccc153fa-"+NUMBER_KEY+"\".equals(System.getProperty(\"MUID\"))) {\n" +
                "                count -= i;\n" +
                "            }\n");

        TEMPLATES.put(BenchmarkVersion.V2, "else if(\"ae7f9682-6bd8-45e8-a3da-4020ccc153fa-"+NUMBER_KEY+"\".equals(muid)) {\n" +
                "                count -= i;\n" +
                "            }\n");


        TEMPLATES.put(BenchmarkVersion.V3, "else if(\"ae7f9682-6bd8-45e8-a3da-4020ccc153fa-"+NUMBER_KEY+"\".equals(MUID_STATIC)) {\n" +
                "                count -= i;\n" +
                "            }\n");

        TEMPLATES.put(BenchmarkVersion.V4, "else if(\"ae7f9682-6bd8-45e8-a3da-4020ccc153fa-"+NUMBER_KEY+"\".equals(MUID_THREAD_LOCAL.get())) {\n" +
                "                count -= i;\n" +
                "            }\n");
    }

    public enum BenchmarkVersion {
        BASE,
        V1,
        V2,
        V3,
        V4;
    }
*/
    public static void main(String[] args) {

        // 10, 25, 50, 75, 100
        var numCopies = 100;


        //System.out.println(generateSwitchInt(numCopies));
        //System.out.println(generateSwitchString(numCopies));
        System.out.println(generateIfElseInt(numCopies));

//        for(var version : BenchmarkVersion.values()) {
//            var code = new StringBuilder();
//            for(int i=0; i<numCopies; i++) {
//                code.append(TEMPLATES.get(version).replace(NUMBER_KEY, Integer.toString(i)));
//            }
//
//            System.out.println("------------------\nCODE FOR VERSION " + version+"\n------------------\n\n\n\n"+code+"\n\n\n\n");
//        }


    }

    public static String generateSwitchInt(int numCopies) {
        var code = new StringBuilder();

        code.append("switch(MUID) {\n");

        for(int i=0; i<numCopies; i++) {
            code.append("""
                    case %s:
                        count += %s;
                        break;
                    """.formatted(i+1, i+1));
        }

        code.append("""
                    default:
                        count += i;
                }
                """);

        return code.toString();
    }

    public static String generateSwitchString(int numCopies) {
        var firstValue = "ae7f9682-6bd8-45e8-a3da-4020ccc153fa";

        var code = new StringBuilder();


        code.append("switch(MUID) {\n");

        code.append("""
                    case "%s":
                        count += 1;
                        break;
                    """.formatted(firstValue, 1));

        for(int i=1; i<numCopies; i++) {
            code.append("""
                    case "%s":
                        count += %s;
                        break;
                    """.formatted(firstValue+i, i+1));
        }

        code.append("""
                    default:
                        count += i;
                }
                """);

        return code.toString();
    }


    public static String generateIfElseInt(int numCopies) {
        var code = new StringBuilder();

        code.append("""
                if(MUID == 1) {
                    count += 1;
                }
                """);

        for(int i=1; i<numCopies; i++) {
            code.append("""
                    else if(MUID == %s) {
                        count +=%s;
                    }
                    """.formatted(i+1, i+1));
        }

        code.append("""
                else {
                    count += i;
                }
                """);

        return code.toString();
    }

}
