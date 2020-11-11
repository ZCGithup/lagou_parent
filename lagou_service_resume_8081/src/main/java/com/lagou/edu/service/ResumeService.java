package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
public interface ResumeService {

    Resume findDefaultResumeByUserID(Long UserId);
}
