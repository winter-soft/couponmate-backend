package com.daou.kooteam.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id @GeneratedValue
    private int follow_id;

    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1; // following user1이 user2를 팔로우한다.

    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2; // follower

    @Enumerated(EnumType.STRING)
    private RelationShip relationShip; // user1과 user2의 관계

    public void setRelationShip(RelationShip relationShip) {
        this.relationShip = relationShip;
    }
}
