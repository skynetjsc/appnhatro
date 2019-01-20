package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DuongKK on 6/16/2017.
 */

public class MyPlace implements Parcelable {
    double lat;
    double lng;
    String type;
    @SerializedName("liked")
    int isFavourite;

    public double getLat() {
        return lat;
    }

    public boolean isFavourite() {
        if (isFavourite == 1)
            return true;
        return false;
    }

    public void setFavourite(int favourite) {
        isFavourite = favourite;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @SerializedName("description")
    private String description;
    @SerializedName("address")
    private String address;
    @SerializedName("name")
    private String name;
    @SerializedName("a_id")
    private String id;
    @SerializedName("structured_formatting")
    private Structured_formatting formatting;
//    @SerializedName("types")
//    private List<Types> types;

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Structured_formatting getFormatting() {
        return formatting;
    }

    public void setFormatting(Structured_formatting formatting) {
        this.formatting = formatting;
    }

    //    public void setDescription(String description) {
//        String []splitAddress = description.split(",");
//        this.description = splitAddress[0];
//        if(splitAddress.length>1)           this.description +="," +splitAddress[1];
//    }
    public void setDescription(String description) {

        this.description = description;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public List<Types> getTypes() {
//        return types;
//    }
//
//    public void setTypes(List<Types> types) {
//        this.types = types;
//    }

    public static class Matched_substrings {
        @SerializedName("length")
        private int length;
        @SerializedName("offset")
        private int offset;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
    }

    public static class Main_text_matched_substrings {
        @SerializedName("length")
        private int length;
        @SerializedName("offset")
        private int offset;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
    }

    public static class Structured_formatting {
        @SerializedName("main_text")
        private String main_text;
        @SerializedName("main_text_matched_substrings")
        private List<Main_text_matched_substrings> main_text_matched_substrings;
        @SerializedName("secondary_text")
        private String secondary_text;

        public String getMain_text() {
            return main_text;
        }

        public void setMain_text(String main_text) {
            this.main_text = main_text;
        }

        public List<Main_text_matched_substrings> getMain_text_matched_substrings() {
            return main_text_matched_substrings;
        }

        public void setMain_text_matched_substrings(List<Main_text_matched_substrings> main_text_matched_substrings) {
            this.main_text_matched_substrings = main_text_matched_substrings;
        }

        public String getSecondary_text() {
            return secondary_text;
        }

        public void setSecondary_text(String secondary_text) {
            this.secondary_text = secondary_text;
        }
    }

    public static class Terms {
        @SerializedName("offset")
        private int offset;
        @SerializedName("value")
        private String value;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public MyPlace() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeString(this.type);
        dest.writeInt(this.isFavourite);
        dest.writeString(this.description);
        dest.writeString(this.address);
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    protected MyPlace(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.type = in.readString();
        this.isFavourite = in.readInt();
        this.description = in.readString();
        this.address = in.readString();
        this.name = in.readString();
        this.id = in.readString();
//        this.formatting = in.readParcelable(Structured_formatting.class.getClassLoader());
    }

    public static final Creator<MyPlace> CREATOR = new Creator<MyPlace>() {
        @Override
        public MyPlace createFromParcel(Parcel source) {
            return new MyPlace(source);
        }

        @Override
        public MyPlace[] newArray(int size) {
            return new MyPlace[size];
        }
    };
}
