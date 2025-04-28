package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.BirthDateData
import com.imperatorofdwelling.android.data.entities.UserData
import com.imperatorofdwelling.android.data.entities.UserEditData
import com.imperatorofdwelling.android.domain.user.entities.BirthDateDomain
import com.imperatorofdwelling.android.domain.user.entities.UserDomain

fun UserData.toDomain(): UserDomain {
    return UserDomain(
        avatar = this.avatar,
        birthDate = this.birthDate?.toDomain(),
        city = this.city,
        country = this.country,
        createdAt = this.createdAt,
        email = this.email,
        gender = this.gender,
        id = this.id,
        name = this.name,
        national = this.national,
        phone = this.phone,
        updatedAt = this.updatedAt
    )
}

fun BirthDateData.toDomain(): BirthDateDomain {
    return BirthDateDomain(
        time = this.time,
        valid = this.valid
    )
}

fun UserDomain.toData(): UserData {
    return UserData(
        avatar = this.avatar,
        birthDate = this.birthDate?.toData(),
        city = this.city ?: "",
        country = this.country ?: "",
        createdAt = this.createdAt ?: "",
        email = this.email,
        gender = this.gender ?: "",
        id = this.id,
        name = this.name,
        national = this.national ?: "",
        phone = this.phone ?: "",
        updatedAt = this.updatedAt ?: ""
    )
}

fun UserDomain.toUserEditData(): UserEditData {
    return UserEditData(
        avatar = this.avatar,
        birthDate = this.birthDate?.toData(),
        city = this.city ?: "",
        country = this.country ?: "",
        createdAt = this.createdAt ?: "",
        gender = this.gender ?: "",
        id = this.id,
        name = this.name,
        national = this.national ?: "",
        phone = this.phone ?: "",
        updatedAt = this.updatedAt ?: ""
    )
}

fun BirthDateDomain.toData(): BirthDateData {
    return BirthDateData(
        time = this.time,
        valid = this.valid
    )
}