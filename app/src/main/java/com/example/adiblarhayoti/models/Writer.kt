package com.example.adiblarhayoti.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Writer:Serializable {

    @PrimaryKey
    var id:Int? = null

    var name:String? = null

    var year:String? = null

    var desc:String? = null

    var image:String? = null

    var favorite:Boolean? = false


    constructor()





}