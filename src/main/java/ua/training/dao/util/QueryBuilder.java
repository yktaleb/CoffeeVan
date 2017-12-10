package ua.training.dao.util;

public class QueryBuilder {
    private StringBuilder query;

    public QueryBuilder() {
        query = new StringBuilder();
    }

    public QueryBuilder insert() {
        query
                .append("INSERT")
                .append(" ");
        return this;
    }

    public QueryBuilder into() {
        query
                .append("INTO")
                .append(" ");
        return this;
    }

    public QueryBuilder table(String table) {
        query
                .append("`")
                .append(table)
                .append("`")
                .append(" ");
        return this;
    }

    public QueryBuilder insertValues(String[] names) {
        query.append("(");
        int length = names.length;
        for (int i = 0; i < length; i++) {
            query
                    .append("`")
                    .append(names[i])
                    .append("`");
            if (i != length - 1) {
                query.append(",");
            }
        }
        query
                .append(")")
                .append(" ")
                .append("VALUES")
                .append("(");
        for (int i = 0; i < length; i++) {
            query.append("?");
            if (i != length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        return this;
    }

    public QueryBuilder update() {
        query
                .append("UPDATE")
                .append(" ");
        return this;
    }

    public QueryBuilder set() {
        query
                .append("SET")
                .append(" ");
        return this;
    }

    public QueryBuilder updateValues(String[] names) {
        int length = names.length;
        for (int i = 0; i < length; i++) {
            query
                    .append(names[i])
                    .append("=")
                    .append("?");
            if (i != length - 1) {
                query.append(",");
            }
        }
        query.append(" ");
        return this;
    }

    public QueryBuilder where() {
        query
                .append("WHERE")
                .append(" ");
        return this;
    }

    public QueryBuilder condition(String tableName, String column) {
        query
                .append(tableName)
                .append(".")
                .append(column)
                .append("=")
                .append("?");
        return this;
    }

    public QueryBuilder selectAll() {
        query
                .append("SELECT")
                .append(" ")
                .append("*")
                .append(" ");
        return this;
    }

    public QueryBuilder select(String[] columns) {
        query
                .append("SELECT")
                .append(" ");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i != columns.length - 1) {
                query.append(",");
            }
        }
        query.append(" ");
        return this;
    }

    public QueryBuilder select(String column) {
        query
                .append("SELECT")
                .append(" ")
                .append("`")
                .append(column)
                .append("`")
                .append(" ");
        return this;
    }
    public QueryBuilder select() {
        query
                .append("SELECT")
                .append(" ");
        return this;
    }

    public QueryBuilder from() {
        query
                .append("FROM")
                .append(" ");
        return this;
    }

    public QueryBuilder delete() {
        query
                .append("DELETE")
                .append(" ");
        return this;
    }

    public QueryBuilder orderBy(String column) {
        query
                .append("ORDER BY")
                .append(" ")
                .append(column);
        return this;
    }

    public QueryBuilder limit(long limit, long offset) {
        query
                .append("limit")
                .append(" ")
                .append(limit)
                .append(" ")
                .append("offset")
                .append(" ")
                .append(offset);
        return this;
    }

    public QueryBuilder count() {
        query
                .append("count(*)")
                .append(" ");
        return this;
    }

    public QueryBuilder count(String columnName) {
        query
                .append("count")
                .append("(")
                .append(columnName)
                .append(")")
                .append(" ");
        return this;
    }

    public QueryBuilder as(String alias) {
        query
                .append("as")
                .append(" ")
                .append(alias)
                .append(" ");
        return this;
    }

    public String build() {
        return query.toString();
    }
}
