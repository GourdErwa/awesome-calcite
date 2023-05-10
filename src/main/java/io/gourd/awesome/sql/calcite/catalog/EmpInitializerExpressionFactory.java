/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gourd.awesome.sql.calcite.catalog;

import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.schema.ColumnStrategy;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.sql2rel.InitializerContext;
import org.apache.calcite.sql2rel.NullInitializerExpressionFactory;

import java.math.BigDecimal;

/**
 * Default values for the "EMPDEFAULTS" table.
 */
class EmpInitializerExpressionFactory
        extends NullInitializerExpressionFactory {
    @Override
    public ColumnStrategy generationStrategy(RelOptTable table,
                                             int iColumn) {
        return switch (iColumn) {
            case 0, 1, 5 -> ColumnStrategy.DEFAULT;
            default -> super.generationStrategy(table, iColumn);
        };
    }

    @Override
    public RexNode newColumnDefaultValue(RelOptTable table,
                                         int iColumn, InitializerContext context) {
        final RexBuilder rexBuilder = context.getRexBuilder();
        final RelDataTypeFactory typeFactory = rexBuilder.getTypeFactory();
        return switch (iColumn) {
            case 0 -> rexBuilder.makeExactLiteral(new BigDecimal(123),
                    typeFactory.createSqlType(SqlTypeName.INTEGER));
            case 1 -> rexBuilder.makeLiteral("Bob");
            case 5 -> rexBuilder.makeExactLiteral(new BigDecimal(555),
                    typeFactory.createSqlType(SqlTypeName.INTEGER));
            default -> super.newColumnDefaultValue(table, iColumn, context);
        };
    }
}
