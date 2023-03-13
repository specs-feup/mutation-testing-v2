package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

public class KadabraWrapper {
    private String outputPath;
    private String projectPath;
    private boolean debugMessages;
    private String filePath;
    private boolean traditionalMutation;

    public KadabraWrapper(String outputPath, String projectPath, boolean debugMessages, String filePath, boolean traditionalMutation) {
        this.outputPath = outputPath;
        this.projectPath = projectPath;
        this.debugMessages = debugMessages;
        this.filePath = filePath;
        this.traditionalMutation = traditionalMutation;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public boolean isDebugMessages() {
        return debugMessages;
    }

    public void setDebugMessages(boolean debugMessages) {
        this.debugMessages = debugMessages;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isTraditionalMutation() {
        return traditionalMutation;
    }

    public void setTraditionalMutation(boolean traditionalMutation) {
        this.traditionalMutation = traditionalMutation;
    }

    @Override
    public String toString() {
        return "KadabraWrapper{" +
                "outputPath='" + outputPath + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", debugMessages=" + debugMessages +
                ", filePath='" + filePath + '\'' +
                ", traditionalMutation=" + traditionalMutation +
                '}';
    }
}
