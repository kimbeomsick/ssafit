package com.ssafy.ssafit.model.dto;

public class Follow {
    private String follower;
    private String following;

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "follower='" + follower + '\'' +
                ", following='" + following + '\'' +
                '}';
    }
}
