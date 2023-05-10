package io.gourd.awesome.sql.jooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;


/**
 * @author Li.Wei by 2023/5/10
 */
class DslMainTest {

    void example1() {
        DSLContext create = DSL.using(SQLDialect.MYSQL);
//        create.select("book.title")
//                .from("BOOK")
//                .where(BOOK.PUBLISHED_IN.eq(2011))
//                .orderBy(BOOK.TITLE)
    }

    class Book {

    }
}
