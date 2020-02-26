package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumClass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;



    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1 request data
        UserApiRequest userApiRequest=request.getData();

        //2 create User
        User user= User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser =userRepository.save(user);
        //3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne , getbyID;
        Optional<User> user=userRepository.findById(id);

        //user -> userApiResponse return
        return user
                .map(u-> response(u))
                .orElseGet(
                        ()->Header.ERROR("데이터없음")
                );

        }


    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        //1.data
        UserApiRequest userApiRequest = request.getData();

        //2. id -> user 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional
                .map(u -> {
                    //3. update
                    u.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setStatus(userApiRequest.getStatus())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                    return u;

                    //4. userApiResponse

                })
                .map(user -> userRepository.save(user))
                .map(upadteUser -> response(upadteUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));


    }

    @Override
    public Header delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));


    }

    private Header<UserApiResponse> response(User user){
        // user - > userApiResponse
        UserApiResponse userApiResponse=UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //todo 암호화
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisterAt(user.getUnregisteredAt())
                .build();

        //Header + data

        return Header.OK(userApiResponse);
    }
}
