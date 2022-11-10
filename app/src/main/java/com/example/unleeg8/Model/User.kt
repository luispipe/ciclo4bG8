package com.example.unleeg8.Model

import android.provider.ContactsContract.CommonDataKinds.Phone
import java.io.Serializable
import java.sql.Timestamp

class User : Serializable {
    lateinit var name: String
    lateinit var email: String
    lateinit var bithdate: Timestamp
    lateinit var phone: String
}