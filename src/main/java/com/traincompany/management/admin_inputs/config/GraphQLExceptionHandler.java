package com.traincompany.management.admin_inputs.config;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return toGraphQLError(ex);
    }

    private GraphQLError toGraphQLError(Throwable ex) {
        return GraphqlErrorBuilder.newError().message(ex.getMessage()).errorType(ErrorType.DataFetchingException).build();
    }
}
