package com.unzile.service;

import com.unzile.repository.IUserProfileRepository;
import com.unzile.repository.entity.UserProfile;
import com.unzile.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository=repository;
    }
}
