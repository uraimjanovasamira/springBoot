package com.example.springBoot.mapper;

import com.example.springBoot.model.Group;
import com.example.springBoot.model.dto.request.GroupRequest;
import com.example.springBoot.model.dto.response.GroupResponse;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Group groupMapper(GroupRequest request) {
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setCourses(request.getCourses());
        group.setUsers(request.getUsers());
        group.setCourseId(request.getCourseId());
        return group;
    }

    public GroupResponse mapToResponse(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .dateOfStart(group.getDateOfStart())
                .dateOfFinish(group.getDateOfFinish())
                .build();
    }
}
