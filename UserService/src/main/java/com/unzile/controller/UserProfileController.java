package com.unzile.controller;

import com.unzile.dto.request.UserProfileSaveRequestDto;
import com.unzile.dto.request.UserProfileUpdateRequestDto;
import com.unzile.repository.entity.UserProfile;
import com.unzile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    @GetMapping("/getpage")
    public ResponseEntity<String> getPage(){
        return ResponseEntity.ok("User Service e ulaştınız");
    }


    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid UserProfileSaveRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid UserProfileUpdateRequestDto dto){
         userProfileService.update(dto);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/findall")
    public ResponseEntity<List<UserProfile>> findAll(){
        return  ResponseEntity.ok(userProfileService.findAll());
    }
}
