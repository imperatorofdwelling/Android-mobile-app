package com.imperatorofdwelling.android.presentation.entities.dwelling

import com.imperatorofdwelling.android.R

sealed class Properties  {
    abstract val nameResource: Int

    private var _count: Int = 0
    val count: Int
        get() = _count

    fun setEmptyCount(){
        _count = 0
    }

    fun increase(){
        if(_count < 20){
            _count++
        }
    }
    fun decrease(){
        if(_count > 0){
            _count--
        }
    }
}

data object Rooms : Properties() {
    override val nameResource: Int = R.string.rooms
    override fun toString() : String {
        if(count != 0){
            return "$count Rooms"
        }
        return "Rooms"
    }
}

data object Adults : Properties (){
    override val nameResource: Int = R.string.adults
    override fun toString() : String {
        if(count != 0){
            return "$count Adults"
        }
        return "Adults"
    }
}

data object Children : Properties (){
    override val nameResource: Int = R.string.children
    override fun toString() : String {
        if(count != 0){
            return "$count Children"
        }
        return "Children"
    }
}

data object Babies : Properties (){
    override val nameResource: Int = R.string.babies
    override fun toString(): String {
        if(count != 0){
            return "$count Babies"
        }
        return "Babies"
    }
}

data object Pets : Properties (){
    override val nameResource: Int = R.string.pets
    override fun toString(): String {
        if(count != 0){
            return "$count Pets"
        }
        return "Pets"
    }
}
