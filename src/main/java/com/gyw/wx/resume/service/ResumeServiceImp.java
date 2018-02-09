package com.gyw.wx.resume.service;

import com.gyw.wx.resume.service.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guoyouwei on 2018/2/5.
 */
@Service
public class ResumeServiceImp implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public String getContentByType(String type) {
       return resumeRepository.getContentByType(type);
    }
}
