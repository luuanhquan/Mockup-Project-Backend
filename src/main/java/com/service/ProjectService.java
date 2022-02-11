package com.service;

import com.DTO.ProjectDetailDTO;
import com.DTO.UserSimpleDTO;
import com.entity.ProjectUser;
import com.entity.Projects;
import com.repositories.ProjectRepository;
import com.repositories.ProjectUserRepository;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    IssueService issueService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProjectUserRepository projectUserRepository;


    public List<Projects> findAll() {
        return projectRepository.findAll();
    }

    public List<ProjectDetailDTO> findAllActive(){
        List<ProjectDetailDTO> projectDetailDTOList= projectRepository.findAllActive();
        projectDetailDTOList.stream().forEach(projectDetailDTO -> {
            projectDetailDTO.setListIssues(issueService.findByProject(projectDetailDTO.getId()));
            projectDetailDTO.setListMember(usersRepository.findByProject(projectDetailDTO.getId()));
            projectDetailDTO.setPM(usersRepository.findPM(projectDetailDTO.getId()));
        });
        return  projectDetailDTOList;
    }


    public Projects findbyProjects(Integer id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

    //Add project
    public Projects addProject(Projects projects) {
        return projectRepository.save(projects);
    }

    //Edit Project
    public Projects updateProject(Projects projects) {
        return projectRepository.saveAndFlush(projects);
    }

    //Delete Project
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    public Optional<Projects> findById(Integer id) {
        return projectRepository.findById(id);
    }

    public void remove(Projects product) {
        projectRepository.delete(product);
    }

}
