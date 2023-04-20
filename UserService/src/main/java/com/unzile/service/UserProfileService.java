package com.unzile.service;

import com.unzile.dto.request.UserProfileSaveRequestDto;
import com.unzile.dto.request.UserProfileUpdateRequestDto;
import com.unzile.exception.ErrorType;
import com.unzile.exception.UserException;
import com.unzile.mapper.IUserProfileMapper;
import com.unzile.rabbitmq.model.CreateUserModel;
import com.unzile.repository.IUserProfileRepository;
import com.unzile.repository.entity.UserProfile;
import com.unzile.utility.JwtTokenManager;
import com.unzile.utility.ServiceManager;
import com.unzile.utility.TokenCreator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    private final JwtTokenManager jwtTokenManager;
    public  UserProfileService(IUserProfileRepository repository,
                               JwtTokenManager jwtTokenManager){
        super(repository);
        this.repository=repository;
        this.jwtTokenManager=jwtTokenManager;
    }

    public void save(UserProfileSaveRequestDto dto){
      save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
    }

   public void save(CreateUserModel model){
        save(IUserProfileMapper.INSTANCE.toUserProfile(model));
   }
    public void update(UserProfileUpdateRequestDto dto){
        Optional<Long> authid = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authid.isEmpty())
            throw new UserException(ErrorType.ERROR_INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(authid.get());
        if(userProfile.isPresent()){
            UserProfile profile = userProfile.get();
            profile.setAddress(dto.getAddress());
            profile.setAvatar(dto.getAvatar());
            profile.setGender(dto.getGender());
            profile.setName(dto.getName());
            profile.setPhone(dto.getPhone());
            profile.setSurname(dto.getSurname());
            update(profile);
        }
    }



}

