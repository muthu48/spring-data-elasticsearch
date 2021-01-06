package com.gpx.elastic.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user2", type = "user", createIndex = false)
public class User {
	@Id
	private String id;
	
	//@Field(type=FieldType.Text, fielddata=true)
	private String username;
	private String full_name;//Display name
	private String userType;
	private String status;
	private String sourceSystem;
	private String firstName;
	private String lastName;
	private String photoUrl;
	private String profileAvatarImgFileId;//for profile image
	private String profileBannerImgFileId;//for banner image
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the sourceSystem
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * @param sourceSystem
	 *            the sourceSystem to set
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getProfileAvatarImgFileId() {
		return profileAvatarImgFileId;
	}

	public void setProfileAvatarImgFileId(String profileAvatarImgFileId) {
		this.profileAvatarImgFileId = profileAvatarImgFileId;
	}

	public String getProfileBannerImgFileId() {
		return profileBannerImgFileId;
	}

	public void setProfileBannerImgFileId(String profileBannerImgFileId) {
		this.profileBannerImgFileId = profileBannerImgFileId;
	}
	
}