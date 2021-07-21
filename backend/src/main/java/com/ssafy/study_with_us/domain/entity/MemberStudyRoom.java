package com.ssafy.study_with_us.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "member_studyroom")
public class MemberStudyroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_studyroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "studyroom_id")
    private Studyroom studyRoom;

    public MemberStudyroom() {
    }

    public MemberStudyroom(Long id, Member member, Studyroom studyRoom) {
        this.id = id;
        this.member = member;
        this.studyRoom = studyRoom;
    }

    @Override
    public String toString() {
        return "MemberStudyRoom{" +
                "id=" + id +
                ", member=" + member +
                ", studyRoom=" + studyRoom +
                '}';
    }
}
