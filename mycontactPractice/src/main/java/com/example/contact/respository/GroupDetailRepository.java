package com.example.contact.respository;

import com.example.contact.model.entity.Group;
import com.example.contact.model.entity.GroupDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDetailRepository extends JpaRepository<GroupDetail,Long> {

}
