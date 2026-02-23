package com.example.Spring.LMS.mapper;


import com.example.Spring.LMS.tests.answers.AnswerOptionEntity;
import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.QuestionEntity;
import com.example.Spring.LMS.tests.questions.dto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.TestEntity;
import com.example.Spring.LMS.tests.test.dto.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TestMapper {

    Test toResponse(TestEntity testEntity);

    @Mapping(source = "text", target = "name")
    QuestionToCreate toQuestionResponse(QuestionEntity questionEntity);

    AnswersToAnswer toAnswerResponse(AnswerOptionEntity answerOptionEntity);


}
