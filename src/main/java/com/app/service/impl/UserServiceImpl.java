package com.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.service.UserService;
import com.app.ultils.Constraints;
import com.app.dto.converter.UserConverter;
import com.app.dto.response.ProfileResponseType;
import com.app.dto.response.UserResponseType;
import com.app.entities.Profile;
import com.app.entities.Users;
import com.app.exception.NotFoundEntityException;
import com.app.repository.ProfileRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private UserConverter userConverter;
    private RoleRepository roleRepository;
    private ProfileRepository profileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, RoleRepository roleRepository, ProfileRepository profileRepository){
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public UserResponseType loadUserByEmail(String email) {
        // TODO Auto-generated method stub
        Optional<Users> user= userRepository.loadUser(email);
        if (user.isPresent()){
            return userConverter.ConvertToAll(user.get());
            
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public UserResponseType save(UserResponseType userResponseType) throws Exception {
        // TODO Auto-generated method stub
        UserResponseType response = null;
        Users user = userConverter.convertToEntity(userResponseType);
        user.setPasssword(bCryptPasswordEncoder.encode(user.getPasssword()));
        user.setRoles(roleRepository.getById(Constraints.ROLE_USER_ID));
        Users userSave = userRepository.save(user);
        if(null != userSave){
            response = userConverter.ConvertToBasic(userSave);
        }
        return response;
    }

    @Override
    public UserResponseType update(UserResponseType userResponseType, Integer id) throws Exception {
        // TODO Auto-generated method stub
        Users user = null;
        Optional<Users> usersOptional = Optional.ofNullable(userRepository.findById(id).orElse(null));
        Users userUpdate = userConverter.convertToEntity(userResponseType);
        if(usersOptional.isPresent()){
            user = usersOptional.get();
            if(userUpdate.getFullName() != null)
                user.setFullName(userUpdate.getFullName());
            
            if(userUpdate.getBirthDay() != null)
                user.setBirthDay(userUpdate.getBirthDay());

            if(userUpdate.getUuid() != null)
                user.setUuid(userUpdate.getUuid());

            if(userUpdate.getEmail() != null)
                user.setEmail(userUpdate.getEmail());

            if(userUpdate.getSex() != null)
                user.setSex(userUpdate.getSex());

            if(userUpdate.getUrlImg() != null)
                user.setUrlImg(userUpdate.getUrlImg());
            
            if(userUpdate.getPasssword() != null){
                if(bCryptPasswordEncoder.matches(userUpdate.getPasssword(),bCryptPasswordEncoder.encode(user.getPasssword()))){
                    user.setPasssword(bCryptPasswordEncoder.encode(user.getPasssword()));
                }
            }
            Profile profileUpdate = userUpdate.getProfile();
            Profile profileSave = null;
            if(profileUpdate != null){
                if(profileUpdate.getId() != null){
                    Profile profile = null;
                    Optional<Profile> profileOptional = Optional.ofNullable(profileRepository.findById(profileUpdate.getId()).orElse(null));
                    if(usersOptional.isPresent()){
                        profile = profileOptional.get();
                        profile.setAddress(profileUpdate.getAddress());
                        profile.setCCCD(profileUpdate.getCCCD());
                        profile.setCareer(profileUpdate.getCareer());
                        profile.setEducation(profileUpdate.getEducation());
                        profile.setExperence(profileUpdate.getExperence());
                        profile.setHeight(profileUpdate.getHeight());
                        profile.setHobby(profileUpdate.getHobby());
                        profile.setHomeId(profileUpdate.getHomeId());
                        profile.setJobInformation(profileUpdate.getJobInformation());
                        profile.setPersonality(profileUpdate.getPersonality());
                        profile.setPhone(profileUpdate.getPhone());
                        profile.setSalary(profileUpdate.getSalary());
                        profile.setSchoolName(profileUpdate.getSchoolName());
                        profile.setWeight(profileUpdate.getWeight());
                        profileSave = profile;
                    }}
                else{
                    profileSave = profileRepository.save(profileUpdate);
                }
                user.setProfile(profileSave);
            }
            Users userSave = userRepository.save(user);
            return userConverter.ConvertToDTO(userSave);
        }
         throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public UserResponseType findById(Integer id) {
        // TODO Auto-generated method stub
        Optional<Users> user= userRepository.findById(id);
        if (user.isPresent()){
            return userConverter.ConvertToDTO(user.get());
            
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

}
