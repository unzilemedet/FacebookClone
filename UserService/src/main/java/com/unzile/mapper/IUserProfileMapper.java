package com.unzile.mapper;

import com.unzile.dto.request.UserProfileSaveRequestDto;
import com.unzile.dto.request.UserProfileUpdateRequestDto;
import com.unzile.rabbitmq.model.CreateUserModel;
import com.unzile.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {
  IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

  UserProfile toUserProfile(final UserProfileSaveRequestDto dto);
  UserProfile toUserProfile(final UserProfileUpdateRequestDto dto);
  UserProfile toUserProfile(final CreateUserModel dto);
}