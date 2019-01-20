package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post implements Parcelable {

    @Expose
    @SerializedName("number_share")
    private int number_share;
    @Expose
    @SerializedName("number_like")
    private int number_like;
    @Expose
    @SerializedName("number_comment")
    private int number_comment;
    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("number_seen")
    private int number_seen;
    @Expose
    @SerializedName("off_comment")
    private int off_comment;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("fullname")
    private String fullname;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("type_share")
    private int type_share;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("is_like")
    private int is_like;
    @Expose
    @SerializedName("is_delete")        //cho phep quyen xoa bai post
    private int is_delete;
    @Expose
    @SerializedName("active_comment")        //cho phep quyen bat/tat comment bai post
    private int is_comment;
    @Expose
    @SerializedName("user")
    private Profile user;

    @Expose
    @SerializedName("post_share")
    private Post postShare;

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public List<Image> getList_image() {
        return list_image;
    }

    public void setList_image(List<Image> list_image) {
        this.list_image = list_image;
    }

    @Expose
    @SerializedName(value = "list_image", alternate = "file")
    private List<Image> list_image;
    @Expose
    @SerializedName(value = "comment")
    private List<Comment> list_comment;

    public List<Comment> getList_comment() {
        return list_comment;
    }

    public int getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(int is_comment) {
        this.is_comment = is_comment;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public Post getPostShare() {
        return postShare;
    }

    public void setPostShare(Post postShare) {
        this.postShare = postShare;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public void setList_comment(List<Comment> list_comment) {
        this.list_comment = list_comment;
    }

    public int getNumber_share() {
        return number_share;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public void setNumber_share(int number_share) {
        this.number_share = number_share;
    }

    public int getNumber_like() {
        return number_like;
    }

    public void setNumber_like(int number_like) {
        if (number_like < 0) number_like = 0;
        this.number_like = number_like;

    }

    public int getNumber_comment() {
        return number_comment;
    }

    public void setNumber_comment(int number_comment) {
        this.number_comment = number_comment;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getNumber_seen() {
        return number_seen;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setNumber_seen(int number_seen) {
        this.number_seen = number_seen;
    }

    public int getOff_comment() {
        return off_comment;
    }

    public void setOff_comment(int off_comment) {
        this.off_comment = off_comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType_share() {
        return type_share;
    }

    public void setType_share(int type_share) {
        this.type_share = type_share;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number_share);
        dest.writeInt(this.number_like);
        dest.writeInt(this.number_comment);
        dest.writeString(this.active);
        dest.writeInt(this.number_seen);
        dest.writeInt(this.off_comment);
        dest.writeString(this.date);
        dest.writeString(this.avatar);
        dest.writeString(this.fullname);
        dest.writeString(this.content);
        dest.writeString(this.address);
        dest.writeInt(this.type_share);
        dest.writeString(this.user_id);
        dest.writeInt(this.id);
        dest.writeInt(this.is_like);
        dest.writeInt(this.is_delete);
        dest.writeInt(this.is_comment);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.postShare, flags);
        dest.writeTypedList(this.list_image);
        dest.writeList(this.list_comment);
    }

    protected Post(Parcel in) {
        this.number_share = in.readInt();
        this.number_like = in.readInt();
        this.number_comment = in.readInt();
        this.active = in.readString();
        this.number_seen = in.readInt();
        this.off_comment = in.readInt();
        this.date = in.readString();
        this.avatar = in.readString();
        this.fullname = in.readString();
        this.content = in.readString();
        this.address = in.readString();
        this.type_share = in.readInt();
        this.user_id = in.readString();
        this.id = in.readInt();
        this.is_like = in.readInt();
        this.is_delete = in.readInt();
        this.is_comment = in.readInt();
        this.user = in.readParcelable(Profile.class.getClassLoader());
        this.postShare = in.readParcelable(Post.class.getClassLoader());
        this.list_image = in.createTypedArrayList(Image.CREATOR);
        this.list_comment = new ArrayList<Comment>();
        in.readList(this.list_comment, Comment.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
