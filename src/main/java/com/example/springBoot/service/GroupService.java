package com.example.springBoot.service;

import com.example.springBoot.mapper.GroupMapper;
import com.example.springBoot.model.Group;
import com.example.springBoot.model.dto.request.GroupRequest;
import com.example.springBoot.model.dto.response.GroupResponse;
import com.example.springBoot.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;


    public GroupResponse save(GroupRequest request) {
        Group group = groupMapper.groupMapper(request);
        groupRepository.save(group);
        return groupMapper.mapToResponse(group);
    }

    public List<GroupResponse> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::mapToResponse).toList();
    }

    public GroupResponse findById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Group by id" + id));
        return groupMapper.mapToResponse(group);
    }

    public Group update(Long id, GroupRequest request) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found group by id" + id));
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setUsers(request.getUsers());
        group.setCourses(request.getCourses());
        group.setCourseId(request.getCourseId());
        log.info("Successfully updated Group with id:{}", id);
        return groupRepository.save(group);
    }


    public String delete(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found group by id" + id));
        groupRepository.delete(group);
        return "Successfully deleted group by id" + id;
    }
}
