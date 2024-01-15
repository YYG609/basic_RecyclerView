package com.android.basic_recyclerview

sealed class DogItems {
    data class MyItem(var aIcon:Int, var aName:String, var aAge:String) : DogItems()
    data class MyTitle(var age:String) : DogItems()
}
