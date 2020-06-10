package com.example.habitbread.base

class Const {
    val RESPONSE_STATUS_SUCCEED = 200;
    val RESPONSE_STATUS_FAILED = getDefaultErrors();

    private fun getDefaultErrors() : HashMap<String, Int> {
        val errors = HashMap<String, Int>();
        errors["a"] = 402;
        return errors;
    }
}