package com.example.practiceapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practiceapp.config.Config
import com.google.gson.annotations.SerializedName

@Entity(tableName = Config.SAVED_DETAILS_TABLE)
data class UserData(
	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("id")
	@PrimaryKey
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)