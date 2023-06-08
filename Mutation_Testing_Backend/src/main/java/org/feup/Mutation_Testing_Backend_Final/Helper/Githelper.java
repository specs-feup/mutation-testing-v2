package org.feup.Mutation_Testing_Backend_Final.Helper;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectVersion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Githelper {

    public static void checkoutCommit(String localPath, String commit) throws IOException, GitAPIException {
        Repository repository = new FileRepositoryBuilder().setGitDir(new File(localPath + "/.git")).build();

        Git git = new Git(repository);
        ObjectId commitId = repository.resolve(commit);

        if (commitId == null) {
            throw new IllegalArgumentException("Invalid commit");
        }

        git.checkout().setName(commitId.getName()).call();

    }

    public static Git cloneRepo(String url, String path) throws GitAPIException {
        Git git = Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(path))
                .call();

        return git;
    }

    public static List<ProjectVersion> getCommitsHistory(Git repo, Project project) throws GitAPIException {
        // Get all the refs (branches, tags, etc.) in the repository
        List<Ref> refs = repo.tagList().call();
        refs.addAll(repo.branchList().call());

        List<ProjectVersion> projectVersionList = new ArrayList<>();

        for (Ref ref : refs) {
            ProjectVersion projectVersion = new ProjectVersion();
            projectVersion.setVersion(ref.getObjectId().getName());
            projectVersion.setBranch(ref.getTarget().getName());
            projectVersion.setProject(project);
            projectVersionList.add(projectVersion);
        }

        repo.close();

        return projectVersionList;
    }

    public static boolean updateCurrentVersion(String localPath, String targetCommit) {
        try {
            Repository repository = Git.open(new File(localPath + "/.git")).getRepository();
            String currentCommitId = repository.resolve("HEAD").getName();

            if (!currentCommitId.equals(targetCommit)){
                checkoutCommit(localPath, targetCommit);
            }
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


    public static List<String> getChangedFiles(String oldCommitId, String newCommitId, String repoPath, String ignorePath)
            throws IOException, RevisionSyntaxException, GitAPIException {

        List<String> changedFiles = new ArrayList<>();

        Repository repository = Git.open(new File(repoPath)).getRepository();
        RevWalk walk = new RevWalk(repository);

        ObjectId oldCommit = repository.resolve(oldCommitId);
        ObjectId newCommit = repository.resolve(newCommitId);

        RevCommit oldRevCommit = walk.parseCommit(oldCommit);
        RevCommit newRevCommit = walk.parseCommit(newCommit);

        CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
        oldTreeParser.reset(repository.newObjectReader(), oldRevCommit.getTree().getId());

        CanonicalTreeParser newTreeParser = new CanonicalTreeParser();
        newTreeParser.reset(repository.newObjectReader(), newRevCommit.getTree().getId());

        Git git = new Git(repository);
        List<DiffEntry> diffs = git.diff()
                .setOldTree(oldTreeParser)
                .setNewTree(newTreeParser)
                .call();

        for (DiffEntry diff : diffs) {
            changedFiles.add(File.separator + diff.getNewPath());
        }

        return changedFiles.stream().filter(file -> !file.contains(ignorePath) && file.endsWith(".java")).collect(Collectors.toList());
    }


    public static boolean isFirst(String version, String repoPath) throws IOException {
        Repository repository = Git.open(new File(repoPath)).getRepository();

        ObjectId commitId = repository.resolve(version);

        if (commitId == null) {
            throw new IllegalArgumentException("Invalid commit");
        }

        RevWalk revWalk = new RevWalk(repository);
        RevCommit commit = revWalk.parseCommit(commitId);
        Ref firstCommitRef = repository.getRefDatabase().findRef("refs/heads/master");

        if (firstCommitRef == null) {
            return true;
        }

        ObjectId firstCommitId = firstCommitRef.getObjectId();

        return commit.getId().equals(firstCommitId);
    }
}
