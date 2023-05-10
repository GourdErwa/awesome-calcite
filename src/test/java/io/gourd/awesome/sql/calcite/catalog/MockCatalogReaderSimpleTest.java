package io.gourd.awesome.sql.calcite.catalog;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Programs;
import org.apache.calcite.tools.RelBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author Li.Wei by 2023/5/9
 */
@Slf4j
class MockCatalogReaderSimpleTest {

    public static Frameworks.ConfigBuilder config() {
        return Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.Config.DEFAULT)
                //.defaultSchema(rootSchema)
                .programs(Programs.heuristicJoinOrder(Programs.RULE_SET, true, 2));
    }

    @Test
    void testPrint() {
        final RelDataTypeFactory typeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        final MockCatalogReaderSimple simple = new MockCatalogReaderSimple(typeFactory, true).init();
        LOG.info(simple.getRootSchema().toString());
        LOG.info(simple.getRootSchema().getSubSchemaMap().toString());
    }

    @Test
    void relBuilder() {
        final RelBuilder builder = RelBuilder.create(config().build());
        final RelNode root = builder.scan("norn_bi", "datasource").project().build();
        LOG.info(root.toString());
    }
}
