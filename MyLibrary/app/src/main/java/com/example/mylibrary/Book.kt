package com.example.mylibrary

import android.os.Parcel
import android.os.Parcelable

data class Book(var id: Int?, val title:String?, val author: String?, val type: String?, val pages:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        //parcel.readString(),
        parcel.readInt()
        //parcel.readInt()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(type)
        //parcel.writeString(read)
        parcel.writeInt(pages)
        //parcel.writeValue(readPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}

