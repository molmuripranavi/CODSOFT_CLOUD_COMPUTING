package com.molmuripranavi.educloud.models

data class StudentGrade(
    var id: String = "",
    var studentId: String = "",
    var subjectName: String = "",
    var internalMarks: String = "",
    var externalMarks: String = "",
    var grade: String = ""
)
