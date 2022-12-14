package com.ssafy.api.response.userRoom;

import com.ssafy.api.response.room.RoomUserRes;
import com.ssafy.db.entity.Mode;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("MyRoomResponse")
public class UserRoomRes {

    @ApiModelProperty(name = "방 ID", example = "123")
    Long id;

    @ApiModelProperty(name = "호스트 정보")
    RoomUserRes hostUser;

    @ApiModelProperty(name = "방 이름", example = "coding_with_me")
    String name;

    @ApiModelProperty(name = "방 커버 이미지 URL", example = "https://images.unsplash.com/photo-1622653533660-a1538fe8424c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80")
    String imageUrl;

    @ApiModelProperty(name = "방 설명", example = "나랑 모각코 할 사람~")
    String description;

    @ApiModelProperty(name = "방 모드", example = "1")
    int mode;

    @ApiModelProperty(name = "방 최대 참가 인원 수", example = "4")
    int maxPopulation;

    @ApiModelProperty(name = "공개 여부", example = "1")
    boolean isPublic;

    public static UserRoomRes of(Room room){
        if(room == null){
            return null;
        }

        UserRoomRes res = new UserRoomRes();

        res.setId(room.getId());
        res.setHostUser(room.getHostUser());
        res.setName(room.getName());
        res.setImageUrl(room.getImageUrl());
        res.setDescription(room.getDescription());
        res.setMode(room.getMode() == Mode.FINGER ? 0 : 1);
        res.setMaxPopulation(room.getMaxPopulation());
        res.setPublic(room.isPublic());

        return res;
    }

    public void setHostUser(User hostUser){
        this.hostUser = RoomUserRes.of(hostUser);
    }
}
