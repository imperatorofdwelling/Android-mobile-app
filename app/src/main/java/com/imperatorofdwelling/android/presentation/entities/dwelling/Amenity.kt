package com.imperatorofdwelling.android.presentation.entities.dwelling

import com.imperatorofdwelling.android.R

sealed class Amenity{
    abstract val iconDrawableId: Int
    abstract val nameStringId: Int
    abstract val count: Int?

    fun hasCount() : Boolean {
        return count != null
    }
}


class RoomsAmenity(override val count: Int): Amenity(){
    override val iconDrawableId: Int = R.drawable.rooms_amenity
    override val nameStringId: Int = R.string.rooms_amenity
}

class BedsAmenity(override val count: Int): Amenity(){
    override val iconDrawableId: Int = R.drawable.beds_amenity
    override val nameStringId: Int = R.string.beds_amenity
}

class WiFiAmenity(): Amenity(){
    override val iconDrawableId: Int = R.drawable.wi_fi_amenity
    override val nameStringId: Int = R.string.wi_fi_amenity
    override val count: Int? = null
}

class HomeLightControlAmenity(): Amenity(){
    override val iconDrawableId: Int = R.drawable.home_light_control_amenity
    override val nameStringId: Int = R.string.home_light_control_amenity
    override val count: Int? = null
}

class SmartDoorLocksAmenity() : Amenity(){
    override val iconDrawableId: Int = R.drawable.smart_doors_amenity
    override val nameStringId: Int = R.string.smart_doors_amenity
    override val count: Int? = null
}

class VoiceAssistantAmenity(): Amenity(){
    override val iconDrawableId: Int = R.drawable.voice_assistant_amenity
    override val nameStringId: Int = R.string.voice_assistant_amenity
    override val count: Int? = null
}

class TouchControlPanels(): Amenity(){
    override val iconDrawableId: Int = R.drawable.touch_control_panels_amenity
    override val nameStringId: Int = R.string.touch_control_panels_amenity
    override val count: Int? = null
}