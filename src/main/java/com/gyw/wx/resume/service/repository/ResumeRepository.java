package com.gyw.wx.resume.service.repository;

import com.gyw.wx.resume.service.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by guoyouwei on 2018/2/5.
 */
public interface ResumeRepository extends JpaRepository<Resume,Long> {

    @Query("SELECT a.content FROM Resume a where a.type = :resumeType")
    String getContentByType(@Param("resumeType")String type);

}
