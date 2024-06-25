package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.Question;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question> {

    List<Question> findByResultExamens(Long id);
}
