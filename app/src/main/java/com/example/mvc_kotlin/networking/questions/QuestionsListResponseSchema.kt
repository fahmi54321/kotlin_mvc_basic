package com.example.mvc_kotlin.networking.questions

import com.example.mvc_kotlin.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)