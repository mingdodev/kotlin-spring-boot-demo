package com.mykotlin.demo.global.config

import com.mykotlin.demo.domain.user.repository.Users
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import javax.sql.DataSource

@Configuration
class DatabaseConfig(private val dataSource: DataSource) {

    @EventListener(ContextRefreshedEvent::class)
    fun onApplicationEvent() {
        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(Users)
        }
    }
}