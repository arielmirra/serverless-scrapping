package dpoi.serverless.scapping.action;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import dpoi.serverless.scapping.exception.BadRequestException;
import dpoi.serverless.scapping.exception.InternalErrorException;

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
