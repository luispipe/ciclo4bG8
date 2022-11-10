package com.example.unleeg8.Model

import java.io.Serializable
import java.sql.Timestamp

class Books: Serializable {
    lateinit var title:String
    lateinit var author:String
    lateinit var editorial:String
    lateinit var yearOfPublication:Timestamp
    lateinit var price: Number
}