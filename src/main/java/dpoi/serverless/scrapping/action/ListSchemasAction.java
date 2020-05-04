package dpoi.serverless.scrapping.action;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import dpoi.serverless.scrapping.exception.BadRequestException;
import dpoi.serverless.scrapping.exception.InternalErrorException;

/**
 * Action that returns the list of all available schemas
 */
public class ListSchemasAction implements ApplicationAction {
    @Override
    public String handle(JsonObject request, Context lambdaContext)
            throws BadRequestException, InternalErrorException {
        return null;
    }
}
