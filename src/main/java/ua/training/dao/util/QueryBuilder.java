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

    public QueryBuilder values(String[] names) {
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

    public String built() {
        return query.toString();
    }
}
