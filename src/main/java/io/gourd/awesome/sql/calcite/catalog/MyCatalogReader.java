package io.gourd.awesome.sql.calcite.catalog;

import org.apache.calcite.config.CalciteConnectionConfig;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.sql.validate.SqlNameMatcher;

import java.util.List;

/**
 * @author Li.Wei by 2023/5/9
 */
public class MyCatalogReader extends CalciteCatalogReader {

    public MyCatalogReader(CalciteSchema rootSchema, List<String> defaultSchema,
                           RelDataTypeFactory typeFactory, CalciteConnectionConfig config) {
        super(rootSchema, defaultSchema, typeFactory, config);
    }

    protected MyCatalogReader(CalciteSchema rootSchema, SqlNameMatcher nameMatcher,
                              List<List<String>> schemaPaths, RelDataTypeFactory typeFactory,
                              CalciteConnectionConfig config) {
        super(rootSchema, nameMatcher, schemaPaths, typeFactory, config);
    }

    // 计算器加法实现

}
