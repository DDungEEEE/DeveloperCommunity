package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.domain.dto.request.ProjectCreateReqDto;
import io.devcommunity.developer_community.domain.dto.response.ProjectResponseDto;
import io.devcommunity.developer_community.domain.entity.Projects;
import io.devcommunity.developer_community.repository.ProjectRepository;
import io.devcommunity.developer_community.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UsersRepository usersRepository;

    public ProjectResponseDto createProject(ProjectCreateReqDto projectCreateReqDto){
        Projects createProject = projectCreateReqDto.asProject();
        createProject.updateOwner(usersRepository.findById(projectCreateReqDto.getUserId()).get());

        Projects saveProject = projectRepository.save(createProject);
        return ProjectResponseDto.of(saveProject);
    }

    public List<ProjectResponseDto> getAllProjects(){
        return ProjectResponseDto.of(projectRepository.findAll());
    }
}
