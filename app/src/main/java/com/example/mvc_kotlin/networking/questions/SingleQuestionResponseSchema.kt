package com.example.mvc_kotlin.networking.questions

import com.example.mvc_kotlin.questions.QuestionDetails
import com.google.gson.annotations.SerializedName

data class SingleQuestionResponseSchema(@SerializedName("items") val questions: List<QuestionDetails>) {
    val question: QuestionDetails get() = questions[0]
}