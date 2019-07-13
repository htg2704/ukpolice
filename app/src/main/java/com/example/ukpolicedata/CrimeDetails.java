package com.example.ukpolicedata;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class CrimeDetails implements Parcelable {
    public static final Creator<CrimeDetails> CREATOR = new Creator<CrimeDetails>() {
        public CrimeDetails createFromParcel(Parcel in) {
            return new CrimeDetails(in);
        }

        public CrimeDetails[] newArray(int size) {
            return new CrimeDetails[size];
        }
    };
    @SerializedName("category")
    private String category;
    @SerializedName("context")
    private String context;
    @SerializedName("id")

    /* renamed from: id */
    private String f41id;
    @SerializedName("location")
    private Location location;
    @SerializedName("location_subtype")
    private String locationSubtype;
    @SerializedName("location_type")
    private String location_type;
    @SerializedName("month")
    private String month;
    @SerializedName("outcome_status")
    private OutcomeStatus outcomeStatus;
    @SerializedName("persistent_id")
    private String persistent_id;

    public class Location {
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("street")
        private Street street;

        public Location() {
        }

        public String getLatitude() {
            return this.latitude;
        }

        public Street getStreet() {
            return this.street;
        }

        public String getLongitude() {
            return this.longitude;
        }
    }

    public class OutcomeStatus {
        @SerializedName("category")
        private String category;
        @SerializedName("date")
        private String date;

        public OutcomeStatus() {
        }

        public String getCategory() {
            return this.category;
        }

        public String getDate() {
            return this.date;
        }
    }

    public class Street {
        @SerializedName("id")

        /* renamed from: id */
        private String f42id;
        @SerializedName("name")
        private String name;

        public Street() {
        }

        public String getId() {
            return this.f42id;
        }

        public String getName() {
            return this.name;
        }
    }

    protected CrimeDetails(Parcel in) {
        this.category = in.readString();
        this.location_type = in.readString();
        this.context = in.readString();
        this.persistent_id = in.readString();
        this.f41id = in.readString();
        this.locationSubtype = in.readString();
        this.month = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category);
        dest.writeString(this.location_type);
        dest.writeString(this.context);
        dest.writeString(this.persistent_id);
        dest.writeString(this.f41id);
        dest.writeString(this.locationSubtype);
        dest.writeString(this.month);
    }

    public String getCategory() {
        return this.category;
    }

    public String getLocation_type() {
        return this.location_type;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getContext() {
        return this.context;
    }

    public OutcomeStatus getOutcomeStatus() {
        return this.outcomeStatus;
    }

    public String getPersistent_id() {
        return this.persistent_id;
    }

    public String getId() {
        return this.f41id;
    }

    public String getLocationSubtype() {
        return this.locationSubtype;
    }

    public String getMonth() {
        return this.month;
    }
}