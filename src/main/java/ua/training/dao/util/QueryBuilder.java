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
                .append(table)
                .append(" ");
        return this;
    }

    public QueryBuilder insertValues(String[] names) {
        query.append("(");
        int length = names.length;
        for (int i = 0; i < length; i++) {
            query.append(names[i]);
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

    public QueryBuilder condition(String tableName, String field) {
        query
                .append(tableName)
                .append(".")
                .append(field)
                .append("=")
                .append("?");
        return this;
    }

    public QueryBuilder selectAll() {
        query
                .append("SELECT")
                .append("*")
                .append(" ");
        return this;
    }

    public QueryBuilder from() {
        query.append("FROM");
        return this;
    }

    public String built() {
        return query.toString();
    }
}
