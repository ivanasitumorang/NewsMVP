package com.example.newsmvp.external

import io.reactivex.Scheduler

/**
 * Created by ivanaazuka on 10/1/2019.
 * Android Engineer
 **/

interface SchedulerProvider {
    fun ui() : Scheduler
    fun computation() : Scheduler
    fun io() : Scheduler
}