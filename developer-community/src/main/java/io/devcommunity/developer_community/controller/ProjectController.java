package io.devcommunity.developer_community.controller;

import io.devcommunity.developer_community.domain.dto.request.ProjectCreateReqDto;
import io.devcommunity.developer_community.domain.dto.response.ProjectResponseDto;
import io.devcommunity.developer_community.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDto create(@RequestBody ProjectCreateReqDto projectCreateReqDto){
        return projectService.createProject(projectCreateReqDto);
    }

    @GetMapping
    public List<ProjectResponseDto> getAll(){
        return projectService.getAllProjects();
    }
}
