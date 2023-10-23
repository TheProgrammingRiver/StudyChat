package com.example.studychat.repositores;

import com.example.studychat.models.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
    @Query("SELECT sr FROM StudyRoom sr JOIN sr.users u WHERE u.userId = :userId")
    List<StudyRoom> findRoomsByUserId(@Param("userId") Long userId);
}