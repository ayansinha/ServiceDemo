package org.service.demo

import android.app.job.JobParameters
import android.app.job.JobService

private const val TAG = "JobSchedulerImpl"
class JobSchedulerImpl: JobService() {
    override fun onStartJob(job: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onStopJob(job: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }
}