package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.enumclass.AdminStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser=new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("adminUser01");
        adminUser.setStatus(AdminStatus.REGISTERED);
        adminUser.setRole("PARTNER");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("Admin Server");

        AdminUser newAdminUser=adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
        newAdminUser.setAccount("Change");
        adminUserRepository.save(newAdminUser);
    }

}