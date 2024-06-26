package com.kosta.board.dto;

public class UserInfo {
	private Long id;
	private String nickname;
	private String profile_image;
	private String thumbnail_image;
	
	public UserInfo() {}
	
	public UserInfo(Long id, String nickname, String profile_image, String thumbnail_image) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.profile_image = profile_image;
		this.thumbnail_image = thumbnail_image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getThumbnail_image() {
		return thumbnail_image;
	}
	public void setThumbnail_image(String thumbnail_image) {
		this.thumbnail_image = thumbnail_image;
	}
	
	
	
}
