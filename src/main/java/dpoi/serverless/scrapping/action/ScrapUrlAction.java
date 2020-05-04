/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package dpoi.serverless.scrapping.action;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import dpoi.serverless.scrapping.configuration.ExceptionMessages;
import dpoi.serverless.scrapping.exception.BadRequestException;
import dpoi.serverless.scrapping.exception.DAOException;
import dpoi.serverless.scrapping.exception.InternalErrorException;
import dpoi.serverless.scrapping.model.DAOFactory;
import dpoi.serverless.scrapping.model.action.CreateScrapResponse;
import dpoi.serverless.scrapping.model.action.ScrapUrlRequest;
import dpoi.serverless.scrapping.model.scrap.Scrap;
import dpoi.serverless.scrapping.model.scrap.ScrapDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;

import static dpoi.serverless.scrapping.Utils.*;
import static dpoi.serverless.scrapping.configuration.ExceptionMessages.EX_INVALID_INPUT;

/**
 * Action that scraps an url and posts result to 'scraps' table
 */
public class ScrapUrlAction implements ApplicationAction {

  public String handle(JsonObject request, Context lambdaContext)
          throws BadRequestException, InternalErrorException {
    final LambdaLogger logger = lambdaContext.getLogger();

    final ScrapUrlRequest input = getGson().fromJson(request, ScrapUrlRequest.class);

    if (input == null || isEmpty(input.getUrl())) {
      throw new BadRequestException(EX_INVALID_INPUT);
    }

    final ScrapDAO dao = DAOFactory.getScrapDAO();

    final Scrap scrap = new Scrap();

    scrap.setUuid(nextId());
    scrap.setDate(new Date());
    scrap.setContent(fetchStructuredData(input, logger));

    try {
      dao.createScrap(scrap);
    } catch (final DAOException e) {
      logger.log("Error while creating new scrap\n" + e.getMessage());
      throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
    }

    final CreateScrapResponse output = new CreateScrapResponse();
    output.setScrapId(scrap.getUuid());

    return getGson().toJson(output);
  }

  private String fetchStructuredData(ScrapUrlRequest request, LambdaLogger logger) {
    // Return jsonld for given request
    try {
      Document document = Jsoup.connect(request.getUrl()).get();
      return document.select("script[type=application/ld+json]").first().childNode(0).toString();
    } catch (IOException e) {
      System.out.println(e.toString());
      return null;
    }
  }
}
