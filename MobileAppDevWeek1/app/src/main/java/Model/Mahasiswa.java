package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {

    //Properties
    private String nama, age, address;

    //Constructor
    public Mahasiswa(String nama, String age, String address) {
        this.nama = nama;
        this.age = age;
        this.address = address;
    }
    public Mahasiswa() {
        this.nama = "";
        this.age = "";
        this.address = "";
    }

    //Method
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNama() {
        return nama;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    //Parcelable Method
    protected Mahasiswa(Parcel in) {
        nama = in.readString();
        age = in.readString();
        address = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(age);
        dest.writeString(address);
    }
}
