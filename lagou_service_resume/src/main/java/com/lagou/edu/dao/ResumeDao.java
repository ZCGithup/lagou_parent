package com.lagou.edu.dao;


import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {



}
