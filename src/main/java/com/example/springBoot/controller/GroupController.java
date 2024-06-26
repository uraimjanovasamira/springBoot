package com.example.springBoot.controller;

import com.example.springBoot.model.Group;
import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.GroupRequest;
import com.example.springBoot.model.dto.response.GroupResponse;
import com.example.springBoot.repository.GroupRepository;
import com.example.springBoot.service.GroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/groups")
public class GroupController {
    GroupService groupService;
    private final GroupRepository groupRepository;


    @PostMapping
    public GroupResponse save(@RequestBody GroupRequest request) {
        return groupService.save(request);
    }


    @GetMapping("/{id}")
    public GroupResponse findById(@PathVariable("id") Long id) {
        return groupService.findById(id);
    }

    @GetMapping
    public List<GroupResponse> findAll() {
        return groupService.findAll();
    }

    @PutMapping("/{id}")
    public Group update(@PathVariable("id") Long id, @RequestBody GroupRequest request) {
        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return groupService.delete(id);
    }
}
