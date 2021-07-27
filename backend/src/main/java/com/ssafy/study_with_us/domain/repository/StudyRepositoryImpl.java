package com.ssafy.study_with_us.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.study_with_us.domain.entity.QStudy;
import com.ssafy.study_with_us.domain.entity.Theme;
import com.ssafy.study_with_us.dto.StudyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.study_with_us.domain.entity.QStudy.*;
import static com.ssafy.study_with_us.domain.entity.QTheme.theme;

@Repository
public class StudyRepositoryImpl implements StudyRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;

    public StudyRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Theme> getThemes() {
        return jpaQueryFactory.selectFrom(theme).from(theme).fetch();
    }

    @Override
    public Object update(StudyDto params) {
        jpaQueryFactory.update(study)
                .set(study.studyName, params.getStudyName())
                .set(study.studyIntro, params.getStudyIntro())
                .where(study.id.eq(params.getId()))
                .execute();
        return null;
    }

}
