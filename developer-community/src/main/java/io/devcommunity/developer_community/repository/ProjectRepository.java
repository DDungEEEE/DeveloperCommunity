package io.devcommunity.developer_community.repository;

import io.devcommunity.developer_community.domain.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
