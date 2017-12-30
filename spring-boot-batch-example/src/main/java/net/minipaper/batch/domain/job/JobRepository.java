package net.minipaper.batch.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "jobs")
public interface JobRepository extends JpaRepository<Job, String> {
}
