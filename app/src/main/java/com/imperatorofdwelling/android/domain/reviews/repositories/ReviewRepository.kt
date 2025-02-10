package com.imperatorofdwelling.android.domain.reviews.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.reviews.entities.Review

interface ReviewRepository {

    fun getReviewsByStayId(id: String): NetworkResult<List<Review>>

    fun addReview(review: Review): NetworkResult<Boolean>

    fun deleteReview(id: String): NetworkResult<Boolean>

}