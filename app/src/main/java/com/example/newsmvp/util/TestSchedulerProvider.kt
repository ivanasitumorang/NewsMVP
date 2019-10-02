package com.example.newsmvp.util

import com.example.newsmvp.external.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by ivanaazuka on 10/1/2019.
 * Android Engineer
 **/

class TestSchedulerProvider constructor(private val testScheduler: TestScheduler) :
    SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}