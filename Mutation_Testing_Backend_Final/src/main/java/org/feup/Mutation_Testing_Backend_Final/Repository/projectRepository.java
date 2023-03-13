package org.feup.Mutation_Testing_Backend_Final.Repository;

import org.feup.Mutation_Testing_Backend_Final.Model.Project;
import org.springframework.data.repository.CrudRepository;

public interface projectRepository extends CrudRepository<Project, Long> {
}
