package com.imperatorofdwelling.android.presentation.entities.dwelling

import com.imperatorofdwelling.android.R

sealed class Properties  {
    abstract val nameResource: Int

    private var _count: Int = 0
    val count: Int
        get() = _count

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
}

data object Adult : Properties (){
    override val nameResource: Int = R.string.adults
}

data object Children : Properties (){
    override val nameResource: Int = R.string.children
}

data object Babies : Properties (){
    override val nameResource: Int = R.string.babies
}

data object Pets : Properties (){
    override val nameResource: Int = R.string.pets
}
