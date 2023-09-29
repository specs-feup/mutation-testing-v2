package pt.up.fe.specs.mutation.benchmark;

import java.util.Map;
import java.util.HashMap;

public class IfChainGeneratorLauncher {

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

    public static void main(String[] args) {

        // 10, 25, 50, 75, 100
        var numCopies = 100;

        for(var version : BenchmarkVersion.values()) {
            var code = new StringBuilder();
            for(int i=0; i<numCopies; i++) {
                code.append(TEMPLATES.get(version).replace(NUMBER_KEY, Integer.toString(i)));
            }

            System.out.println("------------------\nCODE FOR VERSION " + version+"\n------------------\n\n\n\n"+code+"\n\n\n\n");
        }


    }

}
