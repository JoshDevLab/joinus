package com.josh.joinus.core.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {

    override fun getAsyncExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 10
        executor.maxPoolSize = 10
        executor.queueCapacity = 10000
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setAwaitTerminationSeconds(10)
        executor.initialize()
        return executor
    }
}