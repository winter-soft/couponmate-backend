package com.daou.kooteam.dto.follow;

import com.daou.kooteam.model.Follow;
import com.daou.kooteam.model.RelationShip;
import com.daou.kooteam.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowResDTO {
    private int follow_id;
    private User user; // follower
    private RelationShip relationShip; // user1과 user2의 관계

    public static FollowResDTO of(Follow follow){
        return FollowResDTO.builder()
                .follow_id(follow.getFollow_id())
                .user(follow.getUser2())
                .relationShip(follow.getRelationShip())
                .build();
    }
}
