/*
 * *
 *   Created by Yoga Setiawan on 11/12/19 8:56 AM
 *   Copyright (c) 2019 . All rights reserved.
 *   Last modified 11/12/19 7:15 AM
 *   Github : https://github.com/arioki1/Submission-Kelas-Kade-Dicoding.git
 *
 */

package com.arioki.submission.data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaguesItem(
    val dateFirstEvent: String?,
    val idLeague: String?,
    val strLeague: String?,
    val strBadge: String?,
    val strGender: String?,
    val strCountry: String?
) : Parcelable