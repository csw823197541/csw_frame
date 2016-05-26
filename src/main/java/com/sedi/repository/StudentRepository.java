package com.sedi.repository;

import com.sedi.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/3/16.
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    Student findByUserName(String userName);
}
