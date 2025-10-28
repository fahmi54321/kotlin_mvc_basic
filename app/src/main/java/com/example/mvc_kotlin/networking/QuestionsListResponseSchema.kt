package com.example.mvc_kotlin.networking

import com.example.mvc_kotlin.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)