/**
 *    Copyright 2016-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql.util.kotlin

import org.mybatis.dynamic.sql.BindableColumn
import org.mybatis.dynamic.sql.SqlTable
import org.mybatis.dynamic.sql.VisitableCondition
import org.mybatis.dynamic.sql.select.CountDSL
import org.mybatis.dynamic.sql.select.SelectModel

typealias CountCompleter = KotlinCountBuilder.() -> KotlinCountBuilder

class KotlinCountBuilder(val dsl: CountDSL<SelectModel>) {
    fun join(table: SqlTable, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.join(table, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun join(table: SqlTable, alias: String, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.join(table, alias, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun fullJoin(table: SqlTable, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.fullJoin(table, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun fullJoin(table: SqlTable, alias: String, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.fullJoin(table, alias, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun leftJoin(table: SqlTable, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.leftJoin(table, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun leftJoin(table: SqlTable, alias: String, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.leftJoin(table, alias, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun rightJoin(table: SqlTable, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.rightJoin(table, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun rightJoin(table: SqlTable, alias: String, receiver: JoinReceiver) =
            apply {
                val collector = JoinCollector()
                receiver(collector)
                dsl.rightJoin(table, alias, collector.onJoinCriterion, collector.andJoinCriteria)
            }

    fun <T> where(column: BindableColumn<T>, condition: VisitableCondition<T>) =
            apply {
                dsl.where(column, condition)
            }

    fun <T> where(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
            apply {
                val collector = CriteriaCollector()
                collect(collector)
                // TODO...awkward
                dsl.where(column, condition, *collector.criteria.toTypedArray())
            }

    fun applyWhere(whereApplier: WhereApplier) =
            apply {
                dsl.applyWhere(whereApplier)
            }

    fun <T> and(column: BindableColumn<T>, condition: VisitableCondition<T>) =
            apply {
                dsl.where().and(column, condition)
            }

    fun <T> and(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
            apply {
                val collector = CriteriaCollector()
                collect(collector)
                // TODO...awkward
                dsl.where().and(column, condition, *collector.criteria.toTypedArray())
            }

    fun <T> or(column: BindableColumn<T>, condition: VisitableCondition<T>) =
            apply {
                dsl.where().or(column, condition)
            }

    fun <T> or(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
            apply {
                val collector = CriteriaCollector()
                collect(collector)
                // TODO...awkward
                dsl.where().or(column, condition, *collector.criteria.toTypedArray())
            }

    fun allRows() = this
}
